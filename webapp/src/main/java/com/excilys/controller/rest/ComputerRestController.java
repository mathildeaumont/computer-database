package com.excilys.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.model.Computer;
import com.excilys.service.ComputerService;

@RestController
@RequestMapping("/rest/computers")
public class ComputerRestController {
	
	@Autowired
	private ComputerService computerService;
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public List<Computer> getComputers() {
		return computerService.getAll();
	}
	
	@RequestMapping(value = "/getById", method = RequestMethod.GET)
	public Computer getComputerById(@RequestParam("id") long id) {
		return computerService.getById(id);
	}

}
