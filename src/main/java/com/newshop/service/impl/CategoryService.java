package com.newshop.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.newshop.dao.impl.CategoryDAO;
import com.newshop.model.CategoryModel;
import com.newshop.service.ICategorySevice;

public class CategoryService implements ICategorySevice{
//	private CategoryDAO categoryDAO;
//	private CategoryDAO categoryDAO1;
//	private CategoryService() {
//		categoryDAO = new CategoryDAO();
//		categoryDAO1 = new CategoryDAO();
//	}
	
//	sử dụng inject ta sẽ không phải khai báo như trên
	@Inject private CategoryDAO categoryDAO;
	
	@Override
	public List<CategoryModel> findAll() {
		return categoryDAO.findAll(); // trả về dữ liệu trong hàm findAll của  CategoryDAO
	}

}
