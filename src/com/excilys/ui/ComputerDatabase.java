package com.excilys.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.excilys.model.CompanyModel;
import com.excilys.model.ComputerModel;
import com.excilys.persistence.CompanyDao;
import com.excilys.persistence.CompanyDaoImpl;
import com.excilys.persistence.ComputerDao;
import com.excilys.persistence.ComputerDaoImpl;
import com.excilys.persistence.JDBCConnection;

public class ComputerDatabase {
	
	public static void main(String[] args) throws Exception {
		try (JDBCConnection JDBCConnection = new JDBCConnection()) {
			ComputerDao computerDao = new ComputerDaoImpl();
			CompanyDao companyDao = new CompanyDaoImpl();
			String in = "";
			Scanner scanIn = new Scanner(System.in);
			while (!in.equals("exit")) {
				in = scanIn.nextLine();
				long id;
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				switch (in) {
					case "computers": 
						System.out.println("Computers list :");
						List<ComputerModel> computers = computerDao.getAllComputers();
						for (ComputerModel computer : computers) {
							System.out.println(computer);
						}
						break;
					case "companies": 
						System.out.println("Companies list :");
						List<CompanyModel> companies = companyDao.getAllCompanies();
						for (CompanyModel company : companies) {
							System.out.println(company);
						}
						break;
					case "details":
						System.out.println("Enter a computer id :");
						in = scanIn.nextLine();
						try {
							id = Long.parseLong(in);
							ComputerModel c = computerDao.getComputerDetails(id);
							System.out.println(c);
						} catch (NumberFormatException nfe) {
							System.out.println("A number is required");
						}
						break;
					case "create":
						System.out.println("Enter a computer name :");
						in = scanIn.nextLine();
						computerDao.createComputer(in);	
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
						computerDao.updateComputer(id, name, introduced, discontinued, companyId);
						break;
					case "delete":
						System.out.println("Enter a computer id :");
						in = scanIn.nextLine();
						try {
							id = Long.parseLong(in);
							computerDao.deleteComputer(id);
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
}
