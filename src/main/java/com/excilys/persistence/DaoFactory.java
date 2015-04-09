package com.excilys.persistence;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.exception.PersistenceException;


@Component
public class DaoFactory {

	//INSTANCE;

	private static final Logger LOGGER = LoggerFactory.getLogger(DaoFactory.class);

	private static final ThreadLocal<Connection> CONNECTION = new ThreadLocal<Connection>();

	@Autowired
	private DataSource dataSource;

	@Autowired
	CompanyDao companyDao;

	@Autowired
	ComputerDao computerDao;


	public DaoFactory() {
		if ("TEST".equals(System.getProperty("env"))) {
			try {
				Class.forName("org.h2.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
			}
		} else {
			try {
				Driver monDriver = new com.mysql.jdbc.Driver();
				DriverManager.registerDriver(monDriver);
			} catch (SQLException e2) {
				System.err.println(e2.getMessage());
			}
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		companyDao = new CompanyDaoImpl();
		computerDao = new ComputerDaoImpl();
	}

	public void closeConnection() {
		final Connection connection = CONNECTION.get();
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.error("Failure : Connection closed");
				throw new PersistenceException(e);
			}
			CONNECTION.remove();
			LOGGER.info("Successfully connection closed");
		}
	}

	public Connection getConnection() {
		try {
			if (CONNECTION.get() == null
					|| CONNECTION.get().isClosed()) {
				CONNECTION.set(dataSource.getConnection());
				LOGGER.info("Successfully getting connection");
				return CONNECTION.get();
			} else {
				LOGGER.info("Successfully getting connection");
				return CONNECTION.get();
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}

	public void startTransaction() {
		final Connection connection = CONNECTION.get();
		if (connection != null) {
			try {
				connection.setAutoCommit(false);
			} catch (SQLException e) {
				throw new PersistenceException(e);
			}
		}
	}

	public void endTransaction() {
		final Connection connection = CONNECTION.get();
		if (connection != null) {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				throw new PersistenceException(e);
			}
		}
	}

	public void commit() {
		final Connection connection = CONNECTION.get();
		if (connection != null) {
			try {
				connection.commit();
			} catch (SQLException e) {
				LOGGER.error("Failure : commit");
				throw new PersistenceException(e);
			}
			LOGGER.info("Successfully commit");
		}
	}

	public void rollback() {
		final Connection connection = CONNECTION.get();
		if (connection != null) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				LOGGER.error("Failure : rollback");
				throw new PersistenceException(e);
			}
			LOGGER.info("Successfully rollback");
		}
	}

	public CompanyDao getCompanyDAO() {
		return companyDao;
	}

	public ComputerDao getComputerDAO() {
		return computerDao;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
