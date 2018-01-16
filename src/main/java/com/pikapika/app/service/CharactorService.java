package com.pikapika.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pikapika.app.entity.CharactorEntity;
@Service
public interface CharactorService {
	/**
	 * 查询所有角色信息
	 * @return 角色列表
	 * @throws Exception
	 */
	List<CharactorEntity> searchAllCharactors() throws Exception;
	/**
	 * 查询角色通过角色ID
	 * @param cId 角色ID
	 * @return 角色对象
	 */
	CharactorEntity searchCharactorById(String cId);
	/**
	 * 更新角色
	 * @param charactorEntity 更新对象
	 */
	void updateForCharactor(CharactorEntity charactorEntity);
}
