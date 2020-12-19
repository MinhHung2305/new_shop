package com.newshop.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

//class sử dụng chung cho các Model
public class AbstractModel<T> {
	private Long id;
	private Timestamp createdDate;
	private Timestamp modifiedDate;
	private String createdBy;
	private String modifiedBy;
	private long[] ids; // sinh ra khi chúng muốn xóa nhiều model 1 lúc thì truyền vào 1 mảng id
	
	private Integer page ; // số trang hiện tại bắt đầu 
	private Integer	  totalItem; // tổng số item trong db 
	private Integer maxPageItem;  // tổng số bài viết trong 1 trang 
	private Integer totalPage; // tổng số trang từ db -tính = tổng số item/ maxItem

	// tạo 1 cái list dùng chung cho các model , khi truyền tên model vào <T> thì sẽ
	// hiểu phải trả về model nào
	private List<T> listResult = new ArrayList<>();

	 // sinh ra khi muốn sắp xếp item
	private String sortName; // lưu trữ tên của column cần sắp xếp
	private String sortBy; // lưu trữ kiểu sắp xếp theo coloumn trên
	public long[] getIds() {
		return ids;
	}

	public void setIds(long[] ids) {
		this.ids = ids;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public List<T> getListResult() {
		return listResult;
	}

	public void setListResult(List<T> listResult) {
		this.listResult = listResult;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(Integer totalItem) {
		this.totalItem = totalItem;
	}

	public Integer getMaxPageItem() {
		return maxPageItem;
	}

	public void setMaxPageItem(Integer maxPageItem) {
		this.maxPageItem = maxPageItem;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

}
