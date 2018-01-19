package com.pikapika.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pikapika.app.entity.BbsEntity;
import com.pikapika.app.mapper.BbsMapper;
import com.pikapika.app.service.BbsService;

@Service
public class BbsServiceImpl implements BbsService {
	
	@Autowired
	private BbsMapper bbsMapper;

	@Override
	public List<BbsEntity> searchAllBbs() throws Exception{
		List<BbsEntity> bbsList = bbsMapper.getAll();
		if(bbsList!= null
			&& !bbsList.isEmpty()){
			return bbsList;
		}
		return null;
	}

	@Override
	public void postBbs(BbsEntity bbs) throws Exception {
		bbsMapper.insertBbs(bbs);
	}

	@Override
	public BbsEntity searchBbsById(String bbsId) throws Exception {
		return bbsMapper.getBbsById(bbsId);
	}

}
