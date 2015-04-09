package com.excilys.persistence;

import java.util.List;

import com.excilys.model.ComputerModel;
import com.excilys.model.Page;

public interface ComputerDao {
	
	public int getLength();

	public int getLength(String search);
	
	public List<ComputerModel> getAllComputers();
	
	public List<ComputerModel> getAllComputersByPage(Page<ComputerModel> page, String order, String direction, String search);
	
	public ComputerModel getComputerDetails(long id);
	
	public void createComputer(ComputerModel computer);
	
	public void updateComputer(ComputerModel computer);
	
	public void deleteComputer(long id);
	
	public void deleteComputerByCompanyId(long companyId);
}
