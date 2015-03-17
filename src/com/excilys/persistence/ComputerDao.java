package com.excilys.persistence;

import java.time.LocalDateTime;
import java.util.List;

import com.excilys.model.ComputerModel;

public interface ComputerDao {

	public List<ComputerModel> getAllComputers();
	
	public ComputerModel getComputerDetails(long idComputer);
	
	public void createComputer(String name);
	
	public void updateComputer(long computerId, String name, LocalDateTime introduced, LocalDateTime discontinued, long companyId);
	
	public void deleteComputer(long computerId);
}
