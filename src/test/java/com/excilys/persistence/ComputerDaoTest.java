package com.excilys.persistence;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import com.excilys.model.CompanyModelImpl;
import com.excilys.model.ComputerModel;
import com.excilys.model.ComputerModelImpl;
import com.excilys.util.DBUtil;

@RunWith(MockitoJUnitRunner.class)
public class ComputerDaoTest {

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
	public void getAllComputersOk() throws Exception {
		// GIVEN
		DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
				"src/test/java/com/excilys/persistence/data/getComputers.xml")));
		final int size = 2;
		final CompanyModelImpl company = new CompanyModelImpl(1, "Company");

		final ComputerModel computer1 = new ComputerModelImpl(1, "Computer1", LocalDateTime.of(
				2012, 5, 1, 0, 0, 0), null, company);
		final ComputerModel computer2 = new ComputerModelImpl(2, "Computer2", null, null, company);
		// WHEN
		List<ComputerModel> computers = DaoFactory.INSTANCE.getComputerDAO().getAllComputers();
		// THEN
		Assertions.assertThat(computers).isNotNull();
		Assertions.assertThat(computers).isNotEmpty();
		Assertions.assertThat(computers.size()).isEqualTo(size);
		Assertions.assertThat(computers).contains(computer1);
		Assertions.assertThat(computers).contains(computer2);
	}

	@Test
	public void getAllComputersWhenEmptyDataset() throws Exception {
		// GIVEN
		DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
				"src/test/java/com/excilys/persistence/data/getComputersEmpty.xml")));
		// WHEN
		List<ComputerModel> computers = DaoFactory.INSTANCE.getComputerDAO().getAllComputers();
		// THEN
		Assertions.assertThat(computers).isNotNull();
		Assertions.assertThat(computers).isEmpty();
	}

	@Test
	public void countAllComputers() throws Exception {
		// GIVEN
		DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
				"src/test/java/com/excilys/persistence/data/getComputers.xml")));
		final int size = 2;
		// WHEN
		final int totalSize = DaoFactory.INSTANCE.getComputerDAO().getLength();
		// THEN
		Assertions.assertThat(totalSize).isEqualTo(size);
	}
	
	@Test
	public void countAllComputersWhenEmptyDataset() throws Exception {
		// GIVEN
		DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
				"src/test/java/com/excilys/persistence/data/getComputersEmpty.xml")));
		// WHEN
		final int totalSize = DaoFactory.INSTANCE.getComputerDAO().getLength();
		// THEN
		Assertions.assertThat(totalSize).isZero();
	}
	
	@Test
	public void getComputerByIdWhenExist() throws Exception {
		// GIVEN
		DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
				"src/test/java/com/excilys/persistence/data/getComputers.xml")));
		final CompanyModelImpl company = new CompanyModelImpl(1, "Company");
		final String name = "Computer1";
		final LocalDateTime introduced = LocalDateTime.of(2012, 5, 1, 0, 0, 0);
		final int computerId = 1;
		final ComputerModel computer = new ComputerModelImpl(computerId, name, introduced, null, company);
		// WHEN
		ComputerModel comp = DaoFactory.INSTANCE.getComputerDAO().getComputerDetails(computerId);
		// THEN
		Assertions.assertThat(comp).isEqualTo(computer);
		Assertions.assertThat(comp.getId()).isEqualTo(1);
		Assertions.assertThat(comp.getName()).isEqualTo(name);
		Assertions.assertThat(comp.getIntroducedDate()).isEqualTo(introduced);
		Assertions.assertThat(comp.getDiscontinuedDate()).isNull();
		Assertions.assertThat(comp.getCompany()).isEqualTo(company);
	}
	
	@Test
	public void getComputerByIdWhenDontExist() throws Exception {
		// GIVEN
		DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
				"src/test/java/com/excilys/persistence/data/getComputers.xml")));
		final int computerId = 3;
		// WHEN
		ComputerModel comp = DaoFactory.INSTANCE.getComputerDAO().getComputerDetails(computerId);
		// THEN
		Assertions.assertThat(comp).isNull();
	}
	
	@Test
	public void createComputerWithSuccess() throws Exception {
		// GIVEN
		DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
				"src/test/java/com/excilys/persistence/data/createComputer.xml")));
		final String name = "Computer2";
		final LocalDateTime introduced = LocalDateTime.of(2012, 5, 1, 0, 0, 0);
		final ComputerModel computer = new ComputerModelImpl();
		computer.setName(name);
		computer.setIntroducedDate(introduced);
		final int nbComputers = 1;
		// WHEN
		DaoFactory.INSTANCE.getComputerDAO().createComputer(computer);
		List<ComputerModel> computers = DaoFactory.INSTANCE.getComputerDAO().getAllComputers();
		// THEN
		Assertions.assertThat(computers.size()).isEqualTo(nbComputers);
	}
	
	@Test
	public void updateComputerWithSuccess() throws Exception {
		// GIVEN
		DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
				"src/test/java/com/excilys/persistence/data/updateComputer.xml")));
		final int computerId = 1;
		ComputerModel computer = DaoFactory.INSTANCE.getComputerDAO().getComputerDetails(computerId);
		final String name = "Test";
		// WHEN
		computer.setName(name);
		DaoFactory.INSTANCE.getComputerDAO().updateComputer(computer);
		computer = DaoFactory.INSTANCE.getComputerDAO().getComputerDetails(computerId);
		// THEN
		Assertions.assertThat(computer.getName()).isEqualTo(name);
	}
}
