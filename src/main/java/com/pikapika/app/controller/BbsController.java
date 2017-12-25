package com.pikapika.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pikapika.app.service.BbsService;

@Controller
@RequestMapping("/pikapika")
public class BbsController {
	@Autowired
	private BbsService bbsService; 
	
	@RequestMapping(value="bbs", method = RequestMethod.GET)
	@ResponseBody
	public String searchAllBbs(){
		try {
			return bbsService.searchAllBbs("1", "5");
		} catch (Exception e) {
			return "";
		}
	}
}
