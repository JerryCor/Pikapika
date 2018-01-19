package com.pikapika.app.mapper;

import java.util.List;

import com.pikapika.app.entity.BbsEntity;
public interface BbsMapper {
	/**
	 * 查询所有帖子
	 * @return 帖子列表
	 */
	List<BbsEntity> getAll();
	/**
	 * 获取帖子通过帖子Id
	 * @param bbs
	 * @return 帖子
	 */
	BbsEntity getBbsById(String bbsId);
	/**
	 * 添加帖子
	 * @param bbs
	 */
	void insertBbs(BbsEntity bbs);
	/**
	 * 更新帖子
	 * @param bbs
	 */
	void updateBbs(BbsEntity bbs);
	/**
	 * 删除帖子通过帖子Id
	 * @param bbs
	 */
	void deleteBbsById(BbsEntity bbs);
}
