package com.excilys.mapper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.dto.CompanyDto;
import com.excilys.model.CompanyModel;
import com.excilys.model.CompanyModelImpl;

public class CompanyMapperDto {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyMapperDto.class);

	public CompanyModel dtoToModel(CompanyDto dto) {
		if (dto == null) {
			LOGGER.error("Mapper failed : dto null");
			throw new IllegalArgumentException();
		}
		CompanyModel model = new CompanyModelImpl();
		model.setName(dto.getName());
		model.setId(dto.getId());
		LOGGER.info("Mapper succeed");
		return model;
	}
	
	public CompanyDto modelToDto(CompanyModel model) {
		if (model == null) {
			LOGGER.error("Mapper failed : model null");
			throw new IllegalArgumentException();
		}
		CompanyDto dto = new CompanyDto();
		dto.setName(model.getName());
		dto.setId(model.getId());
		LOGGER.info("Mapper succeed");
		return dto;
	}
	
	public List<CompanyDto> modelsToDtos(List<CompanyModel> models) {
		List<CompanyDto> dtos = new ArrayList<CompanyDto>();
		for (CompanyModel model : models) {
			CompanyDto dto = modelToDto(model);
			dtos.add(dto);
		}
		return dtos;
	}
}
