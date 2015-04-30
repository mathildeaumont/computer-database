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

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerWebserviceImpl.
 */
@Component
@WebService(endpointInterface = "com.excilys.webservice.ComputerWebservice")
public class ComputerWebserviceImpl implements ComputerWebservice {

	/** The company service. */
	@Autowired
	private CompanyService companyService;
	
	/** The computer service. */
	@Autowired
	private ComputerService computerService;

	/* (non-Javadoc)
	 * @see com.excilys.webservice.ComputerWebservice#getCompanies()
	 */
	@Override
	public String getCompanies() {
		return CompanyMapperDto.modelsToDtos(companyService.getAll()).toString();
	}

	/* (non-Javadoc)
	 * @see com.excilys.webservice.ComputerWebservice#getComputers()
	 */
	@Override
	public String getComputers() {
		return ComputerMapperDto.modelsToDtos(computerService.getAll()).toString();
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.webservice.ComputerWebservice#getAllByPage(com.excilys.model.Page, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getAllByPage(Page<Computer> page, String order, String direction, String search) {
		return computerService.getAllByPage(page, order, direction, search).toString();
	}

	/* (non-Javadoc)
	 * @see com.excilys.webservice.ComputerWebservice#getComputerById(long)
	 */
	@Override
	public String getComputerById(long id) {
		return ComputerMapperDto.modelToDto(computerService.getById(id)).toString();
	}

	/* (non-Javadoc)
	 * @see com.excilys.webservice.ComputerWebservice#createComputer(java.lang.String, java.lang.String, java.lang.String, long)
	 */
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

	/* (non-Javadoc)
	 * @see com.excilys.webservice.ComputerWebservice#updateComputer(long, java.lang.String, java.lang.String, java.lang.String, long)
	 */
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

	/* (non-Javadoc)
	 * @see com.excilys.webservice.ComputerWebservice#deleteCompany(long)
	 */
	@Override
	public void deleteCompany(long id) {
		companyService.delete(id);
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.webservice.ComputerWebservice#deleteComputer(long)
	 */
	@Override
	public void deleteComputer(long id) {
		computerService.delete(id);
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.webservice.ComputerWebservice#getLengthComputers()
	 */
	@Override
	public int getLengthComputers() {
		return computerService.getLength();
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.webservice.ComputerWebservice#pageComputers(int, int, java.lang.String)
	 */
	@Override
	public Page<Computer> pageComputers(int nbPage, int nbResultByPage, String search) {
		return computerService.page(nbPage, nbResultByPage, search);
	}
}
