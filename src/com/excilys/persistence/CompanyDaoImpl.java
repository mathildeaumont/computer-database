package com.excilys.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.mapper.CompanyMapper;
import com.excilys.model.CompanyModel;
import com.mysql.jdbc.Statement;

public class CompanyDaoImpl implements CompanyDao {

	public CompanyDaoImpl() {

	}
	 
	public List<CompanyModel> getAllCompanies() {
		List<CompanyModel> listCompanies = new ArrayList<CompanyModel>();
		ResultSet resultat = null;
		try {
			Connection connection = DaoFactory.INSTANCE.getConnection();
			Statement statement = (Statement) connection.createStatement();
			resultat = statement.executeQuery("SELECT * FROM company;");
			while (resultat.next()) {
				CompanyModel model = CompanyMapper.toModel(resultat);
				listCompanies.add(model);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listCompanies;
	}
}
