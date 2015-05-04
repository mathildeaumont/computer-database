package com.excilys.persistence;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import com.excilys.ebi.spring.dbunit.config.DBOperation;
import com.excilys.ebi.spring.dbunit.test.DataSet;
import com.excilys.ebi.spring.dbunit.test.RollbackTransactionalDataSetTestExecutionListener;
import com.excilys.model.Company;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context-persistence-test.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
TransactionalTestExecutionListener.class,
RollbackTransactionalDataSetTestExecutionListener.class })
public class CompanyDaoTest {

	@Autowired
	CompanyDao companyDao;

	@Test
	@DataSet(value = "getCompaniesEmpty.xml", tearDownOperation = DBOperation.DELETE_ALL)
	public void getAllCompaniesWhenEmptyDataset() {
		// GIVEN
		// WHEN
		List<Company> companies = companyDao.getAll();
		// THEN
		Assertions.assertThat(companies).isNotNull();
		Assertions.assertThat(companies).isEmpty();
	}
	
	@Test
	@DataSet(value = "getCompanies.xml", tearDownOperation = DBOperation.DELETE_ALL)
	public void getAllCompaniesOk() {
		// GIVEN
		final int size = 2;
		final Company company1 = new Company(1, "Test");
		final Company company2 = new Company(2, "Test2");
		final List<Company> expectedCompanies = Arrays.asList(company1, company2);

		// WHEN
		List<Company> companies = companyDao.getAll();
		// THEN
		Assertions.assertThat(companies).isNotNull();
		Assertions.assertThat(companies).isNotEmpty();
		Assertions.assertThat(companies.size()).isEqualTo(size);
		Assertions.assertThat(companies).containsAll(expectedCompanies);
	}
	
}
