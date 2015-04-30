package com.excilys.service;

import java.util.List;

import com.excilys.model.Company;

// TODO: Auto-generated Javadoc
/**
 * The Interface CompanyService.
 */
public interface CompanyService {
	
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
