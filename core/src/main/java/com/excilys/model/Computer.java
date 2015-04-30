package com.excilys.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

// TODO: Auto-generated Javadoc
/**
 * The Class Computer.
 */
@Entity
@Table(name="computer")
public class Computer implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Column(name = "id") 
	private long id;
	
	/** The name. */
	@Column(name = "name") 
	private String name;
	
	/** The introduced date. */
	@Column(name = "introduced") 
	@Type(type="com.excilys.mapper.DateTimeMapper")
	private LocalDateTime introducedDate;
	
	/** The discontinued date. */
	@Column(name = "discontinued") 
	@Type(type="com.excilys.mapper.DateTimeMapper")
	private LocalDateTime discontinuedDate;
	
	/** The company. */
	@OneToOne
	private Company company;
	
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
	public LocalDateTime getIntroducedDate() {
		return introducedDate;
	}

	/**
	 * Sets the introduced date.
	 *
	 * @param introducedDate the new introduced date
	 */
	public void setIntroducedDate(LocalDateTime introducedDate) {
		this.introducedDate = introducedDate;
	}

	/**
	 * Gets the discontinued date.
	 *
	 * @return the discontinued date
	 */
	public LocalDateTime getDiscontinuedDate() {
		return discontinuedDate;
	}

	/**
	 * Sets the discontinued date.
	 *
	 * @param discontinuedDate the new discontinued date
	 */
	public void setDiscontinuedDate(LocalDateTime discontinuedDate) {
		this.discontinuedDate = discontinuedDate;
	}

	/**
	 * Gets the company.
	 *
	 * @return the company
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * Sets the company.
	 *
	 * @param company the new company
	 */
	public void setCompany(Company company) {
		this.company = company;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
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
	
	/**
	 * The Class Builder.
	 */
	public static class Builder {
		
		/** The c. */
		Computer c;
		
		/**
		 * Instantiates a new builder.
		 */
		private Builder() {
			c = new Computer();
		}
		
		/**
		 * Id.
		 *
		 * @param id the id
		 * @return the builder
		 */
		public Builder id(long id) {
			c.id = id;
			return this;
		}
		
		
		/**
		 * Name.
		 *
		 * @param name the name
		 * @return the builder
		 */
		public Builder name(String name) {
			c.name = name;
			return this;
		}
		
		/**
		 * Introduced.
		 *
		 * @param introduced the introduced
		 * @return the builder
		 */
		public Builder introduced(LocalDateTime introduced) {
			c.introducedDate = introduced;
			return this;
		}
		
		/**
		 * Discontinued.
		 *
		 * @param discontinued the discontinued
		 * @return the builder
		 */
		public Builder discontinued(LocalDateTime discontinued) {
			c.discontinuedDate = discontinued;
			return this;
		}
		
		/**
		 * Company.
		 *
		 * @param company the company
		 * @return the builder
		 */
		public Builder company(Company company) {
			c.company = company;
			return this;
		}
		
		/**
		 * Builds the computer.
		 *
		 * @return the computer
		 */
		public Computer build() {
			return c;
		}
	}

	/**
	 * Builder.
	 *
	 * @return the builder
	 */
	public static Builder builder() {
		return new Builder();
	}
}
