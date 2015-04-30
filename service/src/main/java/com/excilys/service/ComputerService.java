package com.excilys.service;

import java.time.LocalDateTime;
import java.util.List;

import com.excilys.model.Computer;
import com.excilys.model.Page;

// TODO: Auto-generated Javadoc
/**
 * The Interface ComputerService.
 */
public interface ComputerService {
	
	/**
	 * Gets the length.
	 *
	 * @return the length
	 */
	public int getLength();
	
	/**
	 * Gets the length.
	 *
	 * @param search the search
	 * @return the length
	 */
	public int getLength(String search);
	
	/**
	 * Gets all computers.
	 *
	 * @return all computers
	 */
	public List<Computer> getAll();
	
	/**
	 * Gets all computers by page.
	 *
	 * @param page the page
	 * @param order the order
	 * @param direction the direction
	 * @param search the search
	 * @return the all by page
	 */
	public List<Computer> getAllByPage(Page<Computer> page, String order, String direction, String search);
	
	/**
	 * Gets the computer by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	public Computer getById(long id);
	
	/**
	 * Creates the computer.
	 *
	 * @param name the name
	 * @param introduced the introduced
	 * @param discontinued the discontinued
	 * @param companyId the company id
	 */
	public void create(String name, LocalDateTime introduced, LocalDateTime discontinued, long companyId);
	
	/**
	 * Update the computer.
	 *
	 * @param computerId the computer id
	 * @param name the name
	 * @param introduced the introduced
	 * @param discontinued the discontinued
	 * @param companyId the company id
	 */
	public void update(long computerId, String name, LocalDateTime introduced, LocalDateTime discontinued, long companyId);
	
	/**
	 * Delete the computer.
	 *
	 * @param computerId the computer id
	 */
	public void delete(long computerId);
	
	/**
	 * Create the page.
	 *
	 * @param nbPage the number of pages
	 * @param nbResultByPage the number of results by page
	 * @param search the search term
	 * @return the page
	 */
	public Page<Computer> page(int nbPage, int nbResultByPage, String search);
}
