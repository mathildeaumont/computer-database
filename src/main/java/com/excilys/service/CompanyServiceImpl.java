package com.excilys.service;

import java.sql.SQLException;
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

	public void delete(long companyId) {
		DaoFactory.INSTANCE.startTransaction();
		try {
			companyDao.deleteCompany(companyId);
			DaoFactory.INSTANCE.commit();
		} catch (SQLException e) {
			DaoFactory.INSTANCE.rollback();
		}
		DaoFactory.INSTANCE.endTransaction();
		DaoFactory.INSTANCE.closeConnection();
	}
}
