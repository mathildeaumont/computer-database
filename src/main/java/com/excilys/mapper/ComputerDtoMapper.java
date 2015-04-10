package com.excilys.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.dto.ComputerDto;
import com.excilys.model.CompanyModelImpl;
import com.excilys.model.ComputerModel;
import com.excilys.model.ComputerModelImpl;

public class ComputerDtoMapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDtoMapper.class);
	
	public static ComputerModel dtoToModel(ComputerDto dto) {
		if (dto == null) {
			LOGGER.error("Mapper failed : dto null");
			throw new IllegalArgumentException();
		}
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

		LOGGER.info("Mapper succeed");
		return model;
	}
	
	public static ComputerDto modelToDto(ComputerModel model) {
		if (model == null) {
			LOGGER.error("Mapper failed : model null");
			throw new IllegalArgumentException();
		}
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
		dto.setCompany(CompanyMapperDto.modelToDto(company));

		LOGGER.info("Mapper succeed");
		return dto;
	}
	
	public static List<ComputerDto> modelsToDtos(List<ComputerModel> models) {
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
			dto.setCompany(CompanyMapperDto.modelToDto(company));

			dtos.add(dto);
		}
		return dtos;
	}
}
