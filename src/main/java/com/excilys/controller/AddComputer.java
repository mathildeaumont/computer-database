package com.excilys.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.service.CompanyServiceImpl;
import com.excilys.service.ComputerService;
import com.excilys.service.ComputerServiceImpl;

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
		String introduced = req.getParameter("introduced");
		String discontinued = req.getParameter("discontinued");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime introducedDate = LocalDateTime.parse(introduced, formatter);
		
		LocalDateTime discontinuedDate = LocalDateTime.parse(discontinued, formatter);
		long companyId = Long.parseLong(req.getParameter("companyId"));
		
		ComputerService service = new ComputerServiceImpl();
		service.create(name, introducedDate, discontinuedDate, companyId);
		resp.sendRedirect("dashboard");
	}
}
