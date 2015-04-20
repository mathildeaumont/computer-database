package com.excilys.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="computer")
public class Computer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id") 
	private long id;
	
	@Column(name = "name") 
	private String name;
	
	@Column(name = "introduced") 
	@Type(type="com.excilys.mapper.DateTimeMapper")
	private LocalDateTime introducedDate;
	
	@Column(name = "discontinued") 
	@Type(type="com.excilys.mapper.DateTimeMapper")
	private LocalDateTime discontinuedDate;
	
	@OneToOne
	private Company company;
	
	public Computer() {
		
	}
	
	public Computer(long id, String name, LocalDateTime introducedDate, LocalDateTime discontinuedDate, Company company) {
		this.id = id;
		this.setName(name);
		this.setIntroducedDate(introducedDate);
		this.setDiscontinuedDate(discontinuedDate);
		this.setCompany(company);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getIntroducedDate() {
		return introducedDate;
	}

	public void setIntroducedDate(LocalDateTime introducedDate) {
		this.introducedDate = introducedDate;
	}

	public LocalDateTime getDiscontinuedDate() {
		return discontinuedDate;
	}

	public void setDiscontinuedDate(LocalDateTime discontinuedDate) {
		this.discontinuedDate = discontinuedDate;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
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
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime
				* result
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
		Computer other = (Computer) obj;
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
