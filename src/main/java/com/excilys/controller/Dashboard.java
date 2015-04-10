package com.excilys.controller;
import com.excilys.mapper.ComputerMapperDto;
import com.excilys.model.ComputerModel;
import com.excilys.model.Page;
import com.excilys.service.ComputerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.util.List;

@SuppressWarnings("serial")
@Controller
@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {

	@Autowired 
	ComputerService service;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

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

		String order = request.getParameter("order");
		if (order == null || order.isEmpty()) {
			order = "compu.id";
		}

		String direction = request.getParameter("direction");
		if (direction == null || direction.isEmpty()) {
			direction = "asc";
		}

		String search = request.getParameter("search");
		if (search == null || search.isEmpty()) {
			search = "";
		}

		Page<ComputerModel> currentPage = service.page(page, nbResults, search);
		List<ComputerModel> computers = service.getAllByPage(currentPage, order, direction, search);

		request.setAttribute("computers", ComputerMapperDto.modelsToDtos(computers));
		request.setAttribute("computersNb", currentPage.getNbResultTotal());
		request.setAttribute("page", currentPage);
		request.setAttribute("order", order);
		request.setAttribute("direction", direction);
		request.setAttribute("search", search);

		getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}
}