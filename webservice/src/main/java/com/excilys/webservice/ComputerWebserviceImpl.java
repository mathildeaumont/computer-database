package com.excilys.webservice;

import java.time.LocalDateTime;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

@Component
@WebService(endpointInterface = "com.excilys.webservice.ComputerWebservice")
public class ComputerWebserviceImpl implements ComputerWebservice {

	@Autowired
	private CompanyService companyService;
	@Autowired
	private ComputerService computerService;

	@Override
	public String getCompanies() {
		return companyService.getAll().toString();
	}

	@Override
	public String getComputers() {
		return computerService.getAll().toString();
	}

	@Override
	public String getComputerById(long id) {
		return computerService.getById(id).toString();
	}

	@Override
	public void createComputer(String name, LocalDateTime introduced, LocalDateTime discontinued, long company) {
		computerService.create(name, introduced, discontinued, company);
	}

	@Override
	public void updateComputer(long computerId, String name, LocalDateTime introduced, LocalDateTime discontinued, long company) {
		computerService.update(computerId, name, introduced, discontinued, company);
	}

	@Override
	public void deleteCompany(long id) {
		companyService.delete(id);
	}
	
	@Override
	public void deleteComputer(long id) {
		computerService.delete(id);
	}
}
