package com.excilys.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.service.ComputerService;
import com.excilys.service.ComputerServiceImpl;

@SuppressWarnings("serial")
public class DeleteComputer extends HttpServlet {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DeleteComputer.class);
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String selected = req.getParameter("selection");
		if (selected != null&& !selected.isEmpty()) { 
			String[] computerIds = selected.split(",");
			if (computerIds != null && computerIds.length > 0) {
				ComputerService service = new ComputerServiceImpl();
				for (String id : computerIds) {
					Long computerId = Long.parseLong(id);
					service.delete(computerId);
					LOGGER.info("Successfully deleted computer");
				}
			} else {
				LOGGER.error("Failure : computer ids null");
			}
		} else {
			LOGGER.error("Failure : Selection is empty");
		}
		resp.sendRedirect("dashboard");
	}

}
