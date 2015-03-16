package com.excilys.model;

import java.time.LocalDateTime;

public class ComputerModel {

	private long id;
	private String name;
	private LocalDateTime introducedDate;
	private LocalDateTime discontinuedDate;
	private long manufacturer;
	
	public ComputerModel(long id, String name, LocalDateTime introducedDate, LocalDateTime discontinuedDate, long manufacturer) {
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
