package com.excilys.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerDto.
 */
public class ComputerDto {

	/** The id. */
	private long id;
	
	/** The name. */
	@NotEmpty
	@Size(min = 2, max = 50)
	private String name;
	
	/** The introduced date. */
	private String introducedDate;
	
	/** The discontinued date. */
	private String discontinuedDate;
	
	/** The company. */
	private CompanyDto company;
	
	/**
	 * Instantiates a new computer dto.
	 */
	public ComputerDto() {
		company = new CompanyDto();
	}
	
	/**
	 * Instantiates a new computer dto.
	 *
	 * @param id the id
	 * @param name the name
	 * @param introduced the introduced
	 * @param discontinued the discontinued
	 * @param company the company
	 */
	public ComputerDto(long id, String name, String introduced, String discontinued, CompanyDto company) {
		this.id = id;
		this.name = name;
		this.introducedDate = introduced;
		this.discontinuedDate = discontinued;
		this.company = company;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the introduced date.
	 *
	 * @return the introduced date
	 */
	public String getIntroducedDate() {
		if (introducedDate == null) {
			return "";
		}
		return introducedDate;
	}

	/**
	 * Sets the introduced date.
	 *
	 * @param introduced the new introduced date
	 */
	public void setIntroducedDate(String introduced) {
		this.introducedDate = introduced;
	}

	/**
	 * Gets the discontinued date.
	 *
	 * @return the discontinued date
	 */
	public String getDiscontinuedDate() {
		if (discontinuedDate == null) {
			return "";
		}
		return discontinuedDate;
	}

	/**
	 * Sets the discontinued date.
	 *
	 * @param discontinued the new discontinued date
	 */
	public void setDiscontinuedDate(String discontinued) {
		this.discontinuedDate = discontinued;
	}	

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}


	/**
	 * Gets the company.
	 *
	 * @return the company
	 */
	public CompanyDto getCompany() {
		return company;
	}

	/**
	 * Sets the company.
	 *
	 * @param company the new company
	 */
	public void setCompany(CompanyDto company) {
		this.company = company;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
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

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(id);
		sb.append(" \t");
		sb.append(name);
		sb.append(" \t\t");
		sb.append(introducedDate);
		sb.append(" \t");
		sb.append(discontinuedDate);
		sb.append(" \t");
 		if (company != null) {
 			sb.append(company.getName());
 		}
 		sb.append("\n");
		return sb.toString();
	}
}
