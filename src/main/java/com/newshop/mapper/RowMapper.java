package com.newshop.mapper;

import java.sql.ResultSet;

public interface RowMapper<T> { 
	//class này được tạo ra vì việc map qua lại phần setAttribute trong impl 
	T mapRow(ResultSet rs);
	
}
