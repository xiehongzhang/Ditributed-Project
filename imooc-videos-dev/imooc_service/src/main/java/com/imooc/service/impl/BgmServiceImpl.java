/**
* Title: BgmServiceImpl.java  

* Description   

* @author xhz  

* @date 2019年9月6日  
 
 */
package com.imooc.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.dao.BgmMapper;
import com.imooc.pojo.Bgm;
import com.imooc.pojo.BgmExample;
import com.imooc.service.BgmService;

/**
 * @author 
 * @description 
 */
@Service
public class BgmServiceImpl implements BgmService {
	@Autowired
	private BgmMapper bgmMapper;
	
	@Override
	public List<Bgm> queryBgmList() {
		BgmExample bgmExample=new BgmExample();
		List<Bgm> bgm=bgmMapper.selectByExample(bgmExample);
		return bgm;
	}

	@Override
	public Bgm queryBgmById(String audioId) {
		return bgmMapper.selectByPrimaryKey(audioId);
	}

}
