package model;

public class CompanyModel {

	private long id;
	private String name;
	
	public CompanyModel(long id, String name) {
		this.setId(id);
		this.setName(name);
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(id);
		sb.append(" \t");
		sb.append(name);
		return sb.toString();
	}
}
