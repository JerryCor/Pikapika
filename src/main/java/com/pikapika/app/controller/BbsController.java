package com.pikapika.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.pikapika.app.service.BbsService;

@Controller
@RequestMapping("/pikapika")
public class BbsController {
	@Autowired
	private BbsService bbsService; 
	
/*	@Autowired
	private RedisService redisService;*/
	
	@RequestMapping(value="bbs", method = RequestMethod.GET)
	public ModelAndView searchAllBbs(){
		ModelAndView view = new ModelAndView("index");
		try {
			PageHelper.startPage(Integer.valueOf("1"), Integer.valueOf("5"));
			view.addObject("list", bbsService.searchAllBbs());
		} catch (Exception e) {
		}
		return view;
	}
}
