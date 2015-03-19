package com.excilys.service;

import java.util.List;

import com.excilys.model.CompanyModel;
import com.excilys.persistence.CompanyDao;
import com.excilys.persistence.CompanyDaoImpl;

public class CompanyServiceImpl implements CompanyService {
	
	public List<CompanyModel> getAll() {
		CompanyDao companyDao = new CompanyDaoImpl();
		return companyDao.getAllCompanies();
	}
	
}
