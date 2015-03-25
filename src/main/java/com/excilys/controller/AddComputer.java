package com.excilys.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.service.CompanyServiceImpl;
import com.excilys.service.ComputerService;
import com.excilys.service.ComputerServiceImpl;
import com.excilys.util.Regex;

@SuppressWarnings("serial")
public class AddComputer extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("companies", new CompanyServiceImpl().getAll());
		getServletContext().getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String name = req.getParameter("name");
		if (name != null) {
			name = name.trim();
			if (name.isEmpty()) {
				req.setAttribute("errorName", "Name is required");
				req.setAttribute("companies", new CompanyServiceImpl().getAll());
				getServletContext().getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(req, resp);
				return;
			}
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime introducedDate = null;
		LocalDateTime discontinuedDate = null;
		
		String introduced = req.getParameter("introduced");
		if (introduced != null) {
			if (!introduced.isEmpty()) {
				if (!Pattern.matches(Regex.DATE_FORMAT.getRegex(), introduced.trim())) {
					req.setAttribute("errorIntroduced", "Invalid format (yyyy-mm-dd hh:mm:ss)");
					req.setAttribute("companies", new CompanyServiceImpl().getAll());
					getServletContext().getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(req, resp);
					return;
				}
				introducedDate = LocalDateTime.parse(introduced, formatter);
			}
		}
		
		String discontinued = req.getParameter("discontinued");
		if (discontinued != null) {
			if (!discontinued.isEmpty()) {
				if (!Pattern.matches(Regex.DATE_FORMAT.getRegex(), discontinued.trim())) {
					req.setAttribute("errorDiscontinued", "Invalid format (yyyy-mm-dd hh:mm:ss)");
					req.setAttribute("companies", new CompanyServiceImpl().getAll());
					getServletContext().getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(req, resp);
					return;
				}
				discontinuedDate = LocalDateTime.parse(discontinued, formatter);
			}
		}
		
		
	
		long companyId = Long.parseLong(req.getParameter("companyId"));
		
		ComputerService service = new ComputerServiceImpl();
		service.create(name, introducedDate, discontinuedDate, companyId);
		resp.sendRedirect("dashboard");
	}
}
