package com.excilys.persistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.controller.EditComputer;
import com.excilys.exception.PersistenceException;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;


public enum DaoFactory {

	INSTANCE;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DaoFactory.class);

	private static final ThreadLocal<Connection> CONNECTION = new ThreadLocal<Connection>() {
		@Override
		protected Connection initialValue() {
			try {
				return DaoFactory.INSTANCE.getConnectionPool().getConnection();
			} catch (SQLException e) {
				throw new PersistenceException(e);
			}
		}
	};
	private CompanyDao companyDao;
	private ComputerDao computerDao;
	private final Properties properties = new Properties();
	private BoneCP connectionPool = null;

	private DaoFactory() {
		String config = null;
		if ("TEST".equals(System.getProperty("env"))) {
			try {
				Class.forName("org.h2.Driver");
				config = "config-test.properties";
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
			}
		} else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				config = "config.properties";
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
		companyDao = new CompanyDaoImpl();
		computerDao = new ComputerDaoImpl();
		try {
			InputStream inputStream = DaoFactory.class.getClassLoader().getResourceAsStream(config);
			properties.load(inputStream);
			BoneCPConfig boneCPConfig = new BoneCPConfig();
			String url = properties.getProperty("url");
			String user = properties.getProperty("user");
			String pwd = properties.getProperty("pwd");
			boneCPConfig.setJdbcUrl(url);
			boneCPConfig.setUsername(user);
			boneCPConfig.setPassword(pwd);
			boneCPConfig.setMinConnectionsPerPartition(5);
			boneCPConfig.setMaxConnectionsPerPartition(10);
			boneCPConfig.setPartitionCount(2);
			connectionPool = new BoneCP(boneCPConfig);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		LOGGER.info("Successfully getting connection");
		return CONNECTION.get();
	}

	public BoneCP getConnectionPool() {
		return connectionPool;
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
}
