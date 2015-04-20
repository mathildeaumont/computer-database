package com.excilys.service;

import java.util.List;

import com.excilys.model.Company;

public interface CompanyService {
	public List<Company> getAll();
	
	public void delete(long companyId);
}
