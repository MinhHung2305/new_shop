package com.newshop.dao;

import java.util.List;

import com.newshop.mapper.RowMapper;

public interface GenericDAO<T> { //Generic
	<T> List<T> query(String sql,RowMapper<T> rowMapper, Object... parameters); // hàm này được xây dựng làm hàm chung , các class extends class có thể sử dụng 
	void update(String sql , Object... parameters);
	Long insert(String sql , Object... parameters);
	int count(String sql , Object... parameters);
}
