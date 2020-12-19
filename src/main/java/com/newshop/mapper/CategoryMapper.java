package com.newshop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.newshop.model.CategoryModel;

public class CategoryMapper implements RowMapper<CategoryModel>{

	@Override
	public CategoryModel mapRow(ResultSet resultSet) {
		try {
			CategoryModel category = new CategoryModel(); // khai báo 1 object CategoryModel để chứa dữ liệu từ resultSet
			category.setId(resultSet.getLong("id")); // lấy dữ liệu từng phần tử trong mỗi hàng của resultSet đổ về đúng các thuộc tính(vd: id , code ,...) tương ứng trong CategoryModel  
			category.setCode(resultSet.getString("code"));//trong nháy kép là tên column trong db
			category.setName(resultSet.getString("name"));
		} catch (SQLException e) {
 
		}
		return null;
	}

}
