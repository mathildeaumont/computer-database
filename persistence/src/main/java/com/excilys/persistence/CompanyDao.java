package com.excilys.persistence;

import java.util.List;

import com.excilys.model.Company;
public interface CompanyDao {

	public List<Company> getAll();
	
	public void delete(long companyId);
}
