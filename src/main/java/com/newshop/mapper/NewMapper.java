package com.newshop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.newshop.model.NewModel;

public class NewMapper implements RowMapper<NewModel>{

	@Override
	public NewModel mapRow(ResultSet resultSet) {
		//Khi thêm mới or khi update sẽ sử dụng để map dữ liệu vừa được thêm or update lên client
		try {
			NewModel news = new NewModel(); // khai báo 1 object CategoryModel để chứa dữ liệu từ resultSet
			news.setId(resultSet.getLong("id"));// lấy dữ liệu từng phần tử trong mỗi hàng của resultSet đổ về đúng các thuộc tính(vd: id , code ,...) tương ứng trong CategoryModel
			news.setTitle(resultSet.getString("title"));//trong nháy kép là tên column trong db
			news.setContent(resultSet.getString("content"));
			news.setCategoryId(resultSet.getLong("categoryid"));
			news.setThumbnail(resultSet.getString("thumbnail"));
			news.setShortDescription(resultSet.getString("shortdescription"));
			news.setCreatedDate(resultSet.getTimestamp("createddate"));
			news.setCreatedBy(resultSet.getString("createdby"));
			if(resultSet.getTimestamp("modifieddate") != null) {
				news.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			}
			if(resultSet.getString("modifiedby") != null) {
				news.setModifiedBy(resultSet.getString("modifiedby"));
			}
			return news;
		} catch (SQLException e) {
			return null;
		}   

	}

}
