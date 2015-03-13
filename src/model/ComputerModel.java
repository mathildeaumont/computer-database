package model;

import java.util.Date;

public class ComputerModel {

	private long id;
	private String name;
	private Date introducedDate;
	private Date discontinuedDate;
	private long manufacturer;
	
	public ComputerModel(long id, String name, Date introducedDate, Date discontinuedDate, long manufacturer) {
		this.id = id;
		this.setName(name);
		this.setIntroducedDate(introducedDate);
		this.setDiscontinuedDate(discontinuedDate);
		this.setManufacturer(manufacturer);
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

	public Date getIntroducedDate() {
		return introducedDate;
	}

	public void setIntroducedDate(Date introducedDate) {
		this.introducedDate = introducedDate;
	}

	public Date getDiscontinuedDate() {
		return discontinuedDate;
	}

	public void setDiscontinuedDate(Date discontinuedDate) {
		this.discontinuedDate = discontinuedDate;
	}

	public long getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(long manufacturer) {
		this.manufacturer = manufacturer;
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
		sb.append(manufacturer);
		return sb.toString();
	}

}
