package com.pikapika.app.mapper;

import java.util.List;

import com.pikapika.app.entity.CharactorEntity;

public interface CharactorMapper {
	public List<CharactorEntity> getAllCharactors(); 
	
	public CharactorEntity getCharactorById(String cId);
	
	public void updateCharactor(CharactorEntity charactorEntity);
}
