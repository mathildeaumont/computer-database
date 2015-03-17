package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.model.CompanyModel;

public class CompanyMapper {

	public static CompanyModel toModel(ResultSet result) {
		CompanyModel company = new CompanyModel();
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
