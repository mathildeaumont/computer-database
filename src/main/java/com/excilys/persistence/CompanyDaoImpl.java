package com.excilys.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.mapper.CompanyMapper;
import com.excilys.model.CompanyModel;
import com.excilys.model.CompanyModelImpl;

@Repository
public class CompanyDaoImpl implements CompanyDao {

	@Autowired
	DaoFactory daoFactory;
	
	public List<CompanyModel> getAllCompanies() {
		List<CompanyModel> listCompanies = new ArrayList<CompanyModel>();
		ResultSet resultat = null;
		Connection connection = null;
		try {
			connection = daoFactory.getConnection();
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
		} finally {
			daoFactory.closeConnection();
		}
		return listCompanies;
	}

	@Transactional
	public void deleteCompany(long companyId) {
		Connection connection = null;
		connection = daoFactory.getConnection();
		PreparedStatement preparedStatement = null;
		int i = 1;
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement("DELETE FROM computer WHERE company_id = ?;");
			preparedStatement.setLong(i++, companyId);
			preparedStatement.execute();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		i = 1;
		PreparedStatement preparedStatement2 = null;
		try {
			preparedStatement2 = (PreparedStatement) connection.prepareStatement("DELETE FROM company WHERE id = ?;");
			preparedStatement2.setLong(i++, companyId);
			preparedStatement2.execute();
			preparedStatement2.close();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
