package com.excilys.persistence;

import java.util.List;

import com.excilys.model.Computer;
import com.excilys.model.Page;

public interface ComputerDao {
	
	public int getLength();

	public int getLength(String search);
	
	public List<Computer> getAllComputers();
	
	public List<Computer> getAllComputersByPage(Page<Computer> page, String order, String direction, String search);
	
	public Computer getComputerDetails(long id);
	
	public void createComputer(Computer computer);
	
	public void updateComputer(Computer computer);
	
	public void deleteComputer(long id);
	
	public void deleteComputerByCompanyId(long companyId);
}
