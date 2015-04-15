package com.excilys.controller;
import com.excilys.dto.ComputerDto;
import com.excilys.mapper.CompanyMapperDto;
import com.excilys.mapper.ComputerMapperDto;
import com.excilys.model.Computer;
import com.excilys.model.Page;
import com.excilys.service.CompanyService;
import com.excilys.service.CompanyServiceImpl;
import com.excilys.service.ComputerService;
import com.excilys.util.Regex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.validation.Valid;

@Controller
public class ComputerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerController.class);

	@Autowired 
	ComputerService service;
	
	@Autowired
	CompanyService companyService;

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String doGet(@RequestParam("offset") Optional<Integer> offset, 
						@RequestParam("nbResults") Optional<Integer> nbResults,
						@RequestParam("order") Optional<String> order,
						@RequestParam("direction") Optional<String> direction,
						@RequestParam("search") Optional<String> search, Model model) {

		int page = 1;
		if (offset.isPresent()) {
			page = offset.get();
		}
		int nbResultsParam = 10;
		if (nbResults.isPresent()) {
			nbResultsParam = nbResults.get();
		}

		String orderParam = "compu.id";
		if (order.isPresent()) {
			orderParam = order.get();
		}

		String directionParam = "asc";
		if (direction.isPresent()) {
			directionParam = direction.get();
		}

		String searchParam = "";
		if (search.isPresent()) {
			searchParam = search.get();
		}

		Page<Computer> currentPage = service.page(page, nbResultsParam, searchParam);
		List<Computer> computers = service.getAllByPage(currentPage, orderParam, directionParam, searchParam);

		model.addAttribute("computers", ComputerMapperDto.modelsToDtos(computers));
		model.addAttribute("computersNb", currentPage.getNbResultTotal());
		model.addAttribute("page", currentPage);
		model.addAttribute("order", orderParam);
		model.addAttribute("direction", directionParam);
		model.addAttribute("search", searchParam);

		return "dashboard";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam("selection") String selected) {
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
	
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Map<String, Object> model) {
		ComputerDto computer = new ComputerDto();
	    model.put("addComputerForm", computer);
	    model.put("companies", companyService.getAll());
		return "addComputer";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@Valid @ModelAttribute("addComputerForm") ComputerDto computerForm, BindingResult result, Model model) {
			
		int nbErrors = 0;

		String nameParam = computerForm.getName();
		if (nameParam != null) {
			nameParam = nameParam.trim();
			if (nameParam.isEmpty()) {
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

		String introducedParam = computerForm.getIntroducedDate();
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

		String discontinuedParam = computerForm.getDiscontinuedDate();
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

		long companyIdParam = computerForm.getCompany().getId();
		model.addAttribute("companyId", companyIdParam);

		if (nbErrors != 0) {
			return "addComputer";
		}

		service.create(nameParam, introducedDate, discontinuedDate, companyIdParam);

		LOGGER.info("Successfully created computer");
		return "redirect:dashboard";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam("id") long id, Model model) {
		model.addAttribute("companies", companyService.getAll());
		Computer computer = service.getById(id);
		model.addAttribute("computer", ComputerMapperDto.modelToDto(computer));
		return "editComputer";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(@RequestParam("computerId") long computerId,
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
					Computer computer = service.getById(computerId);
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
					Computer computer = service.getById(computerId);
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