package com.excilys.persistence;

import java.util.List;

import com.excilys.model.Company;
// TODO: Auto-generated Javadoc

/**
 * The Interface CompanyDao.
 */
public interface CompanyDao {

	/**
	 * Gets all companies.
	 *
	 * @return all companies
	 */
	public List<Company> getAll();
	
	/**
	 * Delete.
	 *
	 * @param companyId the company id
	 */
	public void delete(long companyId);
}
