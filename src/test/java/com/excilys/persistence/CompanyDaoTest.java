package com.excilys.persistence;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.excilys.model.Company;
import com.excilys.util.DBUtil;


@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/application-context-test.xml")
public class CompanyDaoTest {

	@Autowired
	DaoFactory daoFactory;
	
	@BeforeClass
	public static void setUpDB() {
		System.setProperty("env", "TEST");
		try {
			DBUtil.executeSqlFile("script.sql", DBUtil.getConnection());
		} catch (IOException | SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	@After
	public void tearDown() throws Exception {
		DBUtil.databaseTester.onTearDown();
	}

	@Test
	public void getAllCompaniesOk() throws Exception {
		// GIVEN
		DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
				"src/test/java/com/excilys/persistence/data/getCompanies.xml")));
		final int size = 2;
		final Company company1 = new Company(1, "Test");
		final Company company2 = new Company(2, "Test2");
		// WHEN
		List<Company> companies = daoFactory.getCompanyDAO().getAllCompanies();
		// THEN
		Assertions.assertThat(companies).isNotNull();
		Assertions.assertThat(companies).isNotEmpty();
		Assertions.assertThat(companies.size()).isEqualTo(size);
		Assertions.assertThat(companies).contains(company1);
		Assertions.assertThat(companies).contains(company2);
	}
	
	@Test
	public void getAllCompaniesWhenEmptyDataset() throws Exception {
		// GIVEN
		DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
				"src/test/java/com/excilys/persistence/data/getCompaniesEmpty.xml")));
		// WHEN
		List<Company> companies = daoFactory.getCompanyDAO().getAllCompanies();
		// THEN
		Assertions.assertThat(companies).isNotNull();
		Assertions.assertThat(companies).isEmpty();
	}
}
