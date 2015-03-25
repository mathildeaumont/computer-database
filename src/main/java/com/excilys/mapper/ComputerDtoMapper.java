package com.excilys.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.excilys.dto.ComputerDto;
import com.excilys.model.CompanyModelImpl;
import com.excilys.model.ComputerModel;
import com.excilys.model.ComputerModelImpl;

public class ComputerDtoMapper {

	public ComputerModel dtoToModel(ComputerDto dto) {
		ComputerModel model = new ComputerModelImpl();
		
		String name = dto.getName();
		String introduced = dto.getIntroduced();
		String discontinued = dto.getDiscontinued();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime introducedDate = LocalDateTime.parse(introduced, formatter);
		LocalDateTime discontinuedDate = LocalDateTime.parse(discontinued, formatter);
		
		model.setName(name);
		model.setIntroducedDate(introducedDate);
		model.setDiscontinuedDate(discontinuedDate);

		return model;
	}
	
	public ComputerDto modelToDto(ComputerModel model) {
		ComputerDto dto = new ComputerDto();
		
		String name = model.getName();
		LocalDateTime introduced = model.getIntroducedDate();
		LocalDateTime discontinued = model.getDiscontinuedDate();
		CompanyModelImpl company = model.getCompany();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		dto.setName(name);
		dto.setIntroduced(introduced.format(formatter));
		dto.setDiscontinued(discontinued.format(formatter));
		dto.setCompany(company.toString());
		
		return dto;
	}
}
