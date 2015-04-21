package com.excilys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SpringSecurityController {

	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public String loginPage() {
        return "loginPage";
    }
	
	@RequestMapping(value = "/loginError", method = RequestMethod.GET)
    public String loginError() {
        return "loginPage";
    }
}
