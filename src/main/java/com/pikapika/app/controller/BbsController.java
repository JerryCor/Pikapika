package com.pikapika.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.pikapika.app.entity.BbsEntity;
import com.pikapika.app.service.BbsService;

@Controller
@RequestMapping("/pikapika")
public class BbsController {
	
	private static Logger logger =LoggerFactory.getLogger(UeditorController.class);
	
	@Autowired
	private BbsService bbsService; 
	
/*	@Autowired
	private RedisService redisService;*/
	/**
	 * 请求论坛主页
	 * @return 对应页面
	 */
	@RequestMapping(value="bbs", method = RequestMethod.GET)
	public ModelAndView searchAllBbs(){
		ModelAndView view = new ModelAndView("bbs/bbsMenu");
		try {
			PageHelper.startPage(Integer.valueOf("1"), Integer.valueOf("20"));
			view.addObject("bbsList", bbsService.searchAllBbs());
			view.addObject("activeModel", "bbs");
		} catch (Exception e) {
		}
		return view;
	}
	/**
	 * 请求发表帖子页面
	 * @return 对应页面
	 */
	@RequestMapping(value="bbs/addition", method = RequestMethod.GET)
	public ModelAndView addBbs(){
		ModelAndView view = new ModelAndView("bbs/addBbs");
		view.addObject("activeModel", "bbs");
		return view;
	}
	
	/**
	 * 请求发表帖子
	 * @return 对应页面
	 */
	@RequestMapping(value="bbs/addition", method = RequestMethod.POST)
	public ModelAndView postBbs(BbsEntity bbs){
		logger.info("开始将帖子内容存入数据库......");
		ModelAndView view = new ModelAndView("bbs/addBbsSuccess");
		bbs.setUaccountId("zxj123");
		try {
			bbsService.postBbs(bbs);
			logger.info("帖子内容存入数据库成功......");
		} catch (Exception e) {
			logger.error("帖子内容存入数据库失败......");
			logger.error(e.getMessage());
		}
		return view;
	}
	
	/**
	 * 请求单个帖子
	 * @return 对应页面
	 */
	@RequestMapping(value="bbs/bbsList/{bbsId}", method = RequestMethod.GET)
	public ModelAndView addBbs(@PathVariable("bbsId") String bbsId){
		if(bbsId!= null){
			logger.info("开始查找帖子： 帖子Id--->"+bbsId+" ......");
			ModelAndView view = new ModelAndView("bbs/bbsDetails");
			try {
				BbsEntity bbs = bbsService.searchBbsById(bbsId);
				view.addObject("bbs", bbs);
			} catch (Exception e) {
				logger.error("查找帖子（帖子Id--->"+bbsId+"）失败......");
				logger.error(e.getMessage());
			}
			return view;
		}else{
			logger.info("没有接收到帖子Id，跳转至杂谈主页......");
			return new ModelAndView("redirect:bbs/bbs");
		}
	}
}
