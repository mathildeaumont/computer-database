package ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Scanner;
import persistence.ComputerJDBC;
import persistence.JDBCConnection;

public class ComputerDatabase {
	
	public static void main(String[] args) {
		//JDBCConnection JDBCConnection = //new JDBCConnection();
		ComputerJDBC computerJDBC = new ComputerJDBC(JDBCConnection.getInstance());
	
		String in = "";
		Scanner scanIn = new Scanner(System.in);
		while (!in.equals("exit")) {
			in = scanIn.nextLine();
			long id;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			switch (in) {
				case "computers": 
					System.out.println("Computers list :");
					computerJDBC.getAllComputers();
					break;
				case "companies": 
					System.out.println("Companies list :");
					computerJDBC.getAllCompanies();
					break;
				case "details":
					System.out.println("Enter a computer id :");
					in = scanIn.nextLine();
					try {
						id = Long.parseLong(in);
						computerJDBC.getComputerDetails(id);
					} catch (NumberFormatException nfe) {
						System.out.println("A number is required");
					}
					break;
				case "create":
					System.out.println("Enter a computer name :");
					in = scanIn.nextLine();
					computerJDBC.createComputer(in);	
					break;
				case "update":
					System.out.println("Enter a computer id :");
					in = scanIn.nextLine();
					try {
						id = Long.parseLong(in);
					} catch (NumberFormatException nfe) {
						System.out.println("A number is required");
						break;
					}
					System.out.println("Enter a name :");
					in = scanIn.nextLine();
					String name = in;
					System.out.println("Enter an introduced date :");
					in = scanIn.nextLine();
					LocalDateTime introduced;
					try {
						introduced = LocalDateTime.parse(in, formatter);
					} catch (Exception e) {
						System.out.println("Bad date format");
						break;
					}
					System.out.println("Enter a discontinued date :");
					in = scanIn.nextLine();
					LocalDateTime discontinued;
					try {
						discontinued = LocalDateTime.parse(in, formatter);
					} catch (Exception e) {
						System.out.println("Bad date format");
						break;
					}
					System.out.println("Enter a company id :");
					in = scanIn.nextLine();
					long companyId;
					try {
						companyId = Long.parseLong(in);
					} catch (NumberFormatException nfe) {
						System.out.println("A number is required");
						break;
					}
					computerJDBC.updateComputer(id, name, introduced, discontinued, companyId);
					break;
				case "delete":
					System.out.println("Enter a computer id :");
					in = scanIn.nextLine();
					try {
						id = Long.parseLong(in);
						computerJDBC.deleteComputer(id);
					} catch (NumberFormatException nfe) {
						System.out.println("A number is required");
						break;
					}
					break;
				case "exit":
					scanIn.close();
					break;
				default: break;
			}
		}
	}
}
