package com.excilys.persistence;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.h2.engine.SysProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.mapper.ComputerMapper;
import com.excilys.model.ComputerModel;
import com.excilys.model.Page;

@Repository
public class ComputerDaoImpl implements ComputerDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ComputerMapper computerMapper;

	
	public int getLength() {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) as Total FROM computer", Integer.class);
	}
	
	public List<ComputerModel> getAllComputers() {
		return jdbcTemplate.query("SELECT * FROM computer as compu left "
					+ "outer join company as compa ON compu.company_id = compa.id ORDER by compu.id;", computerMapper);
	}
	
	public List<ComputerModel> getAllComputersByPage(Page<ComputerModel> page, String order, String direction, String search) {
		List<Object> param = new ArrayList<Object>();
		String query = "SELECT * FROM computer as compu left outer join company as company ON compu.company_id = company.id WHERE compu.name LIKE ? OR company.name LIKE ? ORDER BY %s %s LIMIT ? OFFSET ?;";
		param.add("%" + search + "%");
		param.add("%" + search + "%");
		param.add(page.getNbResults());
		param.add(page.getOffset());
		final String request = String.format(query, order, direction);
		return jdbcTemplate.query(request, param.toArray(), computerMapper);
	}
	
	public int getLength(String search) {
		List<Object> param = new ArrayList<Object>();
		String query = "SELECT COUNT(*) as Total FROM computer as compu left "
				+ "outer join company as compa ON compu.company_id = compa.id WHERE compu.name LIKE ? OR compa.name LIKE ? ;";
		param.add("%" + search + "%");
		param.add("%" + search + "%");
		return jdbcTemplate.queryForObject(query, param.toArray(), Integer.class);
	}

	public ComputerModel getComputerDetails(long idComputer) {
		String query = "SELECT * FROM computer as compu left "
				+ "outer join company as company ON compu.company_id = company.id WHERE compu.id = ? ORDER by compu.id;";
		return jdbcTemplate.queryForObject(query, new Object[] { idComputer }, computerMapper);
	}

	public void createComputer(ComputerModel computer) {
		String query = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)";
		Timestamp introduced = null;
		if (computer.getIntroducedDate() != null) {
			introduced = Timestamp.valueOf(computer.getIntroducedDate());
		}
		Timestamp discontinued = null;
		if (computer.getDiscontinuedDate() != null) {
			discontinued = Timestamp.valueOf(computer.getIntroducedDate());
		}
		jdbcTemplate.update(query, new Object[] {computer.getName(), introduced, 
				discontinued, computer.getCompany().getId()});
	}

	public void updateComputer(ComputerModel computer) {
		Timestamp introduced = null;
		if (computer.getIntroducedDate() != null) {
			introduced = Timestamp.valueOf(computer.getIntroducedDate());
		}
		Timestamp discontinued = null;
		if (computer.getDiscontinuedDate() != null) {
			discontinued = Timestamp.valueOf(computer.getIntroducedDate());
		}
		String query = "UPDATE computer set name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?;";
		jdbcTemplate.update(query, new Object[] { computer.getName(), introduced, discontinued, 
				computer.getCompany().getId(), computer.getId()});
	}

	public void deleteComputer(long computerId) {
		jdbcTemplate.update("DELETE FROM computer WHERE id = ?;", computerId);
	}
	
	public void deleteComputerByCompanyId(long companyId) {
		String query = "DELETE FROM computer WHERE company = ?;";
		jdbcTemplate.update(query, companyId);
	}

}
