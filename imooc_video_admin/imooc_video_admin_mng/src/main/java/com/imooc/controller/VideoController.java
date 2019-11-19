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
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.StringUtil;
import com.imooc.pojo.Bgm;
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
public class VideoController {

	@Autowired
	private VideoService videoService;
	
	@Autowired
	private BgmService bgmService;
	
	/**
	 * @name showAddBgm
	 * @Description 上传BGM文件
	 * @param file
	 * @return 
	 * @throws IOException 
	 */
	@PostMapping("/showAddBgm")
	@ResponseBody
	public JsonResult showAddBgm(@RequestParam("file") MultipartFile[] file) throws IOException{
		//文件上传的命名空间
//		String fileNamespace=File.separator+"imooc_video_admin";
		String fileNamespace="C:"+File.separator+"imooc_video_admin";
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
						String fileString=fileNamespace+fileDBPath+File.separator+fileName;
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
	@PostMapping("/deleteBgm")
	@ResponseBody
	public JsonResult  deleteBgm(String bgmId){
		if (StringUtils.isEmpty(bgmId)) {
			return JsonResult.errorMsg("bgmId为空");
		}
		//删除BGM
		bgmService.deleteBgm(bgmId);
		return JsonResult.ok();
	}
	
	
	@GetMapping("/showVideo")
	public String showAllVideo(ModelMap modelMap){
		List<Video> lists=videoService.queryVideo();
		modelMap.put("lists", lists);
		return "video/list";
	}
	
	/**
	 * @name showBgmList
	 * @Description 显示BGM列表
	 * @param 
	 * @return 
	 */
	@GetMapping("/showBgmList")
    public String showBgmList(){
    	return "video/bgmList";		
    }
	
	/**
	 * @name queryBgmList
	 * @Description 显示BGM列表
	 * @param 
	 * @return 
	 */
	@PostMapping("/queryBgmList")
    public JsonResult queryBgmList(Integer page){
		if (page == null) {
			page=1;
		}
    	PageResult pageResult=bgmService.queryAllBgm(page,10);
    	return JsonResult.ok(pageResult);		
    }
	
}
