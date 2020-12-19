package com.newshop.service;

import java.util.List;

import com.newshop.model.CategoryModel;

public interface ICategorySevice {
List<CategoryModel> findAll(); //khai báo 1 phương thức , ở đây lấy ra dữ liệu trong bảng category ra dạng list 
}
