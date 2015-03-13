package ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import model.CompanyModel;
import model.ComputerModel;
import persistence.ComputerJDBC;
import persistence.JDBCConnection;

public class ComputerDatabase {
	
	public static void main(String[] args) {
		JDBCConnection JDBCConnection = new JDBCConnection();
		ComputerJDBC computerJDBC = new ComputerJDBC(JDBCConnection);
		
		System.out.println("COMPUTERS\n");
		List<ComputerModel> computers = computerJDBC.getAllComputers();
		System.out.println("\nCOMPANIES\n");
		List<CompanyModel> companies = computerJDBC.getAllCompanies();
		System.out.println("\nDETAILS DE L'ORDINATEUR 150\n");
		ComputerModel computer = computerJDBC.getComputerDetails(150);

		//ComputerModel c = new ComputerModel(575, "test", null, null, 1);
		//computerJDBC.createComputer("test");
		
		String str = "1986-04-08 12:30";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
		
		str = "1995-04-08 12:30";
		LocalDateTime dateTime2 = LocalDateTime.parse(str, formatter);
		computerJDBC.updateComputer(580, "test2", dateTime, dateTime2, 1);
		
		computerJDBC.deleteComputer(579);
		
		System.out.println("COMPUTERS\n");
		computers = computerJDBC.getAllComputers();
		
		JDBCConnection.closeConnection();
	}
}
