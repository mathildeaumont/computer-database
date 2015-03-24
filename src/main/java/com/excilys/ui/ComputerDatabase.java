package com.excilys.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.excilys.model.CompanyModel;
import com.excilys.model.ComputerModel;
import com.excilys.model.Page;
import com.excilys.service.CompanyService;
import com.excilys.service.CompanyServiceImpl;
import com.excilys.service.ComputerService;
import com.excilys.service.ComputerServiceImpl;

public class ComputerDatabase {

	public static void listToString(List<ComputerModel> computers) {
		StringBuilder sb = new StringBuilder();
		for (ComputerModel computer: computers) {
			sb.append(computer.toString());
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	public static void main(String[] args) throws Exception {
		ComputerService computerService = new ComputerServiceImpl();
		CompanyService companyService = new CompanyServiceImpl();
		String in = "";
		Scanner scanIn = new Scanner(System.in);
		boolean isOk = false;
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
				List<ComputerModel> computers = computerService.getAll();
				listToString(computers);
				break;
			case "companies": 
				System.out.println("Companies list :");
				List<CompanyModel> companies = companyService.getAll();
				for (CompanyModel company : companies) {
					System.out.println(company);
				}
				break;
			case "details":
				System.out.println("Enter a computer id :");
				in = scanIn.nextLine();
				if (Pattern.matches("^\\d+$", in.trim())) {
					id = Long.parseLong(in.trim());
					ComputerModel c = computerService.getById(id);
					System.out.println(c);
				} else {
					System.err.println("A number is required");
				}
				break;
			case "create":
				System.out.println("Enter a computer name :");
				in = scanIn.nextLine();
				name = in.trim();
				introduced = null;
				while (!isOk) {
					System.out.println("Enter an introduced date :");
					in = scanIn.nextLine();
					if (!in.equals("")) {
						if (Pattern.matches("^\\d{4}-(0[1-9]|1[012])-((0[1-9]|[12][0-9]|3[01]) (20|21|22|23|[01]\\d|\\d)(([:][0-5]\\d){1,2})(([:][0-5]\\d){1,2}))$", in.trim())) {
							introduced = LocalDateTime.parse(in.trim(), formatter);
							isOk = true;
						} else {
							System.err.println("Bad date format");
						}
					}
				}
				isOk = false;
				discontinued = null;
				while (!isOk) {
					System.out.println("Enter a discontinued date :");
					in = scanIn.nextLine();
					if (!in.equals("")) {
						if (Pattern.matches("^\\d{4}-(0[1-9]|1[012])-((0[1-9]|[12][0-9]|3[01]) (20|21|22|23|[01]\\d|\\d)(([:][0-5]\\d){1,2})(([:][0-5]\\d){1,2}))$", in.trim())) {
							discontinued = LocalDateTime.parse(in.trim(), formatter);
							isOk = true;
						} else {
							System.err.println("Bad date format");
						}
					}
				}
				computerService.create(name, introduced, discontinued, 1);
				break;
			case "update":
				System.out.println("Enter a computer id :");
				in = scanIn.nextLine();
				if (Pattern.matches("^\\d+$", in.trim())) {
					id = Long.parseLong(in.trim());
				} else {
					System.err.println("A number is required");
					break;
				}
				System.out.println("Enter a name :");
				in = scanIn.nextLine();
				name = in.trim();
				isOk = false;
				introduced = null;
				while (!isOk) {
					System.out.println("Enter an introduced date :");
					in = scanIn.nextLine();
					if (!in.equals("")) {
						if (Pattern.matches("^\\d{4}-(0[1-9]|1[012])-((0[1-9]|[12][0-9]|3[01]) (20|21|22|23|[01]\\d|\\d)(([:][0-5]\\d){1,2})(([:][0-5]\\d){1,2}))$", in.trim())) {
							introduced = LocalDateTime.parse(in.trim(), formatter);
							isOk = true;
						} else {
							System.err.println("Bad date format");
						}
					}
				}
				isOk = false;
				discontinued = null;
				while (!isOk) {
					System.out.println("Enter a discontinued date :");
					in = scanIn.nextLine();
					if (!in.equals("")) {
						if (Pattern.matches("^\\d{4}-(0[1-9]|1[012])-((0[1-9]|[12][0-9]|3[01]) (20|21|22|23|[01]\\d|\\d)(([:][0-5]\\d){1,2})(([:][0-5]\\d){1,2}))$", in.trim())) {
							discontinued = LocalDateTime.parse(in.trim(), formatter);
							isOk = true;
						} else {
							System.err.println("Bad date format");
						}
					}
				}
				System.out.println("Enter a company id :");
				in = scanIn.nextLine();
				companyId = 0;
				if (!in.equals("")) {
					if (Pattern.matches("^\\d+$", in.trim())) {
						companyId = Long.parseLong(in.trim());
					} else {
						System.err.println("A number is required");
						break;
					}
				}
				computerService.update(id, name, introduced, discontinued, companyId);
				break;
			case "delete":
				System.out.println("Enter a computer id :");
				in = scanIn.nextLine();
				if (Pattern.matches("^\\d+$", in.trim())) {
					id = Long.parseLong(in.trim());
					computerService.delete(id);
				} else {
					System.err.println("A number is required");
					break;
				}
				break;
			case "page":
				System.out.println("Page number :");
				in = scanIn.nextLine();
				int nbPage = 1;
				int nbResultByPage = 1;
				if (Pattern.matches("^\\d+$", in.trim())) {
					nbPage = Integer.parseInt(in.trim());
				} else {
					System.err.println("A number is required");
					break;
				}
				System.out.println("Results number by page :");
				in = scanIn.nextLine();
				if (Pattern.matches("^\\d+$", in.trim())) {
					nbResultByPage = Integer.parseInt(in.trim());
				} else {
					System.err.println("A number is required");
					break;
				}
				Page<ComputerModel> page = computerService.page(nbPage, nbResultByPage);
				computers = computerService.getAllByPage(page);
				listToString(computers);
				int nbPageTotal = computerService.getLength() / nbResultByPage;
				System.out.println("page " + nbPage + "/" + nbPageTotal);
				while (!in.equals("stop")) {
					in = scanIn.nextLine();
					switch (in.trim()) {
					case "next" :
						if (nbPage < nbPageTotal) {
							nbPage++;
							page = computerService.page(nbPage, nbResultByPage);
							computers = computerService.getAllByPage(page);
							listToString(computers);
							System.out.println("page " + nbPage + "/" + nbPageTotal);
						}
						break;
					case "previous" :
						if (nbPage > 1) {
							nbPage--;
							page = computerService.page(nbPage, nbResultByPage);
							computers = computerService.getAllByPage(page);
							listToString(computers);
							System.out.println("page " + nbPage + "/" + nbPageTotal);
						}
						break;
					default :
						break;
					}
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
