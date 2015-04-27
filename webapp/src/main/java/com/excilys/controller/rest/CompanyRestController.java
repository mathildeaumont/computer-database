package com.excilys.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.model.Company;
import com.excilys.service.CompanyService;

@RestController
@RequestMapping("/rest/companies")
public class CompanyRestController {

	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public List<Company> getCompanies() {
		return companyService.getAll();
	}
}
