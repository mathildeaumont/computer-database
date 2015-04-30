package com.excilys.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.dto.ComputerDto;
import com.excilys.mapper.ComputerMapperDto;
import com.excilys.service.ComputerService;

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerRestController.
 */
@RestController
@RequestMapping("/rest/computers")
public class ComputerRestController {
	
	/** The computer service. */
	@Autowired
	private ComputerService computerService;
	
	/**
	 * Gets the computers.
	 *
	 * @return the computers
	 */
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public List<ComputerDto> getComputers() {
		return ComputerMapperDto.modelsToDtos(computerService.getAll());
	}
	
	/**
	 * Gets the computer by id.
	 *
	 * @param id the id
	 * @return the computer by id
	 */
	@RequestMapping(value = "/getById", method = RequestMethod.GET)
	public ComputerDto getComputerById(@RequestParam("id") long id) {
		return ComputerMapperDto.modelToDto(computerService.getById(id));
	}

}
