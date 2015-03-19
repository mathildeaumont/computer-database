package com.excilys.mapper;

import java.sql.ResultSet;

public interface Mapper<T> {

	public T toModel(ResultSet result);

}
