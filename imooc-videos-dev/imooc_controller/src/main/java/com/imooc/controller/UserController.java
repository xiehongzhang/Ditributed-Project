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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.imooc.BasicController;
import com.imooc.pojo.Users;
import com.imooc.pojo.vo.UsersVO;
import com.imooc.pojo.vo.UsersVideoVO;
import com.imooc.service.UsersService;
import com.imooc.utils.JsonResult;

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
	
	/**
	 * @name queryUserInfo
	 * @Description 通过前端传来的userId和followedUserId进行查询,如果followedUserId有值；则表示查询视频发布者的信息，否则查询用户本身信息；
	 * @param userId
	 * @return 
	 */
	@ApiOperation(value="查询用户信息", notes="查询用户信息操作")
	@ApiImplicitParams({
		@ApiImplicitParam(value="用户id", name="userId", paramType="query", dataType="String", required=true),
		@ApiImplicitParam(value="关注者", name="followerId", paramType="query", dataType="String", required=false)
	})
	@PostMapping("/queryUserInfo")
	public JsonResult queryUserInfo(String userId, String followerId){
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
		usersVO.setIsFollow(usersService.isFollow(userId, followerId));
		return JsonResult.ok(usersVO);
	}

	/**
	 * @name uploadFaceImage 
	 * @Description 用户进行头像上传操作
	 * @param 用户id
	 * @param file
	 * @return  
	 * @throws IOException 
	 */
	@ApiOperation(value="头像上传", notes="用户进行头像上传操作")
	@ApiImplicitParam(value="用户id", name="userId", paramType="query", dataType="String", required=true)
	@PostMapping(value="/uploadFaceImage" , headers="content-type=multipart/form-data")
	public JsonResult uploadFaceImage(String userId, @RequestParam("file") MultipartFile[] file){
		//判断用户id是否为空
		if (StringUtils.isBlank(userId)) {
			return JsonResult.errorMsg("用户为空!");
		}
		//文件存放路径c:/imooc_video_dev
		String fileSpacePath="C:/imooc_video_dev";
		//数据库存放路径
		String fileDBPath="/"+userId+"/face";
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
			if(file !=null && file.length>0){
				//获取文件名称
			    fileName=file[0].getOriginalFilename();
				//判断文件名是否为空
				if (StringUtils.isNotBlank(fileName)) {
					//文件最终保存路径
					String finalName=fileSpacePath+fileDBPath+"/"+fileName;
					//新建一个file对象
					File outFile=new File(finalName);
					//判断文件夹是否存在，当文件夹不存在时，创建文件夹
					if (outFile.getParentFile()!=null || !outFile.getParentFile().isDirectory()) {
						outFile.getParentFile().mkdirs();
					}
					//将上传的文件转换成流
					InputStream inputStream=file[0].getInputStream();
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
		fileDBPath += ("/"+fileName);
		Users users =new Users();
		users.setId(userId);
		users.setFaceImage(fileDBPath);
		//将头像文件路径保存到数据库
		usersService.uploadFaceImage(users);
		//将用户记录查询出来
		Users user1=usersService.selectByPrimaryKey(users.getId());
		//查询用户对应的redis session
		String userSession=redisUtils.get("redis_user_session"+":"+user1.getId());
		System.out.println(userSession);
		UsersVO usersVO=new UsersVO();
		BeanUtils.copyProperties(users, usersVO);
		usersVO.setUserSession(userSession);
		
		return JsonResult.ok(usersVO);
	}
	
	/**
	 * @name queryPublisherInfo
	 * @Description 用户查看视频发布者的信息，将发布者的信息和用户和视频的是否点赞关系返回给前端 
	 * @param userId
	 * @param videoId
	 * @param publisherId
	 * @return 
	 */
	@ApiOperation(value="用户查询视频发布者信息", notes="用户进查询视频发布者的信息操作")
	@ApiImplicitParams({
		@ApiImplicitParam(value="用户id", name="userId", paramType="query", dataType="String", required=true),
		@ApiImplicitParam(value="视频id", name="videoId", paramType="query", dataType="String", required=true),
		@ApiImplicitParam(value="发布者id", name="publisherId", paramType="query", dataType="String", required=true),
	})
	@PostMapping("/queryPublishInfo")
	public JsonResult queryPublishInfo(String userId, String videoId, String publisherId){
		//判断发布者的id是否为空
		if (StringUtils.isBlank(publisherId) || StringUtils.isBlank(videoId)) {
			return JsonResult.errorMsg("");
		}
		//将user用户信息和用户和视频的关系查询出来，封装成新的对象，返回给前端
		Users users=usersService.queryUserInfo(publisherId);
		UsersVO usersVO=new UsersVO();
		BeanUtils.copyProperties(users, usersVO);
		Boolean isLike=usersService.isLike(userId, videoId);
		UsersVideoVO usersVideoVO=new UsersVideoVO();
		usersVideoVO.setUsersVO(usersVO);
		usersVideoVO.setIsLike(isLike);
		
		return JsonResult.ok(usersVideoVO);
	}
	
	/**
	 * @name followUsers
	 * @Description 用户进行关注其他用户
	 * @param userId
	 * @param followedUserId
	 * @return 
	 */
	@ApiOperation(value="关注用户操作", notes="用户进行关注其他用户")
	@ApiImplicitParams({
		@ApiImplicitParam(value="用户id", name="userId", paramType="query", dataType="String", required=true),
		@ApiImplicitParam(value="被关注者id", name="followedUserId", paramType="query", dataType="String", required=true)
	})
	@PostMapping("/followUsers")
	public JsonResult followUsers(String userId, String followedUserId){
		if (StringUtils.isBlank(userId) || StringUtils.isBlank(followedUserId)) {
			return JsonResult.errorMsg("");
		}
		usersService.saveFollowUsers(userId, followedUserId);
		return JsonResult.ok();
	}
	
	/**
	 * @name unfollowUsers
	 * @Description 用户进行取消关注其他用户
	 * @param userId
	 * @param followedUserId
	 * @return 
	 */
	@ApiOperation(value="取消关注用户操作", notes="用户进行取消关注其他用户")
	@ApiImplicitParams({
		@ApiImplicitParam(value="用户id", name="userId", paramType="query", dataType="String", required=true),
		@ApiImplicitParam(value="被关注者id", name="followedUserId", paramType="query", dataType="String", required=true)
	})
	@PostMapping("/unfollowUsers")
	public JsonResult unfollowUsers(String userId, String followedUserId){
		if (StringUtils.isBlank(userId) || StringUtils.isBlank(followedUserId)) {
			return JsonResult.errorMsg("");
		}
		usersService.deleteFollowUsers(userId,followedUserId);
		return JsonResult.ok();
	}
}