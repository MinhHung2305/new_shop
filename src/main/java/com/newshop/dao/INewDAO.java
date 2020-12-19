package com.newshop.dao;

import java.util.List;

import com.newshop.model.NewModel;
import com.newshop.paging.Pageble;

public interface INewDAO extends GenericDAO<NewModel> {
	NewModel findOne(Long id);
	List<NewModel> findByCategoryId(Long categoryId);
	Long save(NewModel newModel);	//tra về 1 Id kiểu bigint(Long)
	void update(NewModel updateNew);
	void delete(long id);
	List<NewModel> findAll(Pageble pageble);// sinh ra 2 param truyền vào khi phân trang
	Integer getTotalItem();
}
