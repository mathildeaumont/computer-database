package com.excilys.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.dto.CompanyDto;
import com.excilys.mapper.CompanyMapperDto;
import com.excilys.service.CompanyService;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanyRestController.
 */
@RestController
@RequestMapping("/rest/companies")
public class CompanyRestController {

	/** The company service. */
	@Autowired
	private CompanyService companyService;
	
	/**
	 * Gets the companies.
	 *
	 * @return the companies
	 */
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public List<CompanyDto> getCompanies() {
		return CompanyMapperDto.modelsToDtos(companyService.getAll());
	}
}
