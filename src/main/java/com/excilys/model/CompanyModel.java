package com.excilys.model;

public interface CompanyModel {
	
	public long getId();
	
	public void setId(long id);
	
	public String getName();
	
	public void setName(String name);
	
	@Override
	public String toString();

	@Override
	public int hashCode();

	@Override
	public boolean equals(Object obj);
}
