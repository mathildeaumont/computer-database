package com.excilys.service;

import java.time.LocalDateTime;
import java.util.List;

import com.excilys.model.CompanyModel;
import com.excilys.model.CompanyModelImpl;
import com.excilys.model.ComputerModel;
import com.excilys.model.ComputerModelImpl;
import com.excilys.model.Page;
import com.excilys.persistence.ComputerDao;
import com.excilys.persistence.DaoFactory;

public class ComputerServiceImpl implements ComputerService {
	
	private ComputerDao computerDao;
	
	public ComputerServiceImpl() {
		computerDao = DaoFactory.INSTANCE.getComputerDAO();
	}

	public int getLength() {
		return computerDao.getLength();
	}
	
	public List<ComputerModel> getAll() {
		return computerDao.getAllComputers();
	}
	
	public List<ComputerModel> getAllByPage(Page<ComputerModel> page) {
		return computerDao.getAllComputersByPage(page);
	}
	
	public ComputerModel getById(long id) {
		return computerDao.getComputerDetails(id);
	}
	
	public void create(String name, LocalDateTime introduced, LocalDateTime discontinued) {
		ComputerModel computer = new ComputerModelImpl();
		computer.setName(name);
		computer.setDiscontinuedDate(discontinued);
		computer.setIntroducedDate(introduced);
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
	
	public Page<ComputerModel> page(int nbPage, int nbResultByPage) {
		if (getLength() % nbResultByPage != 0) {
			nbPage++;
		}
		int offset = nbPage * nbResultByPage - nbResultByPage;
		Page<ComputerModel> page = new Page<ComputerModel>(nbResultByPage, offset, getLength());
		return page;
	}
}
