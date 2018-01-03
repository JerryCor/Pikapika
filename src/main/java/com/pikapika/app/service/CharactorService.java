package com.pikapika.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pikapika.app.entity.CharactorEntity;
@Service
public interface CharactorService {
	List<CharactorEntity> searchAllCharactors(String pageNum, String pageSize) throws Exception;
	CharactorEntity searchCharactorById(String cId);
	void updateForCharactor(CharactorEntity charactorEntity);
}
