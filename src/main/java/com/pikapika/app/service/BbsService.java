package com.pikapika.app.service;

import org.springframework.stereotype.Service;
@Service
public interface BbsService {
	String searchAllBbs(String pageNum, String pageSize) throws Exception;
}
