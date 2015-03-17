package com.excilys.service;

import java.time.LocalDateTime;
import java.util.List;

import com.excilys.model.ComputerModel;
import com.excilys.persistence.ComputerDao;
import com.excilys.persistence.ComputerDaoImpl;

public class ComputerServices {

	public static List<ComputerModel> getAll() {
		ComputerDao computerDao = new ComputerDaoImpl();
		return computerDao.getAllComputers();
	}
	
	public static ComputerModel getById(long id) {
		ComputerDao computerDao = new ComputerDaoImpl();
		return computerDao.getComputerDetails(id);
	}
	
	public static void create(String name) {
		ComputerDao computerDao = new ComputerDaoImpl();
		computerDao.createComputer(name);
	}
	
	public static void update(long computerId, String name, LocalDateTime introduced, LocalDateTime discontinued, long companyId) {
		ComputerDao computerDao = new ComputerDaoImpl();
		computerDao.updateComputer(computerId, name, introduced, discontinued, companyId);
	}
	
	public static void delete(long computerId) {
		ComputerDao computerDao = new ComputerDaoImpl();
		computerDao.deleteComputer(computerId);
	}
}
