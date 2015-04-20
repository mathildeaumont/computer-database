package com.excilys.persistence;

import java.util.List;

import com.excilys.model.Computer;
import com.excilys.model.Page;

public interface ComputerDao {
	
	public int getLength();

	public int getLength(String search);
	
	public List<Computer> getAll();
	
	public List<Computer> getAllByPage(Page<Computer> page, String order, String direction, String search);
	
	public Computer getDetails(long id);
	
	public void create(Computer computer);
	
	public void update(Computer computer);
	
	public void delete(long id);
	
	public void deleteByCompanyId(long companyId);
}
