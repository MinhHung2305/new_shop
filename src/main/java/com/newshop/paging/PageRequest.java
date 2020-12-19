package com.newshop.paging;

import com.newshop.sort.Sorter;

public class PageRequest implements Pageble{
	//class này để tính toán dữ liệu để phân trang
	private Integer page ; //khai báo page
	private Integer maxPageItem ; // khai báo maxPageItem
	private Sorter sorter;
	
	public PageRequest(Integer page , Integer maxPageItem , Sorter sorter){
		this.page = page;
		this.maxPageItem =maxPageItem;
		this.sorter = sorter;
	}
	@Override
	public Integer getPage() {
		return this.page;
	}

	@Override
	public Integer getOffset() {
		//kiểm tra nếu page = null thì sẽ không phân trang
		if (this.page != null || this.maxPageItem != null) {
			return (this.page -1)*this.maxPageItem;
		}
		return null;
	}

	@Override
	public Integer getLimit() {
		return this.maxPageItem;
	}
	@Override
	public Sorter getSorter() { 
		if(this.sorter != null) {
			return this.sorter;
		}
		return null;
	}

	
}
