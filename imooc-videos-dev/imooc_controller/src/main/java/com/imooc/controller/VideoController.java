/**
* Title: VideoController.java  

* Description   

* @author xhz  

* @date 2019年8月26日  
 
 */
package com.imooc.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.imooc.BasicController;
import com.imooc.enums.VideoStatuEnum;
import com.imooc.pojo.Bgm;
import com.imooc.pojo.Comments;
import com.imooc.pojo.UserReport;
import com.imooc.pojo.Users;
import com.imooc.pojo.Video;
import com.imooc.pojo.vo.VideoVO;
import com.imooc.service.BgmService;
import com.imooc.service.UsersService;
import com.imooc.service.VideoService;
import com.imooc.utils.FFMpegUtils;
import com.imooc.utils.JsonResult;
import com.imooc.utils.PageResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author xhz
 * @description 主要包括用户对视频的各种操作，上传和下载等；
 */
/**
 * @author 
 * @description 
 */
@Api(value="用户操作视频接口",tags="视频操作接口")
@RestController
@RequestMapping("/video")
public class VideoController extends BasicController{
	
	@Autowired
	private VideoService videoService;
	
	@Autowired
	private BgmService bgmService;
	
	@Autowired
	private Sid sid=null;
	
	@Autowired
	private UsersService usersService;
	
	/**
	 * @name uploadVideo
	 * @Description 用户上传video文件,保存到数据库中去
	 * @param userId
	 * @param file
	 * @param bgmId
	 * @param desc
	 * @param videoSeconds
	 * @param videoWidth
	 * @param videoHeight
	 * @return 
	 * @throws IOException 
	 */
	@ApiOperation(value="上传视频",notes="用户上传视频操作")
	@ApiImplicitParams({
		@ApiImplicitParam(value="用户id", name="userId", paramType="query", dataType="String", required=true),
		@ApiImplicitParam(value="背景音乐id", name="audioId", paramType="query", dataType="String", required=false),
		@ApiImplicitParam(value="视频描述", name="desc", paramType="query", dataType="String", required=false),
		@ApiImplicitParam(value="秒数", name="videoSeconds", paramType="query", dataType="String", required=true),
		@ApiImplicitParam(value="长度", name="videoWidth", paramType="query", dataType="String", required=true),
		@ApiImplicitParam(value="高度", name="videoHeight", paramType="query", dataType="String", required=true),
	})
	@PostMapping(value="/uploadVideo", headers="content-type=multipart/form-data")
	public JsonResult uploadVideo(String userId, String audioId, @ApiParam(value="file",allowEmptyValue=false) MultipartFile file, 
								  String desc, float videoSeconds, 
								  int videoWidth, int videoHeight) throws IOException{
		//判断用户id是否为空
		if (StringUtils.isEmpty(userId)) {
			return JsonResult.errorMsg("用户id不能为空");
		}
		//文件数据库保存路径
		String fileDBPath="/"+userId+"/video";
		//文件完整路径名
		String filePath=null;
		//文件名称
		String fileName=null;
		//视频封面的数据库保存路径
		String coverDBPath=null;
		//封面的名称
		String coverName=null;
		//封面完整路径名
		String coverTarget=null;
		//字节缓冲输入流
		BufferedInputStream bufferedInputStream=null;
		//字节缓冲输出流
		BufferedOutputStream bufferedOutputStream=null;
		//判断文件是否为空
		try {
			if (file != null) {
				//获取文件名称
				fileName=file.getOriginalFilename();
				//判断文件名是否为空，文件大小是否大于0
				if (StringUtils.isNotEmpty(fileName)) {
					//拼接字符串
					filePath=FILE_NAMESPACE+fileDBPath+"/"+fileName;
					String coverNamePrefix=fileName.split("\\.")[0];
					coverName=coverNamePrefix + ".jpg";
					coverDBPath=fileDBPath+"/"+coverName;
					coverTarget=FILE_NAMESPACE+coverDBPath;
					//创建file
					File outFile=new File(filePath);
					//判断outFile的上一级目录是否存在
					if (outFile.getParentFile() != null || outFile.getParentFile().isDirectory()) {
						outFile.getParentFile().mkdirs();
					}
					//获取文件输入流
					bufferedInputStream=new BufferedInputStream(file.getInputStream());
					//获取文件输出流
					bufferedOutputStream=new BufferedOutputStream(new FileOutputStream(outFile));
					//复制文件
					IOUtils.copy(bufferedInputStream, bufferedOutputStream);
				}else{
					return JsonResult.errorMsg("上传文件内容为空");
				}
			}else{
				return JsonResult.errorMsg("文件不能为空");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			//关闭流
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
		//截取视频封面
		FFMpegUtils.fetchVideoCover(filePath, coverTarget);
		//判断audioId是否为空，
		if (StringUtils.isNotEmpty(audioId)) {
			//查询背景音乐的所有信息
			Bgm bgm=bgmService.queryBgmById(audioId);
			String videoSource=filePath;
			String audioSource=FILE_NAMESPACE+bgm.getPath();
			String target=FILE_NAMESPACE+fileDBPath+"/"+"new"+"/"+fileName;
			//合并背景音乐和短视频
			FFMpegUtils.mergeVideoAndAudio(videoSource, audioSource, videoSeconds, target);
		}
		//保存视频信息
		String id=sid.nextShort();
		fileDBPath += "/"+fileName;
		Video video = new Video();
		video.setId(id);
		video.setUserId(userId);
		video.setAudioId(audioId);
		video.setVideoDesc(desc);
		video.setVideoSeconds(videoSeconds);
		video.setVideoWidth(videoWidth);
		video.setVideoHeight(videoHeight);
		video.setVideoPath(fileDBPath);
		video.setCoverPath(coverDBPath);
		video.setLikeCounts(0L);
		video.setStatus(VideoStatuEnum.NORNAL.getValue());
		video.setCreateTime(new Date());
		//将视频的信息保存到数据库中
		videoService.saveVideoInfo(video);
		return JsonResult.ok();
	}
	
	/**
	 * @name showAll
	 * @Description 查询所有视频，当isSaveRecord为1：保存查询的的内容导数据库中；为0则不保存
	 * @param video
	 * @param isSaveRecord
	 * @param pageNum
	 * @return 
	 */
	@ApiOperation(value="查询所有视频", notes="查询所有视频")
	@ApiImplicitParams({
		@ApiImplicitParam(value="查询的内容", name="video", paramType="query", dataType="String", required=true),
		@ApiImplicitParam(value="是否保存记录", name="isSaveRecord", paramType="query", dataType="String", required=true),
		@ApiImplicitParam(value="当前页页数", name="pageNum", paramType="query", dataType="String", required=true)
	})
	@PostMapping("/showAll")
	public JsonResult showAll(@RequestBody Video video, Integer isSaveRecord, Integer pageNum, Integer pageSize){
		if (pageNum == null) {
			pageNum=1;
		}
		if (pageSize == null) {
			pageSize = PAGE_SIZE;
		}
		PageResult pageResult=videoService.queryAllVideo(video, isSaveRecord, pageNum, pageNum);
		return JsonResult.ok(pageResult);
	}
	
	/**
	 * @name hot
	 * @Description 查询热搜词
	 * @param 
	 * @return 
	 */
	@ApiOperation(value="查询热搜词", notes="查询热搜词")
	@PostMapping("/hot")
	public JsonResult hot(){ 
		return JsonResult.ok(videoService.queryHotRecords());
	} 
	
	/**
	 * @name likeVideo
	 * @Description 用户进行点赞
	 * @param userId
	 * @param videoId
	 * @param videoOwnerId
	 * @return 
	 */
	@ApiOperation(value="点赞视频", notes="用户进行点赞")
	@ApiImplicitParams({
		@ApiImplicitParam(value="用户id", name="userId", paramType="query", dataType="String", required=true ),
		@ApiImplicitParam(value="视频id", name="videoId", paramType="query", dataType="String", required=true ),
		@ApiImplicitParam(value="视频发布者id", name="videoOwnerId", paramType="query", dataType="String", required=true ),
	})
	@PostMapping("/likeVideo")
	public JsonResult likeVideo(String userId, String videoId, String videoOwnerId){
		videoService.saveLikeVideo(userId, videoId, videoOwnerId);
		return JsonResult.ok();
	}
	
	/**
	 * @name likeVideo
	 * @Description 用户取消点赞
	 * @param userId
	 * @param videoId
	 * @param videoOwnerId
	 * @return 
	 */
	@ApiOperation(value="取消点赞", notes="用户进行取消点赞")
	@ApiImplicitParams({
		@ApiImplicitParam(value="用户id", name="userId", paramType="query", dataType="String", required=true ),
		@ApiImplicitParam(value="视频id", name="videoId", paramType="query", dataType="String", required=true ),
		@ApiImplicitParam(value="视频发布者id", name="videoOwnerId", paramType="query", dataType="String", required=true ),
	})
	@PostMapping("/unlikeVideo")
	public JsonResult unlikeVideo(String userId, String videoId, String videoOwnerId){
		videoService.saveUnlikeVideo(userId, videoId, videoOwnerId);
		return JsonResult.ok();
	}
	
	/**
	 * @name showLikeVideo
	 * @Description 按条件查询用户收藏的视频
	 * @param userId
	 * @param pageNum
	 * @param pageSize
	 * @return 
	 */
	@ApiOperation(value="查询收藏的视频", notes="查询用户收藏的所有视频")
	@ApiImplicitParams({
		@ApiImplicitParam(value="用户id", name="userId", paramType="query", dataType="String", required=true),
		@ApiImplicitParam(value="当前页页数", name="pageNum", paramType="query", dataType="String", required=false),
		@ApiImplicitParam(value="每页显示的记录数", name="pageSize", paramType="query", dataType="String", required=false)
	})
	@PostMapping("/showLikeVideo")
	public JsonResult showLikeVideo(String userId, Integer pageNum, Integer pageSize){
		if (pageNum == null) {
			pageNum=1;
		}
		if (pageSize == null) {
			pageSize=PAGE_SIZE;
		}
		List<VideoVO> videoVOList=videoService.queryLikeVideo(userId, pageNum, pageSize);
		return JsonResult.ok(videoVOList);
	}
	
	/**
	 * @name showFollowVideo
	 * @Description 按条件查询用户关注过的所有用户
	 * @param userId
	 * @param pageNum
	 * @param pageSize
	 * @return 
	 */
	@ApiOperation(value="查询关注的用户", notes="查询关注的所有用户")
	@ApiImplicitParams({
		@ApiImplicitParam(value="用户id", name="userId", paramType="query", dataType="String", required=true),
		@ApiImplicitParam(value="当前页页数", name="pageNum", paramType="query", dataType="String", required=false),
		@ApiImplicitParam(value="每页显示的记录数", name="pageSize", paramType="query", dataType="String", required=false)
	})
	@PostMapping("/showFollowUser")
	public JsonResult showFollowUser(String userId, Integer pageNum, Integer pageSize){
		if (pageNum == null) {
			pageNum=1;
		}
		if (pageSize == null) {
			pageSize=PAGE_SIZE;
		}
		List<Users> userList=usersService.queryFollowUser(userId, pageNum, pageSize);
		return JsonResult.ok(userList);
	}
	
	/**
	 * @name reportVideo
	 * @Description 保存用户举报的相关信息
	 * @param userReport
	 * @return 
	 */
	@ApiOperation(value="举报视频", notes="用户进行举报操作")
	@PostMapping("/reportVideo")
	public JsonResult reportVideo(@RequestBody  UserReport userReport){
		usersService.saveUserReport(userReport);
		return JsonResult.ok();
	}
	
	/**
	 * @name commentVideo
	 * @Description 评论视频操作
	 * @param comments
	 * @return List<CommentsVO>
	 */
	@ApiOperation(value="评论视频", notes="用户进行评论视频")
	@PostMapping("/commentVideo")
	public JsonResult commentVideo(@RequestBody Comments comments){
		videoService.saveComments(comments);
		return JsonResult.ok();
	}
	
	/**
	 * @name showAllComments
	 * @Description 查询所有的评论以及相关的用户昵称
	 * @param comments
	 * @param pageNum
	 * @param pageSize
	 * @return 
	 */
	@ApiOperation(value="查询评论", notes="用户进行查询所有的评论")
	@ApiImplicitParams({
		@ApiImplicitParam(value="当前视频id", name="videoId", paramType="query", dataType="String", required=false),
		@ApiImplicitParam(value="当前页码", name="pageNum", paramType="query", dataType="String", required=false),
		@ApiImplicitParam(value="每页显示的记录数", name="pageSize", paramType="query", dataType="String", required=false)
	})
	@PostMapping("/showAllComments")
	public JsonResult showAllComments(String videoId, Integer pageNum, Integer pageSize){
		if(StringUtils.isBlank(videoId)){
			return JsonResult.errorMsg("视频id为空");
		}
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = PAGE_SIZE;
		}
		//查询所有评论
		PageResult pageResult=videoService.queryAllComments(videoId, pageNum, pageSize);
		return  JsonResult.ok(pageResult);
	}
}
