package com.excilys.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.mapper.CompanyMapper;
import com.excilys.model.Company;
import com.excilys.model.QCompany;
import com.mysema.query.jpa.impl.JPAQuery;

@Repository
public class CompanyDaoImpl implements CompanyDao {

	@Autowired
	private DaoFactory daoFactory;

	@Autowired
	private CompanyMapper companyMapper;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	public List<Company> getAllCompanies() {
		EntityManager em = entityManagerFactory.createEntityManager();
		JPAQuery query = new JPAQuery(em);
		QCompany company = QCompany.company;

		List<Company> companies = query.from(company).list(company);
		return companies;
	}

	public void deleteCompany(long companyId) {
		jdbcTemplate.update("DELETE FROM company WHERE company_id = ?;", companyId);
	}
}
