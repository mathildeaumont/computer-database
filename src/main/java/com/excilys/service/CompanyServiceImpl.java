package com.excilys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.model.CompanyModel;
import com.excilys.persistence.CompanyDao;
import com.excilys.persistence.DaoFactory;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyDao companyDao;

	@Autowired
	DaoFactory daoFactory;
	
	public CompanyServiceImpl() {

	}

	public List<CompanyModel> getAll() {
		return companyDao.getAllCompanies();
	}

	public void delete(long companyId) {
		companyDao.deleteCompany(companyId);
	}
}
