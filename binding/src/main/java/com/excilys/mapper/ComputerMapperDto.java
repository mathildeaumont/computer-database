package com.excilys.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;

import com.excilys.dto.ComputerDto;
import com.excilys.model.Company;
import com.excilys.model.Computer;

public class ComputerMapperDto {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerMapperDto.class);
	
	public static Computer dtoToModel(ComputerDto dto) {
		if (dto == null) {
			LOGGER.error("Mapper failed : dto null");
			throw new IllegalArgumentException();
		}
		Computer model = new Computer();
		
		long id = dto.getId();
		String name = dto.getName();
		String introduced = dto.getIntroducedDate();
		String discontinued = dto.getDiscontinuedDate();
		
		DateTimeFormatter formatter;
		if (LocaleContextHolder.getLocale().toString().equals("fr")) {
			formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		} else {
			formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		}
		LocalDateTime introducedDate = LocalDateTime.parse(introduced, formatter);
		LocalDateTime discontinuedDate = LocalDateTime.parse(discontinued, formatter);
		
		model.setId(id);
		model.setName(name);
		model.setIntroducedDate(introducedDate);
		model.setDiscontinuedDate(discontinuedDate);

		LOGGER.info("Mapper succeed");
		return model;
	}
	
	public static ComputerDto modelToDto(Computer model) {
		if (model == null) {
			LOGGER.error("Mapper failed : model null");
			throw new IllegalArgumentException();
		}
		ComputerDto dto = new ComputerDto();
		
		long id = model.getId();
		String name = model.getName();
		LocalDateTime introduced = model.getIntroducedDate();
		LocalDateTime discontinued = model.getDiscontinuedDate();
		Company company = model.getCompany();
		if (company == null) {
			company = new Company();
			company.setId(0);
		}
		
		DateTimeFormatter formatter;
		if (LocaleContextHolder.getLocale().toString().equals("fr")) {
			formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		} else {
			formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		}
		
		dto.setId(id);
		dto.setName(name);
		if (introduced != null) {
			dto.setIntroducedDate(introduced.format(formatter));
		}
		if (discontinued != null) {
			dto.setDiscontinuedDate(discontinued.format(formatter));
		}
		dto.setCompany(CompanyMapperDto.modelToDto(company));

		LOGGER.info("Mapper succeed");
		return dto;
	}
	
	public static List<ComputerDto> modelsToDtos(List<Computer> models) {
		List<ComputerDto> dtos = new ArrayList<ComputerDto>();
		
		for (Computer model : models) {
			ComputerDto dto = new ComputerDto();
			
			long id = model.getId();
			String name = model.getName();
			LocalDateTime introduced = model.getIntroducedDate();
			LocalDateTime discontinued = model.getDiscontinuedDate();
			Company company = model.getCompany();
			
			DateTimeFormatter formatter;
			if (LocaleContextHolder.getLocale().toString().equals("fr")) {
				formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			} else {
				formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			}
			
			dto.setId(id);
			dto.setName(name);
			if (introduced != null) {
				dto.setIntroducedDate(introduced.format(formatter));
			}
			if (discontinued != null) {
				dto.setDiscontinuedDate(discontinued.format(formatter));
			}
			
			if (company != null) {
				dto.setCompany(CompanyMapperDto.modelToDto(company));
			}

			dtos.add(dto);
		}
		return dtos;
	}
}
