package com.excilys.controller;
import com.excilys.dto.ComputerDto;
import com.excilys.mapper.CompanyMapperDto;
import com.excilys.mapper.ComputerMapperDto;
import com.excilys.model.Computer;
import com.excilys.model.Page;
import com.excilys.service.CompanyService;
import com.excilys.service.CompanyServiceImpl;
import com.excilys.service.ComputerService;
import com.excilys.util.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@Controller
public class ComputerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerController.class);

	@Autowired 
	ComputerService service;
	
	@Autowired
	CompanyService companyService;

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView doGet(@RequestParam("offset") Optional<Integer> offset, 
						@RequestParam("nbResults") Optional<Integer> nbResults,
						@RequestParam("order") Optional<String> order,
						@RequestParam("direction") Optional<String> direction,
						@RequestParam("search") Optional<String> search, ModelAndView model) {

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

		model.addObject("computers", ComputerMapperDto.modelsToDtos(computers));
		model.addObject("computersNb", currentPage.getNbResultTotal());
		model.addObject("page", currentPage);
		model.addObject("order", orderParam);
		model.addObject("direction", directionParam);
		model.addObject("search", searchParam);

		model.setViewName("dashboard");
		return model;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView delete(@RequestParam("selection") String selected, ModelAndView model) {
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
		model.setViewName("redirect:dashboard");
		return model;
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(ModelAndView model) {
		ComputerDto computer = new ComputerDto();
	    model.addObject("addComputerForm", computer);
	    model.addObject("companies", companyService.getAll());
	    model.setViewName("addComputer");
		return model;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView add(@Valid @ModelAttribute("addComputerForm") ComputerDto computerForm, BindingResult result, ModelAndView model) {
			
		int nbErrors = 0;

		String nameParam = computerForm.getName();
		if (Validator.isValidName(nameParam)) {
			nameParam = nameParam.trim();
			if (nameParam.isEmpty()) {
				model.addObject("companies", CompanyMapperDto.modelsToDtos(companyService.getAll()));
				nbErrors++;
			} else {
				model.addObject("name", nameParam);
			}
		}

		DateTimeFormatter formatter;
		String errorDate;
		if (LocaleContextHolder.getLocale().toString().equals("fr")) {
			formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			errorDate = "Invalid format (dd-mm-yyyy hh:mm:ss)";
		} else {
			formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			errorDate = "Invalid format (yyyy-mm-dd hh:mm:ss)";
		}
		LocalDateTime introducedDate = null;
		LocalDateTime discontinuedDate = null;

		String introducedParam = computerForm.getIntroducedDate();
		if (!Validator.isValidDate(introducedParam) && !introducedParam.isEmpty()) {
			introducedDate = LocalDateTime.parse(introducedParam, formatter);
			model.addObject("introduced", introducedParam);
		} else if (!introducedParam.isEmpty()) {
			model.addObject("errorIntroduced", errorDate);
			model.addObject("companies", CompanyMapperDto.modelsToDtos(companyService.getAll()));
			nbErrors++;
		}

		String discontinuedParam = computerForm.getDiscontinuedDate();
		if (!Validator.isValidDate(discontinuedParam) && !discontinuedParam.isEmpty()) {
			discontinuedDate = LocalDateTime.parse(discontinuedParam, formatter);
			model.addObject("discontinued", discontinuedParam);
		} else if (!discontinuedParam.isEmpty()) {
			model.addObject("errorDiscontinued", errorDate);
			model.addObject("companies", CompanyMapperDto.modelsToDtos(companyService.getAll()));
			nbErrors++;
		}
		
		long companyIdParam = computerForm.getCompany().getId();
		model.addObject("companyId", companyIdParam);

		if (nbErrors != 0) {
			model.setViewName("addComputer");
			return model;
		}

		service.create(nameParam, introducedDate, discontinuedDate, companyIdParam);

		LOGGER.info("Successfully created computer");
		model.setViewName("redirect:dashboard");
		return model;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("id") long id, ModelAndView model) {
		model.addObject("companies", companyService.getAll());
		Computer computer = service.getById(id);
		model.addObject("computer", ComputerMapperDto.modelToDto(computer));
		model.setViewName("editComputer");
		return model;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView edit(@RequestParam("computerId") long computerId,
			@RequestParam("name") String name, 
			@RequestParam("introduced") Optional<String> introduced,
			@RequestParam("discontinued") Optional<String> discontinued,
			@RequestParam("companyId") long companyId, ModelAndView model) {
		
		if (Validator.isValidName(name)) {
			name = name.trim();
			if (name.isEmpty()) {
				model.addObject("errorName", "Name is required");
				model.addObject("companies", new CompanyServiceImpl().getAll());
				LOGGER.error("Failure : Name is required");
				model.setViewName("editComputer");
				return model;
			}
		}
		
		DateTimeFormatter formatter;
		String errorDate;
		if (LocaleContextHolder.getLocale().toString().equals("fr")) {
			formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			errorDate = "Invalid format (dd-mm-yyyy hh:mm:ss)";
		} else {
			formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			errorDate = "Invalid format (yyyy-mm-dd hh:mm:ss)";
		}
		LocalDateTime introducedDate = null;
		LocalDateTime discontinuedDate = null;
		
		String introducedParam = introduced.get();
		if (!Validator.isValidDate(introducedParam) && !introducedParam.isEmpty()) {
			model.addObject("errorIntroduced", errorDate);
			model.addObject("companies", companyService.getAll());
			model.addObject("id", computerId);
			Computer computer = service.getById(computerId);
			model.addObject("computer", computer);
			model.setViewName("editComputer");
			return model;
		}
		if (!introducedParam.isEmpty()) {
			introducedDate = LocalDateTime.parse(introducedParam, formatter);
		}

		String discontinuedParam = discontinued.get();
		if (!Validator.isValidDate(discontinuedParam) && !discontinuedParam.isEmpty()) {
			model.addObject("errorDiscontinued", errorDate);
			model.addObject("companies", companyService.getAll());
			model.addObject("id", computerId);
			Computer computer = service.getById(computerId);
			model.addObject("computer", computer);
			model.setViewName("editComputer");
			return model;
		}
		if (!discontinuedParam.isEmpty()) {
			discontinuedDate = LocalDateTime.parse(discontinuedParam, formatter);
		}
	
		service.update(computerId, name, introducedDate, discontinuedDate, companyId);
		LOGGER.info("Successfully updated computer");
		model.setViewName("redirect:dashboard");
		return model;
	}
}