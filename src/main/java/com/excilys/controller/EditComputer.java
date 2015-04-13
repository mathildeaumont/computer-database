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

import com.excilys.mapper.ComputerMapperDto;
import com.excilys.model.ComputerModel;
import com.excilys.service.CompanyService;
import com.excilys.service.CompanyServiceImpl;
import com.excilys.service.ComputerService;
import com.excilys.util.Regex;

@Controller
@RequestMapping("/edit")
public class EditComputer {

	private static final Logger LOGGER = LoggerFactory.getLogger(EditComputer.class);
	
	@Autowired
	ComputerService service;
	
	@Autowired
	CompanyService companyService;


	@RequestMapping(method = RequestMethod.GET)
	public String doGet(@RequestParam("id") long id, Model model) {
		model.addAttribute("companies", companyService.getAll());
		ComputerModel computer = service.getById(id);
		model.addAttribute("computer", ComputerMapperDto.modelToDto(computer));
		return "editComputer";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String doPost(@RequestParam("computerId") long computerId,
			@RequestParam("name") String name, 
			@RequestParam("introduced") Optional<String> introduced,
			@RequestParam("discontinued") Optional<String> discontinued,
			@RequestParam("companyId") long companyId, Model model) {
		
		if (name != null) {
			name = name.trim();
			if (name.isEmpty()) {
				model.addAttribute("errorName", "Name is required");
				model.addAttribute("companies", new CompanyServiceImpl().getAll());
				LOGGER.error("Failure : Name is required");
				return "editComputer";
			}
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime introducedDate = null;
		LocalDateTime discontinuedDate = null;
		
		String introducedParam = introduced.get();
		if (introducedParam != null) {
			if (!introducedParam.isEmpty()) {
				if (!Pattern.matches(Regex.DATE_FORMAT.getRegex(), introducedParam.trim())) {
					model.addAttribute("errorIntroduced", "Invalid format (yyyy-mm-dd hh:mm:ss)");
					model.addAttribute("companies", companyService.getAll());
					model.addAttribute("id", computerId);
					ComputerModel computer = service.getById(computerId);
					model.addAttribute("computer", computer);
					LOGGER.error("Failure : Bad format introduced date");
					return "editComputer";
				}
				introducedDate = LocalDateTime.parse(introducedParam, formatter);
			}
		}
		
		String discontinuedParam = discontinued.get();
		if (discontinuedParam != null) {
			if (!discontinuedParam.isEmpty()) {
				if (!Pattern.matches(Regex.DATE_FORMAT.getRegex(), discontinuedParam.trim())) {
					model.addAttribute("errorDiscontinued", "Invalid format (yyyy-mm-dd hh:mm:ss)");
					model.addAttribute("companies", companyService.getAll());
					model.addAttribute("id", computerId);
					ComputerModel computer = service.getById(computerId);
					model.addAttribute("computer", computer);
					LOGGER.error("Failure : Bad format discontinued date");
					return "editComputer";
				}
				discontinuedDate = LocalDateTime.parse(discontinuedParam, formatter);
			}
		}
	
		service.update(computerId, name, introducedDate, discontinuedDate, companyId);
		LOGGER.info("Successfully updated computer");
		return "redirect:dashboard";
	}

}
