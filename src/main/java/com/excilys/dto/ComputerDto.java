package com.excilys.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class ComputerDto {

	private long id;
	
	@NotEmpty
	@Size(min = 2, max = 50)
	private String name;
	private String introducedDate;
	private String discontinuedDate;
	private CompanyDto company;
	
	public ComputerDto() {
		company = new CompanyDto();
	}
	
	public ComputerDto(long id, String name, String introduced, String discontinued, CompanyDto company) {
		this.id = id;
		this.name = name;
		this.introducedDate = introduced;
		this.discontinuedDate = discontinued;
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroducedDate() {
		return introducedDate;
	}

	public void setIntroducedDate(String introduced) {
		this.introducedDate = introduced;
	}

	public String getDiscontinuedDate() {
		return discontinuedDate;
	}

	public void setDiscontinuedDate(String discontinued) {
		this.discontinuedDate = discontinued;
	}	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public CompanyDto getCompany() {
		return company;
	}

	public void setCompany(CompanyDto company) {
		this.company = company;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((company == null) ? 0 : company.hashCode());
		result = prime * result
				+ ((discontinuedDate == null) ? 0 : discontinuedDate.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((introducedDate == null) ? 0 : introducedDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComputerDto other = (ComputerDto) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (discontinuedDate == null) {
			if (other.discontinuedDate != null)
				return false;
		} else if (!discontinuedDate.equals(other.discontinuedDate))
			return false;
		if (id != other.id)
			return false;
		if (introducedDate == null) {
			if (other.introducedDate != null)
				return false;
		} else if (!introducedDate.equals(other.introducedDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
