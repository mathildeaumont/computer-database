package com.excilys.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.service.ComputerService;

@Controller
@RequestMapping("/delete")
public class DeleteComputer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DeleteComputer.class);
	
	@Autowired
	ComputerService service;

	@RequestMapping(method = RequestMethod.POST)
	public String doPost(@RequestParam("selection") String selected) {
		if (selected != null&& !selected.isEmpty()) { 
			String[] computerIds = selected.split(",");
			if (computerIds != null && computerIds.length > 0) {
				for (String id : computerIds) {
					Long computerId = Long.parseLong(id);
					service.delete(computerId);
					LOGGER.info("Successfully deleted computer");
				}
			} else {
				LOGGER.error("Failure : computer ids null");
			}
		} else {
			LOGGER.error("Failure : Selection is empty");
		}
		return "redirect:dashboard";
	}

}
