package com.excilys.persistence;


public enum DaoFactory {

	INSTANCE;

	private DaoFactory() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public JDBCConnection getConnection() {
		return new JDBCConnection();
	}


	public CompanyDao getCompanyDAO() {
		return new CompanyDaoImpl();
	}

	public ComputerDaoImpl getComputerDAO() {
		return new ComputerDaoImpl();
	}
}
