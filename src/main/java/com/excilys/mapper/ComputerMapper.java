package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.model.CompanyModelImpl;
import com.excilys.model.ComputerModel;
import com.excilys.model.ComputerModelImpl;

@Component
public class ComputerMapper implements RowMapper<ComputerModel> {

	public ComputerModel toModel(ResultSet result) {
		ComputerModel computer = new ComputerModelImpl();
		try {
			computer.setId(result.getLong("id"));
			computer.setName(result.getString("name"));	
			LocalDateTime introduced;
			introduced = result.getTimestamp("introduced") == null ? null : result.getTimestamp("introduced").toLocalDateTime();
			LocalDateTime discontinued;
			discontinued = result.getTimestamp("discontinued") == null ? null : result.getTimestamp("discontinued").toLocalDateTime();
			computer.setIntroducedDate(introduced);
			computer.setDiscontinuedDate(discontinued);
			CompanyModelImpl company = new CompanyModelImpl();
			long companyId = result.getLong("company_id");
			if (companyId != 0) {
				company.setId(companyId);
				company.setName(result.getString("company.name"));
			}
			computer.setCompany(company);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return computer;
	}

	@Override
	public ComputerModel mapRow(ResultSet result, int rowNum) throws SQLException {
		if (result == null) {
			throw new IllegalArgumentException();
		}
		ComputerModel computer = new ComputerModelImpl();
		computer.setId(result.getLong("id"));
		computer.setName(result.getString("name"));	
		LocalDateTime introduced;
		introduced = result.getTimestamp("introduced") == null ? null : result.getTimestamp("introduced").toLocalDateTime();
		LocalDateTime discontinued;
		discontinued = result.getTimestamp("discontinued") == null ? null : result.getTimestamp("discontinued").toLocalDateTime();
		computer.setIntroducedDate(introduced);
		computer.setDiscontinuedDate(discontinued);
		CompanyModelImpl company = new CompanyModelImpl();
		long companyId = result.getLong("company_id");
		if (companyId != 0) {
			company.setId(companyId);
			company.setName(result.getString("company.name"));
		}
		computer.setCompany(company);
		return computer;
	}
}
