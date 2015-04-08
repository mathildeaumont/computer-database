package com.excilys.service;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.model.CompanyModel;
import com.excilys.model.CompanyModelImpl;
import com.excilys.model.ComputerModel;
import com.excilys.model.ComputerModelImpl;
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
		//computerDao = daoFactory.getComputerDAO();
		System.out.println("computerserviceimpl");
	}

	public int getLength() {
		return computerDao.getLength();
	}
	
	public int getLength(String search) {
		return computerDao.getLength(search);
	}
	
	public List<ComputerModel> getAll() {
		return computerDao.getAllComputers();
	}
	
	public List<ComputerModel> getAllByPage(Page<ComputerModel> page, String order, String direction, String search) {
		return computerDao.getAllComputersByPage(page, order, direction, search);
	}
	
	public ComputerModel getById(long id) {
		return computerDao.getComputerDetails(id);
	}
	
	public void create(String name, LocalDateTime introduced, LocalDateTime discontinued, long companyId) {
		CompanyModel company = new CompanyModelImpl();
		company.setId(companyId);
		ComputerModel computer = new ComputerModelImpl();
		computer.setName(name);
		computer.setDiscontinuedDate(discontinued);
		computer.setIntroducedDate(introduced);
		computer.setCompany((CompanyModelImpl) company);
		computerDao.createComputer(computer);
	}
	
	public void update(long computerId, String name, LocalDateTime introduced, LocalDateTime discontinued, long companyId) {
		CompanyModel company = new CompanyModelImpl();
		company.setId(companyId);
		ComputerModel computer = new ComputerModelImpl(computerId, name, introduced, discontinued, (CompanyModelImpl) company);
		computerDao.updateComputer(computer);
	}
	
	public void delete(long computerId) {
		computerDao.deleteComputer(computerId);
	}
	
	public Page<ComputerModel> page(int nbPage, int nbResultByPage, String search) {
		
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
		Page<ComputerModel> page = new Page<ComputerModel>(nbPage, nbResultByPage, offset, totalPages, size);
		return page;
	}
}
