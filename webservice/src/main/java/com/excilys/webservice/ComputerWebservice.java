package com.excilys.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.excilys.model.Computer;
import com.excilys.model.Page;

@WebService
public interface ComputerWebservice {
	
	@WebMethod
	public String getCompanies();
	
	@WebMethod
	public String getComputers();
	
	@WebMethod
	public String getComputerById(long id);
	
	@WebMethod
	public String getAllByPage(Page<Computer> page, String order, String direction, String search);
	
	@WebMethod
	public void createComputer(String name, String introduced, String discontinued, long company);
	
	@WebMethod
	public void updateComputer(long computerId, String name, String introduced, String discontinued, long company);
	
	@WebMethod
	public void deleteCompany(long id);
	
	@WebMethod
	public void deleteComputer(long id);
	
	@WebMethod
	public int getLengthComputers();

	@WebMethod
	public Page<Computer> pageComputers(int nbPage, int nbResultByPage, String search);
}
