package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.model.CompanyModel;
import com.excilys.model.CompanyModelImpl;

@Component
public class CompanyMapper implements RowMapper<CompanyModel> {

	/*public CompanyModel toModel(ResultSet result) {
		CompanyModel company = new CompanyModelImpl();
		try {
			company.setId(result.getLong("id"));
			company.setName(result.getString("name"));	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return company;
	}*/

	@Override
	public CompanyModel mapRow(ResultSet result, int rowNum) throws SQLException {
		CompanyModel company = new CompanyModelImpl();
		company.setId(result.getLong("id"));
		company.setName(result.getString("name"));	
		return company;
	}
}
