package com.pikapika.app.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pikapika.app.entity.BbsEntity;
import com.pikapika.app.entity.CharactorEntity;
import com.pikapika.app.service.CharactorService;
import com.pikapika.app.service.RedisService;

@Controller
@RequestMapping("/pikapika")
public class PikapikaController {
	
	@Autowired
	private CharactorService charactorService;
	
	@Autowired
	private RedisService redisService;
	
	@RequestMapping(value="index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) throws Exception{
		String jsessionId = request.getSession().getId();
		if(!redisService.isExist(jsessionId)
				&& jsessionId!= null){
			redisService.set(jsessionId, "user1", 30, TimeUnit.MINUTES);
		}
		ModelAndView view = new ModelAndView("pikapika");
		view.addObject("activeModel", "index");
		return view;
	}
	@RequestMapping(value="pikapikaIndex", method = RequestMethod.GET)
	public ModelAndView searchAllBbs() throws Exception{
		ModelAndView view = new ModelAndView("pikapikaIndex");
		List<CharactorEntity> entities = charactorService.searchAllCharactors("1", "5");
		view.addObject("charactorsInfo", entities);
		return view;
	}
}
