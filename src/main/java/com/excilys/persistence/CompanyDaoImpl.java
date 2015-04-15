package com.excilys.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.mapper.CompanyMapper;
import com.excilys.model.Company;

@Repository
public class CompanyDaoImpl implements CompanyDao {

	@Autowired
	private DaoFactory daoFactory;

	@Autowired
	private CompanyMapper companyMapper;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Company> getAllCompanies() {
		return jdbcTemplate.query("SELECT * FROM company;", companyMapper);
	}

	public void deleteCompany(long companyId) {
		jdbcTemplate.update("DELETE FROM company WHERE company_id = ?;", companyId);
	}
}
