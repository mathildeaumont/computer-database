package com.excilys.persistence;

import java.util.List;

import com.excilys.model.ComputerModel;
import com.excilys.model.Page;

public interface ComputerDao {

	public List<ComputerModel> getAllComputers();
	
	public List<ComputerModel> getAllComputersByPage(Page<ComputerModel> page);
	
	public ComputerModel getComputerDetails(long id);
	
	public void createComputer(ComputerModel computer);
	
	public void updateComputer(ComputerModel computer);
	
	public void deleteComputer(long id);
}
