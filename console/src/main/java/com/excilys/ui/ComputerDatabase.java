package com.excilys.ui;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.springframework.stereotype.Component;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.Page;
import com.excilys.service.CompanyService;
import com.excilys.service.CompanyServiceImpl;
import com.excilys.service.ComputerService;
import com.excilys.service.ComputerServiceImpl;
import com.excilys.webservice.ComputerWebservice;

@Component
public class ComputerDatabase {

	private final URL url;
	private final QName qname;
	private final Service service;
	private final ComputerWebservice ws;
	
	public ComputerWebservice getWebservice() {
		return ws;
	}
	public ComputerDatabase() throws MalformedURLException {
		url = new URL("http://localhost:9898/webservice/computers?wsdl");
		qname = new QName("http://webservice.excilys.com/", "ComputerWebserviceImplService");
		service = Service.create(url, qname);
		ws = service.getPort(ComputerWebservice.class);
	}
	 
	public static void listToString(List<Computer> computers) {
		StringBuilder sb = new StringBuilder();
		for (Computer computer: computers) {
			sb.append(computer.toString());
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	public static void main(String[] args) throws Exception {
		ComputerDatabase cd = new ComputerDatabase();
		ComputerWebservice webservice = cd.getWebservice();
		//ComputerService computerService = new ComputerServiceImpl();
		//CompanyService companyService = new CompanyServiceImpl();
		String in = "";
		Scanner scanIn = new Scanner(System.in);
		boolean isOk = false;
		while (!in.equals("exit")) {
			in = scanIn.nextLine();
			long id;
			String name;
			String introduced;
			String discontinued;
			long companyId;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			switch (in.trim()) {
			case "computers": 
				System.out.println("Computers list :");
				System.out.println(webservice.getComputers());
				break;
			case "companies": 
				System.out.println("Companies list :");
				System.out.println(webservice.getCompanies());
				break;
			case "details":
				System.out.println("Enter a computer id :");
				in = scanIn.nextLine();
				if (Pattern.matches("^\\d+$", in.trim())) {
					id = Long.parseLong(in.trim());
					//Computer c = computerService.getById(id);
					System.out.println(webservice.getComputerById(id));
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
							introduced = in.trim();
							isOk = true;
						} else {
							System.err.println("Bad date format");
						}
					} else {
						isOk = true;
					}
				}
				isOk = false;
				discontinued = null;
				while (!isOk) {
					System.out.println("Enter a discontinued date :");
					in = scanIn.nextLine();
					if (!in.equals("")) {
						if (Pattern.matches("^\\d{4}-(0[1-9]|1[012])-((0[1-9]|[12][0-9]|3[01]) (20|21|22|23|[01]\\d|\\d)(([:][0-5]\\d){1,2})(([:][0-5]\\d){1,2}))$", in.trim())) {
							discontinued = in.trim();
							isOk = true;
						} else {
							System.err.println("Bad date format");
						}
					} else {
						isOk = true;
					}
				}
				webservice.createComputer(name, introduced, discontinued, 43);
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
							introduced = in.trim();
							isOk = true;
						} else {
							System.err.println("Bad date format");
						}
					} else {
						isOk = true;
					}
				}
				isOk = false;
				discontinued = null;
				while (!isOk) {
					System.out.println("Enter a discontinued date :");
					in = scanIn.nextLine();
					if (!in.equals("")) {
						if (Pattern.matches("^\\d{4}-(0[1-9]|1[012])-((0[1-9]|[12][0-9]|3[01]) (20|21|22|23|[01]\\d|\\d)(([:][0-5]\\d){1,2})(([:][0-5]\\d){1,2}))$", in.trim())) {
							discontinued = in.trim();
							isOk = true;
						} else {
							System.err.println("Bad date format");
						}
					} else {
						isOk = true;
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
				webservice.updateComputer(id, name, introduced, discontinued, companyId);
				break;
			case "delete":
				System.out.println("Enter a computer id :");
				in = scanIn.nextLine();
				if (Pattern.matches("^\\d+$", in.trim())) {
					id = Long.parseLong(in.trim());
					webservice.deleteComputer(id);
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
				Page<Computer> page = webservice.pageComputers(nbPage, nbResultByPage, "");
				System.out.println(webservice.getAllByPage(page, "compu.id", "asc", ""));
				int nbPageTotal = webservice.getLengthComputers() / nbResultByPage;
				System.out.println("page " + nbPage + "/" + nbPageTotal);
				while (!in.equals("stop")) {
					in = scanIn.nextLine();
					switch (in.trim()) {
					case "next" :
						if (nbPage < nbPageTotal) {
							nbPage++;
							page = webservice.pageComputers(nbPage, nbResultByPage, "");
							System.out.println(webservice.getAllByPage(page, "compu.id", "asc", ""));
							System.out.println("page " + nbPage + "/" + nbPageTotal);
						}
						break;
					case "previous" :
						if (nbPage > 1) {
							nbPage--;
							page = webservice.pageComputers(nbPage, nbResultByPage, "");
							System.out.println(webservice.getAllByPage(page, "compu.id", "asc", ""));
							System.out.println("page " + nbPage + "/" + nbPageTotal);
						}
						break;
					default :
						break;
					}
				}
				break;
			case "deleteCompany" :
				System.out.println("Enter a company id :");
				in = scanIn.nextLine();
				if (Pattern.matches("^\\d+$", in.trim())) {
					id = Long.parseLong(in.trim());
					webservice.deleteCompany(id);
				} else {
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
	}
}
