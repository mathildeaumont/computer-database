package com.excilys.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.excilys.model.CompanyModel;
import com.excilys.model.ComputerModel;
import com.excilys.model.Page;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

public class ComputerDatabase {

	public static void main(String[] args) throws Exception {
		String in = "";
		Scanner scanIn = new Scanner(System.in);
		while (!in.equals("exit")) {
			in = scanIn.nextLine();
			long id;
			String name;
			LocalDateTime introduced;
			LocalDateTime discontinued;
			long companyId;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			switch (in.trim()) {
			case "computers": 
				System.out.println("Computers list :");
				List<ComputerModel> computers = ComputerService.getAll();
				for (ComputerModel computer : computers) {
					System.out.println(computer);
				}
				break;
			case "companies": 
				System.out.println("Companies list :");
				List<CompanyModel> companies = CompanyService.getAll();
				for (CompanyModel company : companies) {
					System.out.println(company);
				}
				break;
			case "details":
				System.out.println("Enter a computer id :");
				in = scanIn.nextLine();
				try {
					id = Long.parseLong(in.trim());
					ComputerModel c = ComputerService.getById(id);
					System.out.println(c);
				} catch (NumberFormatException nfe) {
					System.err.println("A number is required");
				}
				break;
			case "create":
				System.out.println("Enter a computer name :");
				in = scanIn.nextLine();
				name = in.trim();
				System.out.println("Enter an introduced date :");
				in = scanIn.nextLine();
				introduced = null;
				if (!in.equals("")) {
					try {
						introduced = LocalDateTime.parse(in.trim(), formatter);
					} catch (Exception e) {
						System.err.println("Bad date format");
						break;
					}
				}
				System.out.println("Enter a discontinued date :");
				in = scanIn.nextLine();
				discontinued = null;
				if (!in.equals("")) {
					try {
						discontinued = LocalDateTime.parse(in.trim(), formatter);
					} catch (Exception e) {
						System.err.println("Bad date format");
						break;
					}
				}
				ComputerService.create(name, introduced, discontinued);
				break;
			case "update":
				System.out.println("Enter a computer id :");
				in = scanIn.nextLine();
				try {
					id = Long.parseLong(in.trim());
				} catch (NumberFormatException nfe) {
					System.err.println("A number is required");
					break;
				}
				System.out.println("Enter a name :");
				in = scanIn.nextLine();
				name = in.trim();
				System.out.println("Enter an introduced date :");
				in = scanIn.nextLine();
				introduced = null;
				if (!in.equals("")) {
					try {
						introduced = LocalDateTime.parse(in.trim(), formatter);
					} catch (Exception e) {
						System.err.println("Bad date format");
						break;
					}
				}
				System.out.println("Enter a discontinued date :");
				in = scanIn.nextLine();
				discontinued = null;
				if (!in.equals("")) {
					try {
						discontinued = LocalDateTime.parse(in.trim(), formatter);
					} catch (Exception e) {
						System.err.println("Bad date format");
						break;
					}
				}
				System.out.println("Enter a company id :");
				in = scanIn.nextLine();
				companyId = 0;
				if (!in.equals("")) {
					try {
						companyId = Long.parseLong(in.trim());
					} catch (NumberFormatException nfe) {
						System.err.println("A number is required");
						break;
					}
				}
				ComputerService.update(id, name, introduced, discontinued, companyId);
				break;
			case "delete":
				System.out.println("Enter a computer id :");
				in = scanIn.nextLine();
				try {
					id = Long.parseLong(in.trim());
					ComputerService.delete(id);
				} catch (NumberFormatException nfe) {
					System.err.println("A number is required");
					break;
				}
				break;
			case "exit":
				scanIn.close();
				break;
			default: break;
			}
		}
		Page<ComputerModel> page = new Page<ComputerModel>(10, 10);
		List<ComputerModel> computers = ComputerService.getAllByPage(page);
		for (ComputerModel computer : computers) {
			System.out.println(computer);
		}
	}
}
