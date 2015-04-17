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
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.util.DBUtil;

@RunWith(MockitoJUnitRunner.class)
public class ComputerDaoTest {

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
	public void getAllComputersOk() throws Exception {
		// GIVEN
		DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
				"src/test/java/com/excilys/persistence/data/getComputers.xml")));
		final int size = 2;
		final Company company = new Company(1, "Company");

		final Computer computer1 = new Computer(1, "Computer1", LocalDateTime.of(
				2012, 5, 1, 0, 0, 0), null, company);
		final Computer computer2 = new Computer(2, "Computer2", null, null, company);
		// WHEN
		List<Computer> computers = daoFactory.getComputerDAO().getAll();
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
		List<Computer> computers = daoFactory.getComputerDAO().getAll();
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
		final int totalSize = daoFactory.getComputerDAO().getLength();
		// THEN
		Assertions.assertThat(totalSize).isEqualTo(size);
	}
	
	@Test
	public void countAllComputersWhenEmptyDataset() throws Exception {
		// GIVEN
		DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
				"src/test/java/com/excilys/persistence/data/getComputersEmpty.xml")));
		// WHEN
		final int totalSize = daoFactory.getComputerDAO().getLength();
		// THEN
		Assertions.assertThat(totalSize).isZero();
	}
	
	@Test
	public void getComputerByIdWhenExist() throws Exception {
		// GIVEN
		DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
				"src/test/java/com/excilys/persistence/data/getComputers.xml")));
		final Company company = new Company(1, "Company");
		final String name = "Computer1";
		final LocalDateTime introduced = LocalDateTime.of(2012, 5, 1, 0, 0, 0);
		final int computerId = 1;
		final Computer computer = new Computer(computerId, name, introduced, null, company);
		// WHEN
		Computer comp = daoFactory.getComputerDAO().getDetails(computerId);
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
		Computer comp = daoFactory.getComputerDAO().getDetails(computerId);
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
		final Computer computer = new Computer();
		computer.setName(name);
		computer.setIntroducedDate(introduced);
		final int nbComputers = 1;
		// WHEN
		daoFactory.getComputerDAO().create(computer);
		List<Computer> computers = daoFactory.getComputerDAO().getAll();
		// THEN
		Assertions.assertThat(computers.size()).isEqualTo(nbComputers);
	}
	
	@Test
	public void updateComputerWithSuccess() throws Exception {
		// GIVEN
		DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
				"src/test/java/com/excilys/persistence/data/updateComputer.xml")));
		final int computerId = 1;
		Computer computer = daoFactory.getComputerDAO().getDetails(computerId);
		final String name = "Test";
		// WHEN
		computer.setName(name);
		daoFactory.getComputerDAO().update(computer);
		computer = daoFactory.getComputerDAO().getDetails(computerId);
		// THEN
		Assertions.assertThat(computer.getName()).isEqualTo(name);
	}
	
	@Test
    public void deleteWithSuccess() throws Exception {
        // GIVEN
        DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
        		"src/test/java/com/excilys/persistence/data/deleteComputer.xml")));
		final int computerId = 1;
		daoFactory.getComputerDAO().delete(computerId);
        // WHEN
        final List<Computer> computers = daoFactory.getComputerDAO().getAll();
        // THEN
        Assertions.assertThat(computers).isNotNull();
        Assertions.assertThat(computers).isEmpty();
    }
	
	@Test
    public void deleteWhenComputerDoesntExist() throws Exception {
        // GIVEN
        DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
        		"src/test/java/com/excilys/persistence/data/deleteComputer.xml")));
		final int computerId = 2;
		final int computersNb = 1;
		daoFactory.getComputerDAO().delete(computerId);
        // WHEN
        final List<Computer> computers = daoFactory.getComputerDAO().getAll();
        // THEN
        Assertions.assertThat(computers).isNotNull();
        Assertions.assertThat(computers.size()).isEqualTo(computersNb);
    }
}
