package com.excilys.service;

import java.time.LocalDateTime;
import java.util.List;

import com.excilys.model.CompanyModel;
import com.excilys.model.ComputerModel;
import com.excilys.model.Page;
import com.excilys.persistence.ComputerDao;
import com.excilys.persistence.ComputerDaoImpl;

public class ComputerService {

	public static List<ComputerModel> getAll() {
		ComputerDao computerDao = new ComputerDaoImpl();
		return computerDao.getAllComputers();
	}
	
	public static List<ComputerModel> getAllByPage(Page<ComputerModel> page) {
		ComputerDao computerDao = new ComputerDaoImpl();
		return computerDao.getAllComputersByPage(page);
	}
	
	public static ComputerModel getById(long id) {
		ComputerDao computerDao = new ComputerDaoImpl();
		return computerDao.getComputerDetails(id);
	}
	
	public static void create(String name, LocalDateTime introduced, LocalDateTime discontinued) {
		ComputerModel computer = new ComputerModel();
		computer.setName(name);
		computer.setDiscontinuedDate(discontinued);
		computer.setIntroducedDate(introduced);
		ComputerDao computerDao = new ComputerDaoImpl();
		computerDao.createComputer(computer);
	}
	
	public static void update(long computerId, String name, LocalDateTime introduced, LocalDateTime discontinued, long companyId) {
		CompanyModel company = new CompanyModel();
		company.setId(companyId);
		ComputerModel computer = new ComputerModel(computerId, name, introduced, discontinued, company);
		ComputerDao computerDao = new ComputerDaoImpl();
		computerDao.updateComputer(computer);
	}
	
	public static void delete(long computerId) {
		ComputerDao computerDao = new ComputerDaoImpl();
		computerDao.deleteComputer(computerId);
	}
}
