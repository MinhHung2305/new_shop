package com.newshop.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.newshop.dao.INewDAO;
import com.newshop.dao.impl.NewDAO;
import com.newshop.model.NewModel;
import com.newshop.paging.Pageble;
import com.newshop.service.INewService;

public class NewService implements INewService{
	@Inject
	private INewDAO newDAO;

	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {
		return newDAO.findByCategoryId(categoryId);
	}

	@Override
	public NewModel save(NewModel newModel) { 
		newModel.setCreatedDate(new Timestamp(System.currentTimeMillis())); // set giá trị ngày tạo
		newModel.setCreatedBy(""); // set giá trị người tạo (tạm thời để trống nhưng ko phải null)
		Long newId = newDAO.save(newModel); // save trả về kiểu long , id model vừa thêm vào
		return newDAO.findOne(newId); //findOne trả về kiểu NewModel 
	}

	@Override
	public NewModel update(NewModel updateNew) { 
		NewModel oldNew = newDAO.findOne(updateNew.getId());
		updateNew.setCreatedBy(oldNew.getCreatedBy()); /// createBy là người tạo , createDate là ngày tạo , nên nó sẽ không thay đổi 
		updateNew.setCreatedDate(oldNew.getCreatedDate());// gán giá trị createBy và createDate mới  bằng giá trị cũ
		updateNew.setModifiedDate(new Timestamp(System.currentTimeMillis())); // set giá trị ngày sửa
		updateNew.setModifiedBy(""); //set gia trị người sửa
		newDAO.update(updateNew);
		return newDAO.findOne(updateNew.getId());
	}

	@Override
	public void delete(long[] ids) {
		for (long id : ids) {
			//delete khóa ngoại trước
			newDAO.delete(id);
		}
	}

	@Override
	public List<NewModel> findAll(Pageble pageble) {
		return newDAO.findAll(pageble);
	}

	@Override
	public Integer getTotalItem() {
		return newDAO.getTotalItem();
	}

}
