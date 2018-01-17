package com.pikapika.app.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pikapika.app.entity.BbsEntity;
import com.pikapika.app.entity.CharactorEntity;
import com.pikapika.app.service.BbsService;
import com.pikapika.app.service.CharactorService;
import com.pikapika.app.service.RedisService;
import com.pikapika.app.util.PikapikaConstants;

@Controller
@RequestMapping("/pikapika")
public class PikapikaController {
	
	private static Logger logger =LoggerFactory.getLogger(PikapikaController.class);
	
	@Autowired
	private CharactorService charactorService;
	
	@Autowired
	private BbsService bbsService;
	
	@Autowired
	private RedisService redisService;
	
	@Value("${pikapika.pageNub}")
	private String pageNub;
	
	@Value("${pikapika.pageSize}")
	private String pageSize;
	
	/**
	 * 请求主页
	 * @param request
	 * @return 主页
	 * @throws Exception
	 */
	@RequestMapping(value="index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) throws Exception{
		logger.info("正在访问PIKAPIKA......");
		String jsessionId = request.getSession().getId();
		if(!redisService.isExist(jsessionId)
				&& jsessionId!= null){
			redisService.set(jsessionId, "user1", 30, TimeUnit.MINUTES);
		}
		logger.info("正在访问PIKAPIKA主页......");
		ModelAndView view = new ModelAndView("pikapika");
		//查询角色
		List<CharactorEntity> entities = charactorService.searchAllCharactors();
		logger.info("查询所有角色 总共查到"+entities.size()+"位角色......");
		
		//查询帖子-分页（1，5）
		PageHelper.startPage(PikapikaConstants.INT_NUB_1, PikapikaConstants.INT_NUB_5);
		List<BbsEntity> bbsList = bbsService.searchAllBbs();
		PageInfo<BbsEntity> pageInfo = new PageInfo<>(bbsList);
		logger.info("查询所有帖子-当前页数："+pageInfo.getPageNum()
		+" 每页展示条数："+pageInfo.getPageSize()+" 总共页数:"+pageInfo.getPages()+" 条数："+pageInfo.getTotal());
		view.addObject("charactorsInfo", entities);
		view.addObject("activeModel", "index");
		return view;
	}
}
