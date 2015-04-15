package com.excilys.persistence;

import java.util.List;

import com.excilys.model.Company;
public interface CompanyDao {

	public List<Company> getAllCompanies();
	
	public void deleteCompany(long companyId);
}
