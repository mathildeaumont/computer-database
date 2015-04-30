package com.excilys.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.excilys.model.Computer;
import com.excilys.model.Page;

// TODO: Auto-generated Javadoc
/**
 * The Interface ComputerWebservice.
 */
@WebService
public interface ComputerWebservice {
	
	/**
	 * Gets the companies.
	 *
	 * @return the companies
	 */
	@WebMethod
	public String getCompanies();
	
	/**
	 * Gets the computers.
	 *
	 * @return the computers
	 */
	@WebMethod
	public String getComputers();
	
	/**
	 * Gets the computer by id.
	 *
	 * @param id the id
	 * @return the computer by id
	 */
	@WebMethod
	public String getComputerById(long id);
	
	/**
	 * Gets the all by page.
	 *
	 * @param page the page
	 * @param order the order
	 * @param direction the direction
	 * @param search the search
	 * @return the all by page
	 */
	@WebMethod
	public String getAllByPage(Page<Computer> page, String order, String direction, String search);
	
	/**
	 * Creates the computer.
	 *
	 * @param name the name
	 * @param introduced the introduced
	 * @param discontinued the discontinued
	 * @param company the company
	 */
	@WebMethod
	public void createComputer(String name, String introduced, String discontinued, long company);
	
	/**
	 * Update computer.
	 *
	 * @param computerId the computer id
	 * @param name the name
	 * @param introduced the introduced
	 * @param discontinued the discontinued
	 * @param company the company
	 */
	@WebMethod
	public void updateComputer(long computerId, String name, String introduced, String discontinued, long company);
	
	/**
	 * Delete company.
	 *
	 * @param id the id
	 */
	@WebMethod
	public void deleteCompany(long id);
	
	/**
	 * Delete computer.
	 *
	 * @param id the id
	 */
	@WebMethod
	public void deleteComputer(long id);
	
	/**
	 * Gets the length computers.
	 *
	 * @return the length computers
	 */
	@WebMethod
	public int getLengthComputers();

	/**
	 * Page computers.
	 *
	 * @param nbPage the nb page
	 * @param nbResultByPage the nb result by page
	 * @param search the search
	 * @return the page
	 */
	@WebMethod
	public Page<Computer> pageComputers(int nbPage, int nbResultByPage, String search);
}
