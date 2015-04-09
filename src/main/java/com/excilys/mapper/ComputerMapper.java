package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.model.CompanyModelImpl;
import com.excilys.model.ComputerModel;
import com.excilys.model.ComputerModelImpl;

@Component
public class ComputerMapper implements RowMapper<ComputerModel> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerMapper.class);
	
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
			LOGGER.error("Mapper failed : result null");
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
		LOGGER.info("Mapper succeed");
		return computer;
	}
}
