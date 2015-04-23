package com.excilys.webservice;


import java.time.LocalDateTime;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding
public interface ComputerWebservice {
	
	@WebMethod
	public String getCompanies();
	
	@WebMethod
	public String getComputers();
	
	@WebMethod
	public String getComputerById(long id);
	
	@WebMethod
	public void createComputer(String name, LocalDateTime introduced, LocalDateTime discontinued, long company);
	
	@WebMethod
	public void updateComputer(long computerId, String name, LocalDateTime introduced, LocalDateTime discontinued, long company);
	
	@WebMethod
	public void deleteCompany(long id);
	
	@WebMethod
	public void deleteComputer(long id);

}
