package com.excilys.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.model.ComputerModel;
import com.excilys.service.CompanyService;
import com.excilys.service.CompanyServiceImpl;
import com.excilys.service.ComputerService;
import com.excilys.service.ComputerServiceImpl;
import com.excilys.util.Regex;

@SuppressWarnings("serial")
@Controller
@WebServlet("/edit")
public class EditComputer extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(EditComputer.class);
	
	@Autowired
	ComputerService service;
	
	@Autowired
	CompanyService companyService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		long id = Long.parseLong(req.getParameter("id"));
		req.setAttribute("companies", companyService.getAll());
		ComputerModel computer = service.getById(id);
		req.setAttribute("computer", computer);
		getServletContext().getRequestDispatcher("/WEB-INF/views/editComputer.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		long computerId = Long.parseLong(req.getParameter("computerId"));

		String name = req.getParameter("name");
		if (name != null) {
			name = name.trim();
			if (name.isEmpty()) {
				req.setAttribute("errorName", "Name is required");
				req.setAttribute("companies", new CompanyServiceImpl().getAll());
				LOGGER.error("Failure : Name is required");
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
					req.setAttribute("companies", companyService.getAll());
					req.setAttribute("id", computerId);
					ComputerModel computer = service.getById(computerId);
					req.setAttribute("computer", computer);
					LOGGER.error("Failure : Bad format introduced date");
					getServletContext().getRequestDispatcher("/WEB-INF/views/editComputer.jsp?id="+computerId).forward(req, resp);
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
					req.setAttribute("companies", companyService.getAll());
					req.setAttribute("id", computerId);
					ComputerModel computer = service.getById(computerId);
					req.setAttribute("computer", computer);
					LOGGER.error("Failure : Bad format discontinued date");
					getServletContext().getRequestDispatcher("/WEB-INF/views/editComputer.jsp?id="+computerId).forward(req, resp);
					return;
				}
				discontinuedDate = LocalDateTime.parse(discontinued, formatter);
			}
		}
	
		long companyId = Long.parseLong(req.getParameter("companyId"));
		
		//ComputerService service = new ComputerServiceImpl();
		service.update(computerId, name, introducedDate, discontinuedDate, companyId);
		LOGGER.info("Successfully updated computer");
		resp.sendRedirect("dashboard");
	}

}
