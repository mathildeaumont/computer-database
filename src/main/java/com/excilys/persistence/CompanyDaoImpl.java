package com.excilys.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.mapper.CompanyMapper;
import com.excilys.model.CompanyModel;

@Repository
public class CompanyDaoImpl implements CompanyDao {

	@Autowired
	private DaoFactory daoFactory;

	@Autowired
	private CompanyMapper companyMapper;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<CompanyModel> getAllCompanies() {
		return jdbcTemplate.query("SELECT * FROM company;", companyMapper);
	}

	@Transactional
	public void deleteCompany(long companyId) {
		jdbcTemplate.update("DELETE FROM computer WHERE company_id = ?;", companyId);
	}
}
