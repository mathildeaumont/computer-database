package com.excilys.service;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.Page;
import com.excilys.persistence.ComputerDao;
import com.excilys.persistence.DaoFactory;

@Service
public class ComputerServiceImpl implements ComputerService {
	
	@Autowired
	DaoFactory daoFactory;
	
	@Autowired
	ComputerDao computerDao;
	
	public ComputerServiceImpl() {

	}

	public int getLength() {
		return computerDao.getLength();
	}
	
	public int getLength(String search) {
		return computerDao.getLength(search);
	}
	
	public List<Computer> getAll() {
		return computerDao.getAllComputers();
	}
	
	public List<Computer> getAllByPage(Page<Computer> page, String order, String direction, String search) {
		return computerDao.getAllComputersByPage(page, order, direction, search);
	}
	
	public Computer getById(long id) {
		return computerDao.getComputerDetails(id);
	}
	
	public void create(String name, LocalDateTime introduced, LocalDateTime discontinued, long companyId) {
		Company company = new Company();
		company.setId(companyId);
		Computer computer = new Computer();
		computer.setName(name);
		computer.setDiscontinuedDate(discontinued);
		computer.setIntroducedDate(introduced);
		computer.setCompany((Company) company);
		computerDao.createComputer(computer);
	}
	
	public void update(long computerId, String name, LocalDateTime introduced, LocalDateTime discontinued, long companyId) {
		Company company = new Company();
		company.setId(companyId);
		Computer computer = new Computer(computerId, name, introduced, discontinued, (Company) company);
		computerDao.updateComputer(computer);
	}
	
	public void delete(long computerId) {
		computerDao.deleteComputer(computerId);
	}
	
	public Page<Computer> page(int nbPage, int nbResultByPage, String search) {
		
		int size = getLength();
		if (!search.isEmpty()) {
			size = computerDao.getLength(search);
		}
		
		int totalPages = size / nbResultByPage;
		if (size % nbResultByPage != 0) {
			totalPages++;
		}
		if (size == 0) {
			totalPages = 1;
		}
		int offset = nbPage * nbResultByPage - nbResultByPage;
		Page<Computer> page = new Page<Computer>(nbPage, nbResultByPage, offset, totalPages, size);
		return page;
	}
}
