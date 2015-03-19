package com.excilys.model;

import java.time.LocalDateTime;

public interface ComputerModel {
	
	public long getId();

	public void setId(long id);
	
	public String getName();

	public void setName(String name);

	public LocalDateTime getIntroducedDate();

	public void setIntroducedDate(LocalDateTime introducedDate);

	public LocalDateTime getDiscontinuedDate();

	public void setDiscontinuedDate(LocalDateTime discontinuedDate);

	public CompanyModelImpl getCompany();

	public void setCompany(CompanyModelImpl company);
	
	@Override
	public String toString();

	@Override
	public int hashCode();
}
