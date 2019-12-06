/**
* Title: UserController.java  

* Description:   

* @author xhz  

* @date 2019年8月2日  
 
 */
package com.imooc.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.imooc.BasicController;
import com.imooc.config.ResourceProp;
import com.imooc.pojo.UserReport;
import com.imooc.pojo.Users;
import com.imooc.pojo.vo.UsersVO;
import com.imooc.pojo.vo.UsersVideoVO;
import com.imooc.service.UsersService;
import com.imooc.utils.JsonResult;
import com.sun.research.ws.wadl.Resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author JoyPlay
 * @Description 进行用户的有关操作：1：用户注册 2：用户登录
 */
@Api(value = "用户接口", tags = { "用户操作接口" })
@RestController
@RequestMapping("/user")
public class UserController extends BasicController{
	
	@Autowired
	private UsersService usersService;
	
	//资源属性
	@Autowired
	private ResourceProp resourceProp;
	
	/**
	 * @name query
	 * @Description 通过前端传来的userId和fanId进行查询,如果fanId有值；则表示查询视频发布者的信息，否则查询用户本身信息；
	 * @param userId
	 * @return 
	 */
	@ApiOperation(value="查询用户信息", notes="查询用户信息操作")
	@ApiImplicitParams({
		@ApiImplicitParam(value="用户id", name="userId", paramType="query", dataType="String", required=true),
		@ApiImplicitParam(value="被关注者id", name="fanId", paramType="query", dataType="String", required=false)
	})
	@PostMapping("/query")
	public JsonResult query(String userId, String fanId){
		if (StringUtils.isBlank(userId)) {
			return JsonResult.errorMsg("用户id不能为空");
		}
		Users user=usersService.queryUserInfo(userId);
		if (null == user) {
			return JsonResult.errorMsg("id为"+userId+"的用户不存在");
		}
		UsersVO usersVO=new UsersVO();
		BeanUtils.copyProperties(user, usersVO);
		//设置与粉丝是否存在关注的关系
		usersVO.setFollow(usersService.isFollow(userId, fanId));
		return JsonResult.ok(usersVO);
	}

	/**
	 * @name uploadFace 
	 * @Description 用户进行头像上传操作
	 * @param 用户id
	 * @param files
	 * @return  
	 * @throws IOException 
	 */
	@ApiOperation(value="头像上传", notes="用户进行头像上传操作")
	@ApiImplicitParam(value="用户id", name="userId", paramType="query", dataType="String", required=true)
	@PostMapping(value="/uploadFace" , headers="content-type=multipart/form-data")
	public JsonResult uploadFace(String userId, @RequestParam("file") MultipartFile[] files){
		//判断用户id是否为空
		if (StringUtils.isBlank(userId)) {
			return JsonResult.errorMsg("用户为空!");
		}
		//文件存放路径c:/imooc_video_dev
		//String fileSpacePath="C:/imooc_video_dev";
		//数据库存放路径
		String fileDBPath=File.separator+userId+File.separator+"face";
		//文件名称
		String fileName=null;
		//字节输入缓冲流
		BufferedInputStream bufferedInputStream=null;
//		InputStream inputStream=null;
		//字节输出缓冲流
		BufferedOutputStream bufferedOutputStream=null;
//		OutputStream outputStream=null;
		//判断上传文件是否为空
		try {
			if(files !=null && files.length>0){
				//获取文件名称
			    fileName=files[0].getOriginalFilename();
				//判断文件名是否为空
				if (StringUtils.isNotBlank(fileName)) {
					//头像文件路径
					String finalName=resourceProp.getUploadNamespace()+fileDBPath+File.separator+fileName;
					//新建一个file对象
					File outFile=new File(finalName);
					//判断文件夹是否存在，当文件夹不存在时，创建文件夹
					if (outFile.getParentFile()!=null || !outFile.getParentFile().isDirectory()) {
						outFile.getParentFile().mkdirs();
					}
					//将上传的文件转换成流
					InputStream inputStream=files[0].getInputStream();
					bufferedInputStream =new BufferedInputStream(inputStream);
					bufferedOutputStream=new BufferedOutputStream(new FileOutputStream(outFile));
					IOUtils.copy(bufferedInputStream, bufferedOutputStream);
				}
				
			}else{
				return JsonResult.errorMsg("文件无法上传");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (bufferedInputStream != null) {
				try {
					bufferedInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (bufferedOutputStream != null) {
				try {
					bufferedOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}
		}
		fileDBPath += (File.separator+fileName);
		Users users =new Users();
		users.setId(userId);
		users.setFaceImage(fileDBPath);
		//将头像文件路径保存到数据库
		usersService.uploadFaceImage(users);
		//将用户记录查询出来
		Users user1=usersService.selectByPrimaryKey(users.getId());
		//查询用户对应的redis session
		String userToken=redisUtils.get("redis_user_session"+":"+user1.getId());
		UsersVO usersVO=new UsersVO();
		BeanUtils.copyProperties(users, usersVO);
		usersVO.setUserToken(userToken);
		
		return JsonResult.ok(fileDBPath);
	}
	
	/**
	 * @name queryPublisher
	 * @Description 用户查看视频发布者的信息，将发布者的信息和用户和视频的是否点赞关系返回给前端 
	 * @param userId
	 * @param videoId
	 * @param publisherId
	 * @return 
	 */
	@ApiOperation(value="用户查询视频发布者信息", notes="用户进查询视频发布者的信息操作")
	@ApiImplicitParams({
		@ApiImplicitParam(value="用户id", name="loginUserId", paramType="query", dataType="String", required=false),
		@ApiImplicitParam(value="视频id", name="videoId", paramType="query", dataType="String", required=true),
		@ApiImplicitParam(value="发布者id", name="publishUserId", paramType="query", dataType="String", required=true),
	})
	@PostMapping("/queryPublisher")
	public JsonResult queryPublisher(String loginUserId, String videoId, String publishUserId){
		//判断发布者的id和视频ID是否为空
		if (StringUtils.isBlank(videoId) || StringUtils.isBlank(publishUserId)) {
			return JsonResult.errorMsg("");
		}
		//将user用户信息和用户和视频的关系查询出来，封装成新的对象，返回给前端
		Users users=usersService.queryUserInfo(publishUserId);
		UsersVO publisher=new UsersVO();
		BeanUtils.copyProperties(users, publisher);
		Boolean isLike=usersService.isLike(loginUserId, videoId);
		UsersVideoVO usersVideoVO=new UsersVideoVO();
		usersVideoVO.setPublisher(publisher);
		usersVideoVO.setUserLikeVideo(isLike);
		
		return JsonResult.ok(usersVideoVO);
	}
	
	/**
	 * @name beyourfans
	 * @Description 用户进行关注其他用户
	 * @param userId
	 * @param fanId
	 * @return 
	 */
	@ApiOperation(value="关注用户操作", notes="用户进行关注其他用户")
	@ApiImplicitParams({
		@ApiImplicitParam(value="用户id", name="userId", paramType="query", dataType="String", required=true),
		@ApiImplicitParam(value="被关注者id", name="fanId", paramType="query", dataType="String", required=true)
	})
	@PostMapping("/beyourfans")
	public JsonResult beyourfans(String userId, String fanId){
		if (StringUtils.isBlank(userId) || StringUtils.isBlank(fanId)) {
			return JsonResult.errorMsg("");
		}
		usersService.saveFollowUsers(userId, fanId);
		return JsonResult.ok();
	}
	
	/**
	 * @name dontbeyourfans
	 * @Description 用户进行取消关注其他用户
	 * @param userId
	 * @param fanId
	 * @return 
	 */
	@ApiOperation(value="取消关注用户操作", notes="用户进行取消关注其他用户")
	@ApiImplicitParams({
		@ApiImplicitParam(value="用户id", name="userId", paramType="query", dataType="String", required=true),
		@ApiImplicitParam(value="被关注者id", name="fanId", paramType="query", dataType="String", required=true)
	})
	@PostMapping("/dontbeyourfans")
	public JsonResult dontbeyourfans(String userId, String fanId){
		if (StringUtils.isBlank(userId) || StringUtils.isBlank(fanId)) {
			return JsonResult.errorMsg("");
		}
		usersService.deleteFollowUsers(userId,fanId);
		return JsonResult.ok();
	}
	
	/**
	 * @name reportUser
	 * @Description 保存用户举报的相关信息
	 * @param userReport
	 * @return 
	 */
	@ApiOperation(value="举报视频", notes="用户进行举报操作")
	@PostMapping("/reportUser")
	public JsonResult reportUser(@RequestBody  UserReport userReport){
		usersService.saveUserReport(userReport);
		return JsonResult.ok();
	}
}
