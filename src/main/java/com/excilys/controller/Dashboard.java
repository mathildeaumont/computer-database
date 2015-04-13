package com.excilys.controller;
import com.excilys.mapper.ComputerMapperDto;
import com.excilys.model.ComputerModel;
import com.excilys.model.Page;
import com.excilys.service.ComputerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/dashboard")
public class Dashboard {

	@Autowired 
	ComputerService service;

	@RequestMapping(method = RequestMethod.GET)
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

		Page<ComputerModel> currentPage = service.page(page, nbResultsParam, searchParam);
		List<ComputerModel> computers = service.getAllByPage(currentPage, orderParam, directionParam, searchParam);

		model.addAttribute("computers", ComputerMapperDto.modelsToDtos(computers));
		model.addAttribute("computersNb", currentPage.getNbResultTotal());
		model.addAttribute("page", currentPage);
		model.addAttribute("order", orderParam);
		model.addAttribute("direction", directionParam);
		model.addAttribute("search", searchParam);

		return "dashboard";
	}
}