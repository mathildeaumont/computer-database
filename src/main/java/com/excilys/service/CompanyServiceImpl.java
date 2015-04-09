package com.excilys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.model.CompanyModel;
import com.excilys.persistence.CompanyDao;
import com.excilys.persistence.ComputerDao;
import com.excilys.persistence.ComputerDaoImpl;
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

	@Transactional
	public void delete(long companyId) {
		ComputerDao computerDao = new ComputerDaoImpl();
		computerDao.deleteComputerByCompanyId(companyId);
		companyDao.deleteCompany(companyId);
	}
}
