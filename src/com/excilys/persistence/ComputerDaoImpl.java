package com.excilys.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.excilys.mapper.ComputerMapper;
import com.excilys.model.ComputerModel;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class ComputerDaoImpl implements ComputerDao {

	public ComputerDaoImpl() {
	
	}
	
	public List<ComputerModel> getAllComputers() {
		List<ComputerModel> listComputers = new ArrayList<ComputerModel>();
		ResultSet resultat = null;
		try {
			Connection connection = DaoFactory.INSTANCE.getConnection();
			Statement statement = (Statement) connection.createStatement();
			resultat = statement.executeQuery("SELECT * FROM computer as compu left "
					+ "outer join company as compa ON compu.company_id = compa.id ORDER by compu.id;");
			while (resultat.next()) {
				ComputerModel model = ComputerMapper.toModel(resultat);
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
			Connection connection = DaoFactory.INSTANCE.getConnection();
			PreparedStatement preparedStatement = null;
			preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM computer as compu left "
					+ "outer join company as compa ON compu.company_id = compa.id WHERE compu.id = ? ORDER by compu.id;");
			int i = 1;
			preparedStatement.setLong(i++, idComputer);
			resultat = preparedStatement.executeQuery();
			resultat.next();
			model = ComputerMapper.toModel(resultat);
			resultat.close();
			preparedStatement.close();
		} catch (SQLException e) {
			System.err.println("This computer doesn\'t exist.");
		}
		return model;
	}

	public void createComputer(String name) {
		try {
			Connection connection = DaoFactory.INSTANCE.getConnection();
			PreparedStatement preparedStatement = null;
			int i = 1;
			preparedStatement = (PreparedStatement) connection.prepareStatement("INSERT INTO computer (name, introduced, discontinued, company_id) "
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
			Connection connection = DaoFactory.INSTANCE.getConnection();
			PreparedStatement preparedStatement = null;
			preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM computer WHERE id = ?;");
			int i = 1;
			preparedStatement.setLong(i++, computerId);
			ResultSet resultat = preparedStatement.executeQuery();
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
			i = 1;
			preparedStatement = (PreparedStatement) connection.prepareStatement("UPDATE computer set name=?, introduced=?, discontinued=?, company_id=? WHERE id=?;");
			preparedStatement.setString(i++, name);
			preparedStatement.setTimestamp(i++, Timestamp.valueOf(introduced));
			preparedStatement.setTimestamp(i++, Timestamp.valueOf(discontinued));
			preparedStatement.setLong(i++, companyId);
			preparedStatement.setLong(i++, computerId);
			preparedStatement.execute();
			preparedStatement.close();
		} catch (SQLException e) {
			//e.printStackTrace();
			System.err.println("Invalid date");
		}
	}

	public void deleteComputer(long computerId) {
		try {
			Connection connection = DaoFactory.INSTANCE.getConnection();
			PreparedStatement preparedStatement = null;
			int i = 1;
			preparedStatement = (PreparedStatement) connection.prepareStatement("DELETE FROM computer WHERE id=?;");
					preparedStatement.setLong(i++, computerId);
					preparedStatement.execute();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
