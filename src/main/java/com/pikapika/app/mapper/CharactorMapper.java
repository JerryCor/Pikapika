package com.pikapika.app.mapper;

import java.util.List;

import com.pikapika.app.entity.CharactorEntity;

public interface CharactorMapper {
	/**
	 * 查询船员
	 * @return 船员列表
	 */
	List<CharactorEntity> getAllCharactors(); 
	/**
	 * 查询船员通过船员Id
	 * @param cId
	 * @return 船员
	 */
	CharactorEntity getCharactorById(String cId);
	/**
	 * 更新船员信息
	 * @param charactorEntity 船员信息
	 */
	void updateCharactor(CharactorEntity charactorEntity);
}
