package com.pikapika.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pikapika.app.entity.BbsEntity;
@Service
public interface BbsService {
	/**
	 * 搜寻所有帖子
	 * @param pageNum 页数
	 * @param pageSize 每页条数
	 * @return 帖子列表
	 * @throws Exception
	 */
	List<BbsEntity> searchAllBbs() throws Exception;
	
	/**
	 * 搜寻指定帖子
	 * @param bbsId 帖子ID
	 * @return 帖子对象
	 * @throws Exception
	 */
	BbsEntity searchBbsById(String bbsId) throws Exception;
	
	/**
	 * 发布帖子
	 * @param bbs 帖子对象
	 * @throws Exception
	 */
	void postBbs(BbsEntity bbs) throws Exception;
}
