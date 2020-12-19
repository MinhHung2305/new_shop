package com.newshop.service;

import java.util.List;

import com.newshop.model.NewModel;
import com.newshop.paging.Pageble;

public interface INewService {
	List<NewModel> findByCategoryId(Long categoryId);
	NewModel save(NewModel newModel);
	NewModel update(NewModel newModel);
	void delete(long[] ids);
	// sinh ra 2 param truyền vào khi phân trang
	// Thêm 2 param là sortName và sortBy
	//List<NewModel> findAll(Integer offset , Integer limit, String softName , String sortBy);//quá nhiều tham số , cần phải tối ưu
	List<NewModel> findAll(Pageble pageble); // tối ưu code , nhét tất cả cá tham số vào 1 class pageble, và get set các giá trị cho nó 
	Integer getTotalItem();
}
