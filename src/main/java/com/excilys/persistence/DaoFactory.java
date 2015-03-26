package com.excilys.persistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;


public enum DaoFactory {

	INSTANCE;

	private CompanyDao companyDao;
	private ComputerDao computerDao;
    private final Properties properties = new Properties();
    private BoneCP connectionPool;

	
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
	
	public void closeConnection(Connection connection) {
		try {
			connection.setAutoCommit(true);
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		return connectionPool.getConnection();
	}

	public CompanyDao getCompanyDAO() {
		return companyDao;
	}

	public ComputerDao getComputerDAO() {
		return computerDao;
	}
}
