package com.excilys.persistence;

import java.sql.SQLException;
import java.util.List;

import com.excilys.model.CompanyModel;

public interface CompanyDao {

	public List<CompanyModel> getAllCompanies();
	
	public void deleteCompany(long companyId) throws SQLException;
}
