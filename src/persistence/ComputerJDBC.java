package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.CompanyModel;
import model.ComputerModel;

import com.mysql.jdbc.Statement;

public class ComputerJDBC {

	private Statement statement;
	private JDBCConnection JDBCConnection;

	public ComputerJDBC(JDBCConnection connection) {
		JDBCConnection = connection;
		statement = null;
	}

	public List<ComputerModel> getAllComputers() {

		List<ComputerModel> listComputers = new ArrayList<ComputerModel>();
		ResultSet resultat = null;
		try {
			statement = (Statement) JDBCConnection.getConnection().createStatement();
			resultat = statement.executeQuery("SELECT * FROM computer;");
			while (resultat.next()) {
				long idComputer = resultat.getLong("id");
				String name = resultat.getString("name");
				LocalDateTime introduced;
				introduced = resultat.getTimestamp("introduced") == null ? null : resultat.getTimestamp("introduced").toLocalDateTime();
				LocalDateTime discontinued;
				discontinued = resultat.getTimestamp("discontinued") == null ? null : resultat.getTimestamp("discontinued").toLocalDateTime();
				int companyId = resultat.getInt("company_id");
				ComputerModel model = new ComputerModel(idComputer, name, introduced, discontinued, companyId);
				System.out.println(model);
				listComputers.add(model);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultat != null) {
				try {
					resultat.close();
				} catch (SQLException ignore) {
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return listComputers;
	}

	public List<CompanyModel> getAllCompanies() {
		List<CompanyModel> listCompanies = new ArrayList<CompanyModel>();
		ResultSet resultat = null;
		try {
			statement = (Statement) JDBCConnection.getConnection().createStatement();
			resultat = statement.executeQuery("SELECT * FROM company;");
			while (resultat.next()) {
				long idCompany = resultat.getLong("id");
				String name = resultat.getString("name");
				CompanyModel model = new CompanyModel(idCompany, name);
				System.out.println(model);
				listCompanies.add(model);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultat != null) {
				try {
					resultat.close();
				} catch (SQLException ignore) {
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return listCompanies;
	}

	public ComputerModel getComputerDetails(int idComputer) {
		ComputerModel model = null;
		ResultSet resultat = null;
		try {
			statement = (Statement) JDBCConnection.getConnection().createStatement();
			resultat = statement.executeQuery("SELECT * FROM computer WHERE id = " + idComputer + ";");
			resultat.next();
			long id = resultat.getLong("id");
			String name = resultat.getString("name");
			LocalDateTime introduced;
			introduced = resultat.getTimestamp("introduced") == null ? null : resultat.getTimestamp("introduced").toLocalDateTime();
			LocalDateTime discontinued;
			discontinued = resultat.getTimestamp("discontinued") == null ? null : resultat.getTimestamp("discontinued").toLocalDateTime();
			long companyId = resultat.getLong("company_id");
			model = new ComputerModel(id, name, introduced, discontinued, companyId);
			System.out.println(model);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultat != null) {
				try {
					resultat.close();
				} catch (SQLException ignore) {
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return model;
	}

	public void createComputer(String name) {
		/*long id = computer.getId();
		String name = computer.getName();
		Date introduced = computer.getIntroducedDate();
		Date discontinued = computer.getDiscontinuedDate();
		long companyId = computer.getManufacturer();*/
		long id = 0;
		ResultSet resultat = null;
		try {
			statement = (Statement) JDBCConnection.getConnection().createStatement();
			resultat = statement.executeQuery("SELECT MAX(id) FROM computer;");
			resultat.next();
			id = resultat.getLong(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ignore) {
				}
			}
		}
		try {
			id++;
			statement = (Statement) JDBCConnection.getConnection().createStatement();
			statement.executeUpdate( "INSERT INTO computer (id, name, introduced, discontinued, company_id) "
					+ "VALUES ('" + id + "', '" + name + "', null, null, null);");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ignore) {
				}
			}
		}

	}

	public void updateComputer(long computerId, String name, LocalDateTime introduced, LocalDateTime discontinued, long companyId) {
		try {
			statement = (Statement) JDBCConnection.getConnection().createStatement();
			statement.executeUpdate("UPDATE computer SET name = '" + name + "', introduced = '" + introduced + "', discontinued = '" + discontinued + "', company_id ='" + companyId + "' WHERE id=" + computerId + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}

	public void deleteComputer(long computerId) {
		try {
			statement = (Statement) JDBCConnection.getConnection().createStatement();
			statement.executeUpdate("DELETE FROM computer WHERE id=" + computerId + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}
	
}
