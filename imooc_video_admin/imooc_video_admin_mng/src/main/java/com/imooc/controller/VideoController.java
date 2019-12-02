/**
* Title: VideoController.java  

* Description   

* @author xhz  

* @date 2019年10月9日  
 
 */
package com.imooc.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.StringUtil;
import com.imooc.enums.VideoStatusEnum;
import com.imooc.pojo.Bgm;
import com.imooc.pojo.UserReport;
import com.imooc.pojo.Video;
import com.imooc.service.BgmService;
import com.imooc.service.VideoService;
import com.imooc.utils.JsonResult;
import com.imooc.utils.PageResult;

/**
 * @author xhz
 * @description video控制层
 */
@RequestMapping("video")
@Controller
public class VideoController extends BasicController{

	@Autowired
	private VideoService videoService;
	
	@Autowired
	private BgmService bgmService;
	
	//注入资源文件sources.properties,文件上传的命名空间
	@Value("${FILE_NAMESPACE}")
	private String FILE_NAMESPACE;
	
	/**
	 * @name showVideo
	 * @Description 跳转到视频列表页
	 * @param 
	 * @return 
	 */
	@GetMapping("/showVideo")
	public String showVideo(){
//		ModelAndView modelAndView =new ModelAndView();
//		modelAndView.setViewName("video/list");
//		return modelAndView;
		return "video/list";
	}
	
	/**
	 * @name showVideo
	 * @Description 显示视频列表
	 * @param 
	 * @return 
	 */
	@PostMapping("/queryVideoList")
	@ResponseBody
	public PageResult showAllVideo(Video video , Integer page){
		if (page == null) {
			page = 1;
		}
		PageResult pageResult=videoService.queryVideo(video , page , PAGE_SIZE);
		return pageResult;
	} 
	
	/**
	 * @name forbidVideo
	 * @Description 禁止视频播放
	 * @param videoId
	 * @return 
	 */
	@PostMapping("/forbidVideo")
	@ResponseBody
	public JsonResult forbidVideo(String videoId){
		videoService.updateVideoStatus(videoId,VideoStatusEnum.FORBID.value);
		return JsonResult.ok();
	}
	
	/**
	 * @name showAddBgm
	 * @Description 跳到添加BGM页面
	 * @param 
	 * @return 
	 */
	@GetMapping("/showAddBgm")
	public String showAddBgm(){
//		ModelAndView modelAndView =new ModelAndView();
//		modelAndView.setViewName("video/addBgm");
//		return modelAndView;
		return "video/addBgm";
	}
	
	/**
	 * @name bgmUpload
	 * @Description 上传BGM文件
	 * @param file
	 * @return 
	 * @throws IOException 
	 */
	@PostMapping("/bgmUpload")
	@ResponseBody
	public JsonResult showAddBgm(@RequestParam("file") MultipartFile[] file) throws IOException{
//		//文件上传的命名空间
//		String fileNamespace="imooc_video_admin";
//		String fileNamespace="C:"+File.separator+"imooc_video_admin";
		//数据库保存路径
		String fileDBPath=File.separator+"bgm";
		//文件名称
		String fileName=null;
		//字节输入流和字节输出流
		BufferedInputStream bInputStream=null;
		BufferedOutputStream bOutputStream=null;
		//判断文件是否为空
		try {
			if (file != null && file.length>0) {
				for(int i=0; i<file.length; i++){
					//获取文件的名称
					fileName=file[i].getOriginalFilename();
					//判断文件是否为空 
					if (StringUtil.isNotEmpty(fileName)) {
						//文件要保存的完整路径
						String fileString=FILE_NAMESPACE+fileDBPath+File.separator+fileName;
						//数据库保存的完整路径
						fileDBPath +=File.separator+fileName;
						//新建一个文件
						File newFile=new File(fileString);
						//在文件系统中创建
						if(newFile.getParentFile()!=null && newFile.getParentFile().isDirectory()){
							newFile.getParentFile().mkdirs();
						}
						//获取文件输入流
						bInputStream=new BufferedInputStream(file[i].getInputStream());
						//实例一个文件输出流
						bOutputStream=new BufferedOutputStream(new FileOutputStream(newFile));
						//将输入流复制到输出流
						IOUtils.copy(bInputStream, bOutputStream);
					}
				}
			}else{
				return  JsonResult.errorMsg("文件上传出错！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭流
			if (bOutputStream != null) {
				bOutputStream.flush();
				bOutputStream.close();
			}
		}
		return JsonResult.ok(fileDBPath);
	}
	
	/**
	 * @name addBgm
	 * @Description 添加bgm,将BGM的信息写进数据库，同时向zookeeper写进一个带有增加BGM标志的节点
	 * @param bgm
	 * @return 
	 */
	@PostMapping("/addBgm")
	@ResponseBody
	public JsonResult addBgm(Bgm bgm){
		//保存BGM
		bgmService.addBgm(bgm);
		return JsonResult.ok();
	}
	
	/**
	 * @name deleteBgm
	 * @Description 删除BGM，同时向zookeeper写进一个带有删除标志的节点
	 * @param bgmId
	 * @return 
	 */
	@PostMapping("/delBgm")
	@ResponseBody
	public JsonResult  deleteBgm(String bgmId){
		if (StringUtils.isEmpty(bgmId)) {
			return JsonResult.errorMsg("bgmId为空");
		}
		//删除BGM
		bgmService.deleteBgm(bgmId);
		return JsonResult.ok();
	}
	
	/**
	 * @name showBgmList
	 * @Description 跳转BGM列表
	 * @param 
	 * @return 
	 */
	@GetMapping("/showBgmList")
    public String showBgmList(){
//		ModelAndView modelAndView=new ModelAndView();
//		modelAndView.setViewName("video/bgmList");
//		return modelAndView;
    	return "video/bgmList";		
    }
	
	/**
	 * @name queryBgmList
	 * @Description 显示BGM列表
	 * @param 
	 * @return 
	 */
	@PostMapping("/queryBgmList")
	@ResponseBody
    public PageResult queryBgmList(Integer page){
		if (page == null) {
			page=1;
		}
    	PageResult pageResult=bgmService.queryAllBgm(page,PAGE_SIZE);
    	return pageResult;		
    }
	
	/**
	 * @name showReportList
	 * @Description 跳转到reportList
	 * @param 
	 * @return 
	 */
	@GetMapping("/showReportList")
	public String showReportList(){
//		ModelAndView modelAndView =new ModelAndView();
//		modelAndView.setViewName("video/reportList");
//		return modelAndView;
		return "video/reportList";
	}
	
	/**
	 * @name reportList
	 * @Description 查询用户的举报列表
	 * @param 
	 * @return 
	 */
	@PostMapping("/reportList")
	@ResponseBody
	public PageResult reportList(UserReport userReport, Integer page){
		if (page == null) {
			page=1;
		}
		PageResult pageResult=videoService.queryReportList(userReport, page, PAGE_SIZE);
		return pageResult;
	}
}
