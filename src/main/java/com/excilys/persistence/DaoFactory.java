package com.excilys.persistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public enum DaoFactory {

	INSTANCE;

	private CompanyDao companyDao;
	private ComputerDao computerDao;
    private Connection connection;
    private final Properties properties = new Properties();
	
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
	    	String url = properties.getProperty("url");
	    	String user = properties.getProperty("user");
	    	String pwd = properties.getProperty("pwd");
			connection = (Connection) DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public CompanyDao getCompanyDAO() {
		return companyDao;
	}

	public ComputerDao getComputerDAO() {
		return computerDao;
	}
}
