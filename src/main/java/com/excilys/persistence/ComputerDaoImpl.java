package com.excilys.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.excilys.mapper.ComputerMapper;
import com.excilys.model.ComputerModel;
import com.excilys.model.ComputerModelImpl;
import com.excilys.model.Page;


public class ComputerDaoImpl implements ComputerDao {
	
	public int getLength() {
		int i = 0;
		ResultSet resultat = null;
		Connection connection = null;
		try {
			connection = DaoFactory.INSTANCE.getConnection();
			Statement statement = (Statement) connection.createStatement();
			resultat = statement.executeQuery("SELECT COUNT(*) as Total FROM computer");
			resultat.next();
			i = resultat.getInt("Total");
			resultat.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DaoFactory.INSTANCE.closeConnection();
		}
		return i;
	}
	
	public List<ComputerModel> getAllComputers() {
		List<ComputerModel> listComputers = new ArrayList<ComputerModel>();
		ResultSet resultat = null;
		Connection connection = null;
		try {
			connection = DaoFactory.INSTANCE.getConnection();
			Statement statement = (Statement) connection.createStatement();
			resultat = statement.executeQuery("SELECT * FROM computer as compu left "
					+ "outer join company as compa ON compu.company_id = compa.id ORDER by compu.id;");
			ComputerMapper mapper = new ComputerMapper();
			while (resultat.next()) {
				ComputerModelImpl model = (ComputerModelImpl) mapper.toModel(resultat);
				listComputers.add(model);
			}
			resultat.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DaoFactory.INSTANCE.closeConnection();
		}
		return listComputers;
	}
	
	public List<ComputerModel> getAllComputersByPage(Page<ComputerModel> page, String order, String direction, String search) {
		List<ComputerModel> listComputers = new ArrayList<ComputerModel>();
		ResultSet result = null;
		Connection connection = null;
		try {
			connection = DaoFactory.INSTANCE.getConnection();
			PreparedStatement preparedStatement = null;
			preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM computer as compu left "
					+ "outer join company as company ON compu.company_id = company.id WHERE compu.name LIKE '%" + search + "%' OR company.name LIKE '%" + search + "%' ORDER BY " + order + " " + direction + " LIMIT ? OFFSET ?;");
			int i = 1;
			preparedStatement.setLong(i++, page.getNbResults());
			preparedStatement.setLong(i++, page.getOffset());
			result = preparedStatement.executeQuery();
			ComputerMapper mapper = new ComputerMapper();
			while (result.next()) {
				ComputerModelImpl model = (ComputerModelImpl) mapper.toModel(result);
				listComputers.add(model);
			}
			result.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DaoFactory.INSTANCE.closeConnection();
		}
		return listComputers;
	}
	
	public int getLength(String search) {
		int i = 0;
		ResultSet resultat = null;
		Connection connection = null;
		try {
			connection = DaoFactory.INSTANCE.getConnection();
			PreparedStatement preparedStatement = null;
			preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT COUNT(*) as Total FROM computer as compu left "
					+ "outer join company as compa ON compu.company_id = compa.id WHERE compu.name LIKE '%" + search + "%' OR compa.name LIKE '%" + search + "%' ;");
			resultat = preparedStatement.executeQuery();
			resultat.next();
			i = resultat.getInt("Total");
			resultat.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DaoFactory.INSTANCE.closeConnection();
		}
		return i;
	}

	public ComputerModel getComputerDetails(long idComputer) {
		ComputerModel model = null;
		ResultSet resultat = null;
		Connection connection = null;
		try {
			connection = DaoFactory.INSTANCE.getConnection();
			PreparedStatement preparedStatement = null;
			preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM computer as compu left "
					+ "outer join company as compa ON compu.company_id = compa.id WHERE compu.id = ? ORDER by compu.id;");
			int i = 1;
			preparedStatement.setLong(i++, idComputer);
			resultat = preparedStatement.executeQuery();
			if (resultat.first()) {
				ComputerMapper mapper = new ComputerMapper();
				model = (ComputerModelImpl) mapper.toModel(resultat);
			}
			resultat.close();
			preparedStatement.close();
		} catch (SQLException e) {
			System.err.println("This computer doesn\'t exist.");
		} finally {
			DaoFactory.INSTANCE.closeConnection();
		}
		return model;
	}

	public void createComputer(ComputerModel computer) {
		Connection connection = null;
		try {
			connection = DaoFactory.INSTANCE.getConnection();
			PreparedStatement preparedStatement = null;
			int i = 1;
			String name = computer.getName();
			preparedStatement = (PreparedStatement) connection.prepareStatement("INSERT INTO computer (name, introduced, discontinued, company_id)"
					+ "VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(i++, name);
			if (computer.getIntroducedDate() != null) {
				preparedStatement.setTimestamp(i++, Timestamp.valueOf(computer.getIntroducedDate()));
			} else {
				preparedStatement.setTimestamp(i++, null);
			}
			if (computer.getDiscontinuedDate() != null) {
				preparedStatement.setTimestamp(i++, Timestamp.valueOf(computer.getDiscontinuedDate()));
			} else {
				preparedStatement.setTimestamp(i++, null);
			}
			if (computer.getCompany() != null) {
				preparedStatement.setLong(i++, computer.getCompany().getId());
			} else {
				preparedStatement.setString(i++, null);
			}
			preparedStatement.execute();
			final ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				computer.setId(generatedKeys.getLong(1));
			}
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DaoFactory.INSTANCE.closeConnection();
		}
	}

	public void updateComputer(ComputerModel computer) {
		Connection connection = null;
		try {
			connection = DaoFactory.INSTANCE.getConnection();
			PreparedStatement preparedStatement = null;
			preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM computer WHERE id = ?;");
			int i = 1;
			long computerId = computer.getId();
			preparedStatement.setLong(i++, computerId);
			ResultSet resultat = preparedStatement.executeQuery();
			resultat.next();
			String name = computer.getName();
			if (name.equals("")) {
				name = resultat.getString("name");
			}
			LocalDateTime introduced = computer.getIntroducedDate();
			if (introduced == null) {
				introduced = resultat.getTimestamp("introduced") == null ? null : resultat.getTimestamp("introduced").toLocalDateTime();
			}
			LocalDateTime discontinued = computer.getDiscontinuedDate();
			if (discontinued == null) {
				discontinued = resultat.getTimestamp("discontinued") == null ? null : resultat.getTimestamp("discontinued").toLocalDateTime();
			}
			long companyId = computer.getCompany().getId();
			if (companyId == 0) {
				companyId = resultat.getLong("company_id");
			}
			i = 1;
			PreparedStatement preparedStatement2 = null;
			preparedStatement2 = (PreparedStatement) connection.prepareStatement("UPDATE computer set name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?;");
			preparedStatement2.setString(i++, name);
			if (introduced != null) {
				preparedStatement2.setTimestamp(i++, Timestamp.valueOf(introduced));
			} else {
				preparedStatement2.setTimestamp(i++, null);
			}
			if (discontinued != null) {
				preparedStatement2.setTimestamp(i++, Timestamp.valueOf(discontinued));
			} else {
				preparedStatement2.setTimestamp(i++, null);
			}
			if (computer.getCompany() != null) {
				preparedStatement2.setLong(i++, companyId);
			} else {
				preparedStatement2.setString(i++, null);
			}
			preparedStatement2.setLong(i++, computerId);
			preparedStatement2.execute();
			preparedStatement2.close();
		} catch (SQLException e) {
			System.err.println("Invalid request");
		} finally {
			DaoFactory.INSTANCE.closeConnection();
		}
	}

	public void deleteComputer(long computerId) {
		Connection connection = null;
		try {
			connection = DaoFactory.INSTANCE.getConnection();
			PreparedStatement preparedStatement = null;
			int i = 1;
			preparedStatement = (PreparedStatement) connection.prepareStatement("DELETE FROM computer WHERE id = ?;");
					preparedStatement.setLong(i++, computerId);
					preparedStatement.execute();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DaoFactory.INSTANCE.closeConnection();
		}
	}
	

	
}
