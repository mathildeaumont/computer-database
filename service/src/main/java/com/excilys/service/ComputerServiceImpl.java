package com.excilys.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.Page;
import com.excilys.persistence.ComputerDao;

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerServiceImpl.
 */
@Service
public class ComputerServiceImpl implements ComputerService {
	
	/** The computer dao. */
	@Autowired
	ComputerDao computerDao;
	
	/**
	 * Instantiates a new computer service impl.
	 */
	public ComputerServiceImpl() {

	}

	/* (non-Javadoc)
	 * @see com.excilys.service.ComputerService#getLength()
	 */
	public int getLength() {
		return computerDao.getLength();
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.service.ComputerService#getLength(java.lang.String)
	 */
	public int getLength(String search) {
		return computerDao.getLength(search);
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.service.ComputerService#getAll()
	 */
	public List<Computer> getAll() {
		return computerDao.getAll();
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.service.ComputerService#getAllByPage(com.excilys.model.Page, java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<Computer> getAllByPage(Page<Computer> page, String order, String direction, String search) {
		return computerDao.getAllByPage(page, order, direction, search);
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.service.ComputerService#getById(long)
	 */
	public Computer getById(long id) {
		return computerDao.getDetails(id);
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.service.ComputerService#create(java.lang.String, java.time.LocalDateTime, java.time.LocalDateTime, long)
	 */
	@Transactional
	public void create(String name, LocalDateTime introduced, LocalDateTime discontinued, long companyId) {
		Company company = new Company();
		if (companyId == 0) {
			company = null;
		} else {
			company.setId(companyId);
		}
		Computer computer = Computer.builder().name(name)
											  .introduced(introduced)
											  .discontinued(discontinued)
											  .company(company)
											  .build();
		computerDao.create(computer);
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.service.ComputerService#update(long, java.lang.String, java.time.LocalDateTime, java.time.LocalDateTime, long)
	 */
	@Transactional
	public void update(long computerId, String name, LocalDateTime introduced, LocalDateTime discontinued, long companyId) {
		Company company = new Company();
		if (companyId == 0) {
			company = null;
		} else {
			company.setId(companyId);
		}
		Computer computer = Computer.builder().id(computerId)
											  .name(name)
											  .introduced(introduced)
											  .discontinued(discontinued)
											  .company(company)
											  .build();
		computerDao.update(computer);
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.service.ComputerService#delete(long)
	 */
	public void delete(long computerId) {
		computerDao.delete(computerId);
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.service.ComputerService#page(int, int, java.lang.String)
	 */
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
