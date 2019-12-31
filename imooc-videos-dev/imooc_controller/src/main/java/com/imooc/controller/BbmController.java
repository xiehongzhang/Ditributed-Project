///**
//* Title: BbmController.java  
//
//* Description:   
//
//* @author xhz  
//
//* @date 2019年8月1日  
// 
// */
//package com.imooc.controller;
//
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.imooc.pojo1.Bgm;
//import com.imooc.service.BgmService;
//import com.imooc.utils.JsonResult;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//
///**
// * @author JoyPlay
// *
// */
//@Api(value="Bgm接口",tags={"背景音乐操作接口"})
//@RestController
//public class BbmController {
//	@Autowired
//	private BgmService bgmService;
//	
//	/**
//	 * @name queryBgmList
//	 * @Description 查询Bgm列表
//	 * @param 
//	 * @return 
//	 */
//	@ApiOperation(value="查询bgm列表",notes="查询BGM列表操作")
//	@RequestMapping(value="/bgms",method=RequestMethod.GET)
//	public JsonResult list(){
//		List<Bgm> bgm=bgmService.queryBgmList();
//		return JsonResult.ok(bgm);
//	}
//
//}
