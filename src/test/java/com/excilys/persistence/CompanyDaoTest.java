package com.excilys.persistence;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.excilys.model.CompanyModel;
import com.excilys.util.DBUtil;


@RunWith(MockitoJUnitRunner.class)
public class CompanyDaoTest {

	@BeforeClass
	public static void setUpDB() {
		System.setProperty("env", "TEST");
		try {
			DBUtil.executeSqlFile("ressources/script.sql", DBUtil.getConnection());
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
		// WHEN
		 List<CompanyModel> companies = DaoFactory.INSTANCE.getCompanyDAO().getAllCompanies();
		// THEN
		Assert.assertNotNull(companies);
		Assert.assertEquals(companies.size(), 2);
		
	}
}
