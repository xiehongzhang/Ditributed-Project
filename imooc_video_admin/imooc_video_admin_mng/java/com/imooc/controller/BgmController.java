/**
* Title: BgmController.java  

* Description   

* @author xhz  

* @date 2019年10月9日  
 
 */
package com.imooc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.imooc.pojo.Bgm;
import com.imooc.service.BgmService;

/**
 * @author xhz
 * @description 后台管理系统的视频控制类
 */
@Controller
@RequestMapping("bgm")
public class BgmController {
	
	@Autowired 
	private BgmService bgmService;
	
    /**
     * @name hello
     * @Description 作为测试类
     * @param 
     * @return 
     */
    @GetMapping("/index")
	public String hello(){
		return "index";
	}
    
    @GetMapping("/showBgm")
    public String showBgm(ModelMap modelMap){
    	System.out.println(1);
    	List<Bgm> lists=bgmService.queryAllBgm();
    	System.out.println(1);
    	modelMap.put("lists",lists);
    	System.out.println(1);
    	return "bgm/list";		
    }
}
