package persistence;

import java.io.ObjectInputStream.GetField;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.CompanyModel;
import model.ComputerModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class ComputerJDBC {

	private static String url = "jdbc:mysql://localhost/computer-database-db?zeroDateTimeBehavior=convertToNull";
    private static String user = "root";
    private static String pwd ="root";
    private Connection connexion;
    private Statement statement;
    
	public ComputerJDBC() {
		try {
			Class.forName( "com.mysql.jdbc.Driver" );
	    } catch (ClassNotFoundException e) {
	    	e.printStackTrace();
	    }
	    connexion = null;
	    statement = null;
	}
	
	public List<ComputerModel> getAllComputers() {
		List<ComputerModel> listComputers = new ArrayList<ComputerModel>();
		ResultSet resultat = null;
	    try {
	        connexion = (Connection) DriverManager.getConnection(url, user, pwd);
	        statement = (Statement) connexion.createStatement();
	        resultat = statement.executeQuery( "SELECT * FROM computer;" );
	        while (resultat.next()) {
	            int idComputer = resultat.getInt("id");
	            String name = resultat.getString("name");
	            Date introduced = resultat.getDate("introduced");
	            Date discontinued = resultat.getDate("discontinued");
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
	        if (connexion != null) {
	            try {
	                connexion.close();
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
	        connexion = (Connection) DriverManager.getConnection(url, user, pwd);
	        statement = (Statement) connexion.createStatement();
	        resultat = statement.executeQuery( "SELECT * FROM company;" );
	        while (resultat.next()) {
	            int idCompany = resultat.getInt("id");
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
	        if (connexion != null) {
	            try {
	                connexion.close();
	            } catch (SQLException ignore) {
	            }
	        }
	    }
	    return listCompanies;
	}
	
	public static void main(String[] args) {
		ComputerJDBC computerJDBC = new ComputerJDBC();
		System.out.println("COMPUTERS\n");
		List<ComputerModel> computers = computerJDBC.getAllComputers();
		System.out.println("\nCOMPANIES\n");
		List<CompanyModel> companies = computerJDBC.getAllCompanies();
	}
}
