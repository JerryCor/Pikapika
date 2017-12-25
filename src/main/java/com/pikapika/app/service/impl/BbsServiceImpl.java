package com.pikapika.app.service.impl;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
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
	public String searchAllBbs(String pageNum, String pageSize) throws Exception{
		List<BbsEntity> bbsList = bbsMapper.getAll();
		String result = "";
		if(bbsList!= null
			&& !bbsList.isEmpty()){
			ObjectMapper mapper = new ObjectMapper();
				result = mapper.writeValueAsString(bbsList);
		}
		return result;
	}

}
