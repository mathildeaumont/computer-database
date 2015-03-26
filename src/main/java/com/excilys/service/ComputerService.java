package com.excilys.service;

import java.time.LocalDateTime;
import java.util.List;

import com.excilys.model.ComputerModel;
import com.excilys.model.Page;

public interface ComputerService {
	
	public int getLength();
	
	public List<ComputerModel> getAll();
	
	public List<ComputerModel> getAllByPage(Page<ComputerModel> page, String order, String direction, String search);
	
	public ComputerModel getById(long id);
	
	public void create(String name, LocalDateTime introduced, LocalDateTime discontinued, long companyId);
	
	public void update(long computerId, String name, LocalDateTime introduced, LocalDateTime discontinued, long companyId);
	
	public void delete(long computerId);
	
	public Page<ComputerModel> page(int nbPage, int nbResultByPage);
}
