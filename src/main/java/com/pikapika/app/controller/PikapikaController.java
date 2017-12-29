package com.pikapika.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pikapika")
public class PikapikaController {
	@RequestMapping(value="index", method = RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView view = new ModelAndView("pikapika");
		return view;
	}
	@RequestMapping(value="pikapikaIndex", method = RequestMethod.GET)
	public ModelAndView searchAllBbs(){
		ModelAndView view = new ModelAndView("pikapikaIndex");
		return view;
	}
}
