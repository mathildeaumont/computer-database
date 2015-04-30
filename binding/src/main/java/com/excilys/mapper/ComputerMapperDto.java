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

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerMapperDto.
 */
public class ComputerMapperDto {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerMapperDto.class);
	
	/**
	 * Dto to model.
	 *
	 * @param dto the dto
	 * @return the computer
	 */
	public static Computer dtoToModel(ComputerDto dto) {
		if (dto == null) {
			LOGGER.error("Mapper failed : dto null");
			throw new IllegalArgumentException();
		}		
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
		
		Computer computer = Computer.builder().id(id)
											  .name(name)
											  .introduced(introducedDate)
											  .discontinued(discontinuedDate)
											  .build();

		LOGGER.info("Mapper succeed");
		return computer;
	}
	
	/**
	 * Model to dto.
	 *
	 * @param model the model
	 * @return the computer dto
	 */
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
	
	/**
	 * Models to dtos.
	 *
	 * @param models the models
	 * @return the list
	 */
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
