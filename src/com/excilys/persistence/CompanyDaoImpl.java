package com.excilys.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.model.CompanyModel;
import com.mysql.jdbc.Statement;

public class CompanyDaoImpl implements CompanyDao {

	private Statement statement;
	private JDBCConnection JDBCConnection;
	
	public CompanyDaoImpl() {
		statement = null;
	}
	 
	public List<CompanyModel> getAllCompanies() {
		List<CompanyModel> listCompanies = new ArrayList<CompanyModel>();
		ResultSet resultat = null;
		try {
			JDBCConnection = DaoFactory.INSTANCE.getConnection();
			statement = (Statement) JDBCConnection.getConnection().createStatement();
			resultat = statement.executeQuery("SELECT * FROM company;");
			while (resultat.next()) {
				long idCompany = resultat.getLong("id");
				String name = resultat.getString("name");
				CompanyModel model = new CompanyModel(idCompany, name);
				listCompanies.add(model);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listCompanies;
	}
}
