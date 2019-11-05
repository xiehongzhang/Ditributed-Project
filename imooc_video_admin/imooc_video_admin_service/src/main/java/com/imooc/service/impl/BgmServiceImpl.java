/**
* Title: BgmServiceImpl.java  

* Description   

* @author xhz  

* @date 2019年10月9日  
 
 */
package com.imooc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.n3r.idworker.Sid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.enums.BGMOperatorEnum;
import com.imooc.mapper.BgmMapper;
import com.imooc.pojo.Bgm;
import com.imooc.pojo.BgmExample;
import com.imooc.service.BgmService;
import com.imooc.service.utils.ZKCurator;
import com.imooc.utils.JsonUtils;

/**
 * @author xhz
 * @description 
 */
@Service
public class BgmServiceImpl implements BgmService {
	
	static final Logger log=LoggerFactory.getLogger(BgmServiceImpl.class);
	@Autowired
	private ZKCurator zkCurator;

	@Autowired
	private BgmMapper bgmMapper;
	
	@Autowired
	private Sid sid;
	
	@Override
	public List<Bgm> queryAllBgm() {
		BgmExample bgmExample=new BgmExample();
		return bgmMapper.selectByExample(bgmExample);
	}

	@Override
	public Bgm queryBgm(String id) {
		return bgmMapper.selectByPrimaryKey(id);
	}

	@Override
	public void addBgm(Bgm bgm) {
		String id=sid.nextShort();
		bgm.setId(id);
		bgmMapper.insert(bgm);
		//将操作信息和BGM的保存路径添加到节点去,并在zookeeper中添加节点
		Map<String, String> moperObjap=new HashMap<>();
		moperObjap.put("operType", BGMOperatorEnum.ADD.getValue());
		moperObjap.put("bgmPath",bgm.getPath());		
		//在zookeeper中添加节点
		zkCurator.setZNode(bgm.getId(),JsonUtils.objectToJson(moperObjap));
		
		//在zookeeper中添加节点
//		zkCurator.setZNode(bgm.getId(),BGMOperatorEnum.ADD.getValue());
	}

	@Override
	public void deleteBgm(String bgmId) {
		Bgm bgm = bgmMapper.selectByPrimaryKey(bgmId);
		bgmMapper.deleteByPrimaryKey(bgmId);
		//将操作信息和BGM的保存路径添加到节点去,并在zookeeper中添加节点
		Map<String, String> operObj=new HashMap<>();
		operObj.put("operType", BGMOperatorEnum.DELETE.getValue());
		operObj.put("bgmPath",bgm.getPath());		
		//在zookeeper中添加节点
		zkCurator.setZNode(bgm.getId(),JsonUtils.objectToJson(operObj));
		
		//在zookeeper中添加节点
//		zkCurator.setZNode(bgmId,BGMOperatorEnum.DELETE.getValue());
	}

}
