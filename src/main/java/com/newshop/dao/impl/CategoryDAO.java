package com.newshop.dao.impl;

import java.util.List;

import com.newshop.dao.ICategoryDAO;
import com.newshop.mapper.CategoryMapper;
import com.newshop.model.CategoryModel;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO {

	@Override
	public List<CategoryModel> findAll() {
		String sql="select * from category"; //khai báo 1 biến dạng chuỗi chứa câu lệnh query
		return query(sql, new CategoryMapper());
	}
}

