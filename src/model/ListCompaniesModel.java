package model;

import java.util.ArrayList;
import java.util.List;

public class ListCompaniesModel {
	private List<CompanyModel> listCompanies;
	
	public ListCompaniesModel() {
		listCompanies = new ArrayList<CompanyModel>();
	}
	
	public void addCompany(CompanyModel company) {
		listCompanies.add(company);
	}
	
	public void removeCompany(CompanyModel company) {
		listCompanies.remove(company);
	}
}
