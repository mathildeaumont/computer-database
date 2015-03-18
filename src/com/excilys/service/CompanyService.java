package com.excilys.service;

import java.util.List;

import com.excilys.model.CompanyModel;
import com.excilys.persistence.CompanyDao;
import com.excilys.persistence.CompanyDaoImpl;

public class CompanyService {
	
	public static List<CompanyModel> getAll() {
		CompanyDao companyDao = new CompanyDaoImpl();
		return companyDao.getAllCompanies();
	}
	
}
