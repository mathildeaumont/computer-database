package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.model.Company;

@Component
public class CompanyMapper implements RowMapper<Company> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyMapper.class);
	
	@Override
	public Company mapRow(ResultSet result, int rowNum) throws SQLException {
		if (result == null) {
			LOGGER.error("Mapper failed : result null");
			throw new IllegalArgumentException();
		}
		Company company = new Company();
		company.setId(result.getLong("id"));
		company.setName(result.getString("name"));	
		LOGGER.info("Mapper succeed");
		return company;
	}
}
