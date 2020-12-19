package com.newshop.dao;

import java.util.List;

import com.newshop.model.CategoryModel;

public interface ICategoryDAO extends GenericDAO<CategoryModel> {
	List<CategoryModel> findAll(); // các method trong interface luôn luôn public nên ko cần khai báo thêm modifier
	
}
