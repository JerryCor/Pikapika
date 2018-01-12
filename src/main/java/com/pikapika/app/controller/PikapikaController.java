package com.pikapika.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pikapika.app.entity.CharactorEntity;
import com.pikapika.app.service.CharactorService;

@Controller
@RequestMapping("/pikapika")
public class PikapikaController {
	
	@Autowired
	private CharactorService charactorService;
	
	@RequestMapping(value="index", method = RequestMethod.GET)
	public ModelAndView index() throws Exception{
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
