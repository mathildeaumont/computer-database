package com.excilys.webservice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.mapper.CompanyMapperDto;
import com.excilys.mapper.ComputerMapperDto;
import com.excilys.model.Computer;
import com.excilys.model.Page;
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
		return CompanyMapperDto.modelsToDtos(companyService.getAll()).toString();
	}

	@Override
	public String getComputers() {
		return ComputerMapperDto.modelsToDtos(computerService.getAll()).toString();
	}
	
	public String getAllByPage(Page<Computer> page, String order, String direction, String search) {
		return computerService.getAllByPage(page, order, direction, search).toString();
	}

	@Override
	public String getComputerById(long id) {
		return ComputerMapperDto.modelToDto(computerService.getById(id)).toString();
	}

	@Override
	public void createComputer(String name, String introduced, String discontinued, long company) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime introducedDate;
		LocalDateTime discontinuedDate;
		if (introduced != null) {
			introducedDate = LocalDateTime.parse(introduced, formatter);
		} else {
			introducedDate = null;
		}
		if (discontinued != null) {
			discontinuedDate = LocalDateTime.parse(discontinued, formatter);
		} else {
			discontinuedDate = null;
		}
		computerService.create(name, introducedDate, discontinuedDate, company);
	}

	@Override
	public void updateComputer(long computerId, String name, String introduced, String discontinued, long company) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime introducedDate;
		LocalDateTime discontinuedDate;
		if (introduced != null) {
			introducedDate = LocalDateTime.parse(introduced, formatter);
		} else {
			introducedDate = null;
		}
		if (discontinued != null) {
			discontinuedDate = LocalDateTime.parse(discontinued, formatter);
		} else {
			discontinuedDate = null;
		}
		computerService.update(computerId, name, introducedDate, discontinuedDate, company);
	}

	@Override
	public void deleteCompany(long id) {
		companyService.delete(id);
	}
	
	@Override
	public void deleteComputer(long id) {
		computerService.delete(id);
	}
	
	@Override
	public int getLengthComputers() {
		return computerService.getLength();
	}
	
	@Override
	public Page<Computer> pageComputers(int nbPage, int nbResultByPage, String search) {
		return computerService.page(nbPage, nbResultByPage, search);
	}
}
