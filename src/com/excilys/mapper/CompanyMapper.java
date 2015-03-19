package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.model.CompanyModel;
import com.excilys.model.CompanyModelImpl;

public class CompanyMapper implements Mapper<CompanyModel> {

	public CompanyModel toModel(ResultSet result) {
		CompanyModel company = new CompanyModelImpl();
		try {
			company.setId(result.getLong("id"));
			company.setName(result.getString("name"));	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return company;
	}
}
