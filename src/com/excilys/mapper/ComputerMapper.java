package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.excilys.model.CompanyModel;
import com.excilys.model.ComputerModel;

public class ComputerMapper {

	public static ComputerModel toModel(ResultSet result) {
		ComputerModel computer = new ComputerModel();
		try {
			computer.setId(result.getLong("id"));
			computer.setName(result.getString("name"));	
			LocalDateTime introduced;
			introduced = result.getTimestamp("introduced") == null ? null : result.getTimestamp("introduced").toLocalDateTime();
			LocalDateTime discontinued;
			discontinued = result.getTimestamp("discontinued") == null ? null : result.getTimestamp("discontinued").toLocalDateTime();
			computer.setIntroducedDate(introduced);
			computer.setDiscontinuedDate(discontinued);
			CompanyModel company = new CompanyModel();
			long companyId = result.getLong("company_id");
			if (companyId != 0) {
				company.setId(companyId);
				company.setName(result.getString("compa.name"));
			}
			computer.setCompany(company);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return computer;
	}
}
