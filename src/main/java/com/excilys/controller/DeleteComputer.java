package com.excilys.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.service.ComputerService;
import com.excilys.service.ComputerServiceImpl;

@SuppressWarnings("serial")
public class DeleteComputer extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String selected = req.getParameter("selection");
		System.out.println(selected);
		String[] computerIds = selected.split(",");
		
		if (computerIds != null && computerIds.length > 0) {
			ComputerService service = new ComputerServiceImpl();
			for (String id : computerIds) {
				Long computerId = Long.parseLong(id);
				service.delete(computerId);
			}
		}
		resp.sendRedirect("dashboard");
	}

}
