package com.excilys.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpringSecurityController {

	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public ModelAndView loginPage(@RequestParam("success") Optional<String> success,  ModelAndView model) {
		if (success.isPresent()) {
			model.addObject("success", "success");
		}
		model.setViewName("loginPage");
        return model;
    }
	
	@RequestMapping(value = "/loginError", method = RequestMethod.GET)
    public ModelAndView loginError(ModelAndView model) {
		model.addObject("error", "error");
		model.setViewName("loginPage");
        return model;
    }
	
}
