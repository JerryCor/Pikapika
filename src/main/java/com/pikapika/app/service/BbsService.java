package com.pikapika.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pikapika.app.entity.BbsEntity;
@Service
public interface BbsService {
	List<BbsEntity> searchAllBbs(String pageNum, String pageSize) throws Exception;
}
