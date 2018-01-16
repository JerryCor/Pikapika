package com.pikapika.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pikapika.app.entity.CharactorEntity;
import com.pikapika.app.mapper.CharactorMapper;
import com.pikapika.app.service.CharactorService;

@Service
public class CharactorServiceImpl implements CharactorService {
	
	@Autowired
	private CharactorMapper charactorMapper;

	@Override
	public List<CharactorEntity> searchAllCharactors() throws Exception {
		List<CharactorEntity> charactorEntities = charactorMapper.getAllCharactors();
		if(charactorEntities!= null
				&& !charactorEntities.isEmpty()){
				return charactorEntities;
			}
		return null;
	}

	@Override
	public CharactorEntity searchCharactorById(String cId) {
		return charactorMapper.getCharactorById(cId);
	}

	@Override
	public void updateForCharactor(CharactorEntity charactorEntity) {
		charactorMapper.updateCharactor(charactorEntity);
	}


}
