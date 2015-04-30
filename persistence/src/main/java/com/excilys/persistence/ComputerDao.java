package com.excilys.persistence;

import java.util.List;

import com.excilys.model.Computer;
import com.excilys.model.Page;

// TODO: Auto-generated Javadoc
/**
 * The Interface ComputerDao.
 */
public interface ComputerDao {
	
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
	 * Gets the details.
	 *
	 * @param id the id
	 * @return the details
	 */
	public Computer getDetails(long id);
	
	/**
	 * Creates the computer.
	 *
	 * @param computer the computer
	 */
	public void create(Computer computer);
	
	/**
	 * Update the computer.
	 *
	 * @param computer the computer
	 */
	public void update(Computer computer);
	
	/**
	 * Delete the computer.
	 *
	 * @param id the id
	 */
	public void delete(long id);
	
	/**
	 * Delete< by company id.
	 *
	 * @param companyId the company id
	 */
	public void deleteByCompanyId(long companyId);
}
