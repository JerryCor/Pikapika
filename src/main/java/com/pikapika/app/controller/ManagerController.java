package com.pikapika.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pikapika")
public class ManagerController {
	@RequestMapping(value="charactors", method = RequestMethod.GET)
	public ModelAndView charactors(){
		ModelAndView view = new ModelAndView("charactortoManage");
		return view;
	}
	@RequestMapping(value="charactorsInfo/{charactorId}", method = RequestMethod.GET)
	public ModelAndView charactorsInfo(@PathVariable String charactorId){
		ModelAndView view = new ModelAndView("charactorsInfo");
		return view;
	}
}
