package com.excilys.service;

import java.time.LocalDateTime;
import java.util.List;

import com.excilys.model.CompanyModel;
import com.excilys.model.CompanyModelImpl;
import com.excilys.model.ComputerModel;
import com.excilys.model.ComputerModelImpl;
import com.excilys.model.Page;
import com.excilys.persistence.ComputerDao;
import com.excilys.persistence.ComputerDaoImpl;

public class ComputerServiceImpl implements ComputerService {

	public int getLength() {
		ComputerDao computerDao = new ComputerDaoImpl();
		return computerDao.getLength();
	}
	
	public List<ComputerModel> getAll() {
		ComputerDao computerDao = new ComputerDaoImpl();
		return computerDao.getAllComputers();
	}
	
	public List<ComputerModel> getAllByPage(Page<ComputerModel> page) {
		ComputerDao computerDao = new ComputerDaoImpl();
		return computerDao.getAllComputersByPage(page);
	}
	
	public ComputerModel getById(long id) {
		ComputerDao computerDao = new ComputerDaoImpl();
		return computerDao.getComputerDetails(id);
	}
	
	public void create(String name, LocalDateTime introduced, LocalDateTime discontinued) {
		ComputerModel computer = new ComputerModelImpl();
		computer.setName(name);
		computer.setDiscontinuedDate(discontinued);
		computer.setIntroducedDate(introduced);
		ComputerDao computerDao = new ComputerDaoImpl();
		computerDao.createComputer(computer);
	}
	
	public void update(long computerId, String name, LocalDateTime introduced, LocalDateTime discontinued, long companyId) {
		CompanyModel company = new CompanyModelImpl();
		company.setId(companyId);
		ComputerModel computer = new ComputerModelImpl(computerId, name, introduced, discontinued, (CompanyModelImpl) company);
		ComputerDao computerDao = new ComputerDaoImpl();
		computerDao.updateComputer(computer);
	}
	
	public void delete(long computerId) {
		ComputerDao computerDao = new ComputerDaoImpl();
		computerDao.deleteComputer(computerId);
	}
	
	public Page<ComputerModel> page(int nbPage, int nbResultByPage) {
		if (getLength() % nbResultByPage != 0) {
			nbPage++;
		}
		int offset = nbPage * nbResultByPage - nbResultByPage;
		Page<ComputerModel> page = new Page<ComputerModel>(nbResultByPage, offset, getLength());
		return page;
	}
}
