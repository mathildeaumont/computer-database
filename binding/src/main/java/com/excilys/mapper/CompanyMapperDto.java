package com.excilys.mapper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.dto.CompanyDto;
import com.excilys.model.Company;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanyMapperDto.
 */
public class CompanyMapperDto {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyMapperDto.class);

	/**
	 * Dto to model.
	 *
	 * @param dto the dto
	 * @return the company
	 */
	public static Company dtoToModel(CompanyDto dto) {
		if (dto == null) {
			LOGGER.error("Mapper failed : dto null");
			throw new IllegalArgumentException();
		}
		Company model = new Company();
		model.setName(dto.getName());
		model.setId(dto.getId());
		LOGGER.info("Mapper succeed");
		return model;
	}
	
	/**
	 * Model to dto.
	 *
	 * @param model the model
	 * @return the company dto
	 */
	public static CompanyDto modelToDto(Company model) {
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
	
	/**
	 * Models to dtos.
	 *
	 * @param models the models
	 * @return the list
	 */
	public static List<CompanyDto> modelsToDtos(List<Company> models) {
		List<CompanyDto> dtos = new ArrayList<CompanyDto>();
		for (Company model : models) {
			CompanyDto dto = modelToDto(model);
			dtos.add(dto);
		}
		return dtos;
	}
}
