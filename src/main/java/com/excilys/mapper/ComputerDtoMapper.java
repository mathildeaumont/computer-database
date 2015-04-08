package com.excilys.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.excilys.dto.ComputerDto;
import com.excilys.model.CompanyModelImpl;
import com.excilys.model.ComputerModel;
import com.excilys.model.ComputerModelImpl;

public class ComputerDtoMapper {

	public ComputerModel dtoToModel(ComputerDto dto) {
		ComputerModel model = new ComputerModelImpl();
		
		long id = dto.getId();
		String name = dto.getName();
		String introduced = dto.getIntroducedDate();
		String discontinued = dto.getDiscontinuedDate();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime introducedDate = LocalDateTime.parse(introduced, formatter);
		LocalDateTime discontinuedDate = LocalDateTime.parse(discontinued, formatter);
		
		model.setId(id);
		model.setName(name);
		model.setIntroducedDate(introducedDate);
		model.setDiscontinuedDate(discontinuedDate);

		return model;
	}
	
	public ComputerDto modelToDto(ComputerModel model) {
		ComputerDto dto = new ComputerDto();
		
		long id = model.getId();
		String name = model.getName();
		LocalDateTime introduced = model.getIntroducedDate();
		LocalDateTime discontinued = model.getDiscontinuedDate();
		CompanyModelImpl company = model.getCompany();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		dto.setId(id);
		dto.setName(name);
		if (introduced != null) {
			dto.setIntroducedDate(introduced.format(formatter));
		}
		if (discontinued != null) {
			dto.setDiscontinuedDate(discontinued.format(formatter));
		}
		CompanyMapperDto mapper = new CompanyMapperDto();
		dto.setCompany(mapper.modelToDto(company));

		return dto;
	}
	
	public List<ComputerDto> modelsToDtos(List<ComputerModel> models) {
		List<ComputerDto> dtos = new ArrayList<ComputerDto>();
		
		for (ComputerModel model : models) {
			ComputerDto dto = new ComputerDto();
			
			long id = model.getId();
			String name = model.getName();
			LocalDateTime introduced = model.getIntroducedDate();
			LocalDateTime discontinued = model.getDiscontinuedDate();
			CompanyModelImpl company = model.getCompany();
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
			dto.setId(id);
			dto.setName(name);
			if (introduced != null) {
				dto.setIntroducedDate(introduced.format(formatter));
			}
			if (discontinued != null) {
				dto.setDiscontinuedDate(discontinued.format(formatter));
			}
			CompanyMapperDto mapper = new CompanyMapperDto();
			dto.setCompany(mapper.modelToDto(company));

			dtos.add(dto);
		}
		return dtos;
	}
}
