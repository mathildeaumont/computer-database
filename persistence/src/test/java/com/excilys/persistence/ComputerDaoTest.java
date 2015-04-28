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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.excilys.ebi.spring.dbunit.config.DBOperation;
import com.excilys.ebi.spring.dbunit.test.DataSet;
import com.excilys.ebi.spring.dbunit.test.RollbackTransactionalDataSetTestExecutionListener;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.util.DBUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context-persistence-test.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
TransactionalTestExecutionListener.class,
RollbackTransactionalDataSetTestExecutionListener.class })
public class ComputerDaoTest {

	@Autowired
	CompanyDao companyDao;
	
	@Autowired
	ComputerDao computerDao;
	
	@Test
	@DataSet(value = "getComputers.xml", tearDownOperation = DBOperation.DELETE_ALL)
	public void getAllComputersOk() throws Exception {
		// GIVEN
		final int size = 2;
		final Company company = new Company(1, "Company");

		final Computer computer1 = new Computer(1, "Computer1", LocalDateTime.of(
				2012, 5, 1, 0, 0, 0), null, company);
		final Computer computer2 = new Computer(2, "Computer2", null, null, company);
		// WHEN
		List<Computer> computers = computerDao.getAll();
		// THEN
		Assertions.assertThat(computers).isNotNull();
		Assertions.assertThat(computers).isNotEmpty();
		Assertions.assertThat(computers.size()).isEqualTo(size);
		Assertions.assertThat(computers).contains(computer1);
		Assertions.assertThat(computers).contains(computer2);
	}

	@Test
	@DataSet(value = "getComputersEmpty.xml", tearDownOperation = DBOperation.DELETE_ALL)
	public void getAllComputersWhenEmptyDataset() throws Exception {
		// GIVEN
		// WHEN
		List<Computer> computers = computerDao.getAll();
		// THEN
		Assertions.assertThat(computers).isNotNull();
		Assertions.assertThat(computers).isEmpty();
	}

	@Test
	@DataSet(value = "getComputers.xml", tearDownOperation = DBOperation.DELETE_ALL)
	public void countAllComputers() throws Exception {
		// GIVEN
		final int size = 2;
		// WHEN
		final int totalSize = computerDao.getLength();
		// THEN
		Assertions.assertThat(totalSize).isEqualTo(size);
	}
	
	@Test
	@DataSet(value = "getComputersEmpty.xml", tearDownOperation = DBOperation.DELETE_ALL)
	public void countAllComputersWhenEmptyDataset() throws Exception {
		// GIVEN
		// WHEN
		final int totalSize = computerDao.getLength();
		// THEN
		Assertions.assertThat(totalSize).isZero();
	}
	
	@Test
	@DataSet(value = "getComputers.xml", tearDownOperation = DBOperation.DELETE_ALL)
	public void getComputerByIdWhenExist() throws Exception {
		// GIVEN
		final Company company = new Company(1, "Company");
		final String name = "Computer1";
		final LocalDateTime introduced = LocalDateTime.of(2012, 5, 1, 0, 0, 0);
		final int computerId = 1;
		final Computer computer = new Computer(computerId, name, introduced, null, company);
		// WHEN
		Computer comp = computerDao.getDetails(computerId);
		// THEN
		Assertions.assertThat(comp).isEqualTo(computer);
		Assertions.assertThat(comp.getId()).isEqualTo(1);
		Assertions.assertThat(comp.getName()).isEqualTo(name);
		Assertions.assertThat(comp.getIntroducedDate()).isEqualTo(introduced);
		Assertions.assertThat(comp.getDiscontinuedDate()).isNull();
		Assertions.assertThat(comp.getCompany()).isEqualTo(company);
	}
	
	@Test
	@DataSet(value = "getComputers.xml", tearDownOperation = DBOperation.DELETE_ALL)
	public void getComputerByIdWhenDontExist() throws Exception {
		// GIVEN
		final int computerId = 3;
		// WHEN
		Computer comp = computerDao.getDetails(computerId);
		// THEN
		Assertions.assertThat(comp).isNull();
	}
	
	@Test
	@DataSet(value = "createComputer.xml", tearDownOperation = DBOperation.DELETE_ALL)
	public void createComputerWithSuccess() throws Exception {
		// GIVEN
		final String name = "Computer2";
		final LocalDateTime introduced = LocalDateTime.of(2012, 5, 1, 0, 0, 0);
		final Computer computer = new Computer();
		computer.setName(name);
		computer.setIntroducedDate(introduced);
		final int nbComputers = 1;
		// WHEN
		computerDao.create(computer);
		List<Computer> computers = computerDao.getAll();
		// THEN
		Assertions.assertThat(computers.size()).isEqualTo(nbComputers);
	}
	
	@Test
	@DataSet(value = "updateComputer.xml", tearDownOperation = DBOperation.DELETE_ALL)
	public void updateComputerWithSuccess() throws Exception {
		// GIVEN
		final int computerId = 1;
		Computer computer = computerDao.getDetails(computerId);
		final String name = "Test";
		// WHEN
		computer.setName(name);
		computerDao.update(computer);
		computer = computerDao.getDetails(computerId);
		// THEN
		Assertions.assertThat(computer.getName()).isEqualTo(name);
	}
	
	@Test
	@DataSet(value = "deleteComputer.xml", tearDownOperation = DBOperation.DELETE_ALL)
    public void deleteWithSuccess() throws Exception {
        // GIVEN
		final int computerId = 1;
		computerDao.delete(computerId);
        // WHEN
        final List<Computer> computers = computerDao.getAll();
        // THEN
        Assertions.assertThat(computers).isNotNull();
        Assertions.assertThat(computers).isEmpty();
    }
	
	@Test
	@DataSet(value = "deleteComputer.xml", tearDownOperation = DBOperation.DELETE_ALL)
    public void deleteWhenComputerDoesntExist() throws Exception {
        // GIVEN
		final int computerId = 2;
		final int computersNb = 1;
		computerDao.delete(computerId);
        // WHEN
        final List<Computer> computers = computerDao.getAll();
        // THEN
        Assertions.assertThat(computers).isNotNull();
        Assertions.assertThat(computers.size()).isEqualTo(computersNb);
    }
}
