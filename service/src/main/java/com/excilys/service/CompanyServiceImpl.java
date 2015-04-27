package com.excilys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.model.Company;
import com.excilys.persistence.CompanyDao;
import com.excilys.persistence.ComputerDao;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyDao companyDao;
	
	@Autowired
	ComputerDao computerDao;
	
	public CompanyServiceImpl() {

	}

	public List<Company> getAll() {
		return companyDao.getAll();
	}

	@Transactional
	public void delete(long companyId) {
		computerDao.deleteByCompanyId(companyId);
		companyDao.delete(companyId);
	}
}
