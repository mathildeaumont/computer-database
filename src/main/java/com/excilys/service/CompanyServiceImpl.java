package com.excilys.service;

import java.util.List;

import com.excilys.model.CompanyModel;
import com.excilys.persistence.CompanyDao;
import com.excilys.persistence.DaoFactory;

public class CompanyServiceImpl implements CompanyService {
	
	private CompanyDao companyDao;
	
	public CompanyServiceImpl() {
		companyDao = DaoFactory.INSTANCE.getCompanyDAO();
	}
	
	public List<CompanyModel> getAll() {
		return companyDao.getAllCompanies();
	}
	
}
