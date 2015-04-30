package com.excilys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.model.Company;
import com.excilys.persistence.CompanyDao;
import com.excilys.persistence.ComputerDao;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanyServiceImpl.
 */
@Service
public class CompanyServiceImpl implements CompanyService {

	/** The company dao. */
	@Autowired
	CompanyDao companyDao;
	
	/** The computer dao. */
	@Autowired
	ComputerDao computerDao;
	
	/**
	 * Instantiates a new company service impl.
	 */
	public CompanyServiceImpl() {

	}

	/* (non-Javadoc)
	 * @see com.excilys.service.CompanyService#getAll()
	 */
	public List<Company> getAll() {
		return companyDao.getAll();
	}

	/* (non-Javadoc)
	 * @see com.excilys.service.CompanyService#delete(long)
	 */
	@Transactional
	public void delete(long companyId) {
		computerDao.deleteByCompanyId(companyId);
		companyDao.delete(companyId);
	}
}
