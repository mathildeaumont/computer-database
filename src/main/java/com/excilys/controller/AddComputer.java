package com.excilys.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Pattern;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.mapper.CompanyMapperDto;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.util.Regex;

@Controller
@RequestMapping("/add")
public class AddComputer {

	private static final Logger LOGGER = LoggerFactory.getLogger(AddComputer.class);

	@Autowired
	ComputerService service;
	
	@Autowired
	CompanyService companyService;

	@RequestMapping(method = RequestMethod.GET)
	public String doGet(Model model) {
		model.addAttribute("companies", companyService.getAll());
		return "addComputer";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doPost(@RequestParam("name") String name, 
						@RequestParam("introduced") Optional<String> introduced,
						@RequestParam("discontinued") Optional<String> discontinued,
						@RequestParam("companyId") long companyId, Model model) {

		int nbErrors = 0;

		String nameParam = name;
		if (nameParam != null) {
			nameParam = nameParam.trim();
			if (nameParam.isEmpty()) {
				model.addAttribute("errorName", "Name is required");
				model.addAttribute("companies", CompanyMapperDto.modelsToDtos(companyService.getAll()));
				nbErrors++;
			} else {
				model.addAttribute("name", nameParam);
			}
		} else {
			LOGGER.error("Error because of name is null");
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime introducedDate = null;
		LocalDateTime discontinuedDate = null;

		String introducedParam = introduced.get();
		if (introducedParam != null) {
			if (!introducedParam.isEmpty()) {
				if (!Pattern.matches(Regex.DATE_FORMAT.getRegex(), introducedParam.trim())) {
					model.addAttribute("errorIntroduced", "Invalid format (yyyy-mm-dd hh:mm:ss)");
					model.addAttribute("companies", CompanyMapperDto.modelsToDtos(companyService.getAll()));
					nbErrors++;
				} else {
					introducedDate = LocalDateTime.parse(introducedParam, formatter);
					model.addAttribute("introduced", introducedParam);
				}
			}
		} else {
			LOGGER.error("Error because of introduced date is null");
		}

		String discontinuedParam = discontinued.get();
		if (discontinuedParam != null) {
			if (!discontinuedParam.isEmpty()) {
				if (!Pattern.matches(Regex.DATE_FORMAT.getRegex(), discontinuedParam.trim())) {
					model.addAttribute("errorDiscontinued", "Invalid format (yyyy-mm-dd hh:mm:ss)");
					model.addAttribute("companies", CompanyMapperDto.modelsToDtos(companyService.getAll()));
					nbErrors++;
				} else {
					discontinuedDate = LocalDateTime.parse(discontinuedParam, formatter);
					model.addAttribute("discontinued", discontinuedParam);
				}
			}
		} else {
			LOGGER.error("Error because of discontinued date is null");
		}

		long companyIdParam = companyId;
		model.addAttribute("companyId", companyIdParam);

		if (nbErrors != 0) {
			return "addComputer";
		}

		service.create(name, introducedDate, discontinuedDate, companyId);

		LOGGER.info("Successfully created computer");
		return "redirect:dashboard";
	}
}
