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

import com.excilys.mapper.CompanyMapperDto;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.util.Regex;

@SuppressWarnings("serial")
@Controller
@WebServlet("/add")
public class AddComputer extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AddComputer.class);

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
		req.setAttribute("companies", companyService.getAll());
		getServletContext().getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		int nbErrors = 0;
		CompanyMapperDto companyMapperDto = new CompanyMapperDto();

		String name = req.getParameter("name");
		if (name != null) {
			name = name.trim();
			if (name.isEmpty()) {
				req.setAttribute("errorName", "Name is required");
				req.setAttribute("companies", companyMapperDto.modelsToDtos(companyService.getAll()));
				nbErrors++;
			} else {
				req.setAttribute("name", name);
			}
		} else {
			LOGGER.error("Error because of name is null");
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime introducedDate = null;
		LocalDateTime discontinuedDate = null;

		String introduced = req.getParameter("introduced");
		if (introduced != null) {
			if (!introduced.isEmpty()) {
				if (!Pattern.matches(Regex.DATE_FORMAT.getRegex(), introduced.trim())) {
					req.setAttribute("errorIntroduced", "Invalid format (yyyy-mm-dd hh:mm:ss)");
					req.setAttribute("companies", companyMapperDto.modelsToDtos(companyService.getAll()));
					nbErrors++;
				} else {
					introducedDate = LocalDateTime.parse(introduced, formatter);
					req.setAttribute("introduced", introduced);
				}
			}
		} else {
			LOGGER.error("Error because of introduced date is null");
		}

		String discontinued = req.getParameter("discontinued");
		if (discontinued != null) {
			if (!discontinued.isEmpty()) {
				if (!Pattern.matches(Regex.DATE_FORMAT.getRegex(), discontinued.trim())) {
					req.setAttribute("errorDiscontinued", "Invalid format (yyyy-mm-dd hh:mm:ss)");
					req.setAttribute("companies", companyMapperDto.modelsToDtos(companyService.getAll()));
					nbErrors++;
				} else {
					discontinuedDate = LocalDateTime.parse(discontinued, formatter);
					req.setAttribute("discontinued", discontinued);

				}
			}
		} else {
			LOGGER.error("Error because of discontinued date is null");
		}

		long companyId = Long.parseLong(req.getParameter("companyId"));
		req.setAttribute("companyId", companyId);

		if (nbErrors != 0) {
			getServletContext().getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(req, resp);
			return;
		}

		service.create(name, introducedDate, discontinuedDate, companyId);

		LOGGER.info("Successfully created computer");
		resp.sendRedirect("dashboard");
	}
}
