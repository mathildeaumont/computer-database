package com.excilys.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.excilys.model.CompanyModel;
import com.excilys.model.ComputerModel;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class ComputerDaoImpl implements ComputerDao {

	/*private Statement statement;
	private JDBCConnection JDBCConnection;*/
	 
	public ComputerDaoImpl() {
	
	}
	
	public List<ComputerModel> getAllComputers() {
		List<ComputerModel> listComputers = new ArrayList<ComputerModel>();
		ResultSet resultat = null;
		try {
			JDBCConnection JDBCConnection = DaoFactory.INSTANCE.getConnection();
			Statement statement = (Statement) JDBCConnection.getConnection().createStatement();
			resultat = statement.executeQuery("SELECT * FROM computer;");
			while (resultat.next()) {
				long idComputer = resultat.getLong("id");
				String name = resultat.getString("name");
				LocalDateTime introduced;
				introduced = resultat.getTimestamp("introduced") == null ? null : resultat.getTimestamp("introduced").toLocalDateTime();
				LocalDateTime discontinued;
				discontinued = resultat.getTimestamp("discontinued") == null ? null : resultat.getTimestamp("discontinued").toLocalDateTime();
				long companyId = resultat.getLong("company_id");
				CompanyModel company;
				if (companyId != 0) {
					ResultSet resultat2 = null;
					Statement statement2 = (Statement) JDBCConnection.getConnection().createStatement();
					resultat2 = statement2.executeQuery("SELECT * FROM company WHERE id ='" + companyId + "'");
					resultat2.next();			
					company = new CompanyModel(resultat2.getLong("id"), resultat2.getString("name"));
					resultat2.close();
				} else {
					company = new CompanyModel();
				}
				ComputerModel model = new ComputerModel(idComputer, name, introduced, discontinued, company);
				listComputers.add(model);
			}
			resultat.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listComputers;
	}

	public ComputerModel getComputerDetails(long idComputer) {
		ComputerModel model = null;
		ResultSet resultat = null;
		try {
			JDBCConnection JDBCConnection = DaoFactory.INSTANCE.getConnection();
			Statement statement = (Statement) JDBCConnection.getConnection().createStatement();
			resultat = statement.executeQuery("SELECT * FROM computer WHERE id = " + idComputer + ";");
			resultat.next();
			long id = resultat.getLong("id");
			String name = resultat.getString("name");
			LocalDateTime introduced;
			introduced = resultat.getTimestamp("introduced") == null ? null : resultat.getTimestamp("introduced").toLocalDateTime();
			LocalDateTime discontinued;
			discontinued = resultat.getTimestamp("discontinued") == null ? null : resultat.getTimestamp("discontinued").toLocalDateTime();
			long companyId = resultat.getLong("company_id");
			CompanyModel company;
			if (companyId != 0) {
				ResultSet resultat2 = null;
				Statement statement2 = (Statement) JDBCConnection.getConnection().createStatement();
				resultat2 = statement2.executeQuery("SELECT * FROM company WHERE id ='" + companyId + "'");
				resultat2.next();
				company = new CompanyModel(resultat2.getLong("id"), resultat2.getString("name"));
				statement2.close();
			} else {
				company = new CompanyModel();
			}
			model = new ComputerModel(id, name, introduced, discontinued, company);
			resultat.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println("This computer doesn\'t exist.");
		}
		return model;
	}

	public void createComputer(String name) {
		try {
			JDBCConnection JDBCConnection = DaoFactory.INSTANCE.getConnection();
			PreparedStatement preparedStatement = null;
			int i = 1;
			preparedStatement = (PreparedStatement) JDBCConnection.getConnection()
					.prepareStatement("INSERT INTO computer (name, introduced, discontinued, company_id) "
					+ "VALUES (?, null, null, null)");
			preparedStatement.setString(i++, name);
			preparedStatement.execute();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void updateComputer(long computerId, String name, LocalDateTime introduced, LocalDateTime discontinued, long companyId) {
		try {
			JDBCConnection JDBCConnection = DaoFactory.INSTANCE.getConnection();
			Statement statement = (Statement) JDBCConnection.getConnection().createStatement();
			ResultSet resultat = statement.executeQuery("SELECT * FROM computer WHERE id = " + computerId + ";");
			resultat.next();
			if (name.equals("")) {
				name = resultat.getString("name");
			}
			if (introduced == null) {
				introduced = resultat.getTimestamp("introduced") == null ? null : resultat.getTimestamp("introduced").toLocalDateTime();
			}
			if (discontinued == null) {
				discontinued = resultat.getTimestamp("discontinued") == null ? null : resultat.getTimestamp("discontinued").toLocalDateTime();
			}
			PreparedStatement preparedStatement = null;
			preparedStatement = (PreparedStatement) JDBCConnection.getConnection()
					.prepareStatement("UPDATE computer set name=?, introduced=?, discontinued=?, company_id=? WHERE id=?;");
			preparedStatement.setString(1, name);
			preparedStatement.setTimestamp(2, Timestamp.valueOf(introduced));
			preparedStatement.setTimestamp(3, Timestamp.valueOf(discontinued));
			preparedStatement.setLong(4, companyId);
			preparedStatement.setLong(5, computerId);
			preparedStatement.execute();
			preparedStatement.close();
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("Invalid date");
		}
	}

	public void deleteComputer(long computerId) {
		try {
			JDBCConnection JDBCConnection = DaoFactory.INSTANCE.getConnection();
			PreparedStatement preparedStatement = null;
			preparedStatement = (PreparedStatement) JDBCConnection.getConnection()
					.prepareStatement("DELETE FROM computer WHERE id=?;");
					preparedStatement.setLong(1, computerId);
					preparedStatement.execute();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
