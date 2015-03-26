package com.excilys.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.mapper.CompanyMapper;
import com.excilys.model.CompanyModel;
import com.excilys.model.CompanyModelImpl;


public class CompanyDaoImpl implements CompanyDao {
	 
	public List<CompanyModel> getAllCompanies() {
		List<CompanyModel> listCompanies = new ArrayList<CompanyModel>();
		ResultSet resultat = null;
		try {
			Connection connection = DaoFactory.INSTANCE.getConnection();
			Statement statement = (Statement) connection.createStatement();
			resultat = statement.executeQuery("SELECT * FROM company;");
			CompanyMapper mapper = new CompanyMapper();
			while (resultat.next()) {
				CompanyModelImpl model = (CompanyModelImpl) mapper.toModel(resultat);
				listCompanies.add(model);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listCompanies;
	}
	
	public void deleteCompany(long companyId) {
		Connection connection = null;
		try {
			connection = DaoFactory.INSTANCE.getConnection();
			PreparedStatement preparedStatement = null;
			preparedStatement = (PreparedStatement) connection.prepareStatement("DELETE FROM computer WHERE company_id = ?;");
			int i = 1;
			preparedStatement.setLong(i++, companyId);
			preparedStatement.execute();
			preparedStatement.close();
			try {
				i = 1;
				PreparedStatement preparedStatement2 = null;
				preparedStatement2 = (PreparedStatement) connection.prepareStatement("DELETE FROM company WHERE id = ?;");
				preparedStatement2.setLong(i++, companyId);
				preparedStatement2.execute();
				preparedStatement2.close();
			} catch (SQLException e) {
				e.printStackTrace();
				connection.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DaoFactory.INSTANCE.closeConnection(connection);
	}
}
