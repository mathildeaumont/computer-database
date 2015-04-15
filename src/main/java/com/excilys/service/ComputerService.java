package com.excilys.service;

import java.time.LocalDateTime;
import java.util.List;

import com.excilys.model.Computer;
import com.excilys.model.Page;

public interface ComputerService {
	
	public int getLength();
	
	public int getLength(String search);
	
	public List<Computer> getAll();
	
	public List<Computer> getAllByPage(Page<Computer> page, String order, String direction, String search);
	
	public Computer getById(long id);
	
	public void create(String name, LocalDateTime introduced, LocalDateTime discontinued, long companyId);
	
	public void update(long computerId, String name, LocalDateTime introduced, LocalDateTime discontinued, long companyId);
	
	public void delete(long computerId);
	
	public Page<Computer> page(int nbPage, int nbResultByPage, String search);
}
