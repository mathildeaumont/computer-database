package com.excilys.mapper;

import java.util.ArrayList;
import java.util.List;

import com.excilys.dto.CompanyDto;
import com.excilys.model.CompanyModel;
import com.excilys.model.CompanyModelImpl;

public class CompanyMapperDto {

	public CompanyModel dtoToModel(CompanyDto dto) {
		CompanyModel model = new CompanyModelImpl();
		model.setName(dto.getName());
		model.setId(dto.getId());
		return model;
	}
	
	public CompanyDto modelToDto(CompanyModel model) {
		CompanyDto dto = new CompanyDto();
		dto.setName(model.getName());
		dto.setId(model.getId());	
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
