package com.excilys.controller;
import com.excilys.model.ComputerModel;
import com.excilys.model.Page;
import com.excilys.service.ComputerService;
import com.excilys.service.ComputerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@SuppressWarnings("serial")
public class Dashboard extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		int page = 1;
		if (request.getParameter("offset") != null) {
			page = Integer.valueOf(request.getParameter("offset"));
		}
		int nbResults = 10;
		if (request.getParameter("nbResults") != null) {
			nbResults = Integer.valueOf(request.getParameter("nbResults"));
		}
		
		ComputerService service = new ComputerServiceImpl();
		int computersNb = service.getLength();
		request.setAttribute("computersNb", computersNb);
		
		Page<ComputerModel> currentPage = service.page(page, nbResults);
		request.setAttribute("page", currentPage);
		
		List<ComputerModel> computers = service.getAllByPage(currentPage);
		request.setAttribute("computers", computers);
		
		getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}
}