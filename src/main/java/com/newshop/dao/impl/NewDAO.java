package com.newshop.dao.impl;

import java.util.List;

import com.newshop.dao.INewDAO;
import com.newshop.mapper.NewMapper;
import com.newshop.model.NewModel;
import com.newshop.paging.Pageble;

public class NewDAO extends AbstractDAO<NewModel> implements INewDAO {
	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {	// Hàm này để lấy ra dữ liệu ra db
		String sql="select * from news where category id = ?"; //khai báo 1 biến dạng chuỗi chứa câu lệnh query
		return query(sql, new NewMapper(), categoryId); //hàm query đê thực thi câu lệnh , lấy ra dữ liệu và trả về dạng list
	}

	@Override
	public Long save(NewModel newModel) {	// Hàm này để insert dữ liệu vào db
		StringBuilder sql = new StringBuilder("Insert into news (title , content, thumbnail, shortdescription ,");
		sql.append("categoryid, createdby, createddate) values(?,?,?,?,?,?,?)"); // câu lệnh sql
		return insert(sql.toString(), newModel.getTitle() , newModel.getContent() ,
				newModel.getThumbnail(), newModel.getShortDescription(),newModel.getCategoryId(),
				newModel.getCreatedBy(),newModel.getCreatedDate()); // return lại hàm insert ở bên abstractDAO
	}

	@Override
	public NewModel findOne(Long id) {
		String sql = "Select * from news where id = ?";
		List<NewModel> news = query(sql,new NewMapper(),id); // gọi đến hàm query ở class abstractDAO
		return news.isEmpty() ? null : news.get(0); // kiểm tra nếu  ko có dữ liệu sẽ trả về null và có sẽ trả về dữ liệu phần tử  đầu tiên và duy nhất trong mảng get(0) 
	
	}

	@Override
	public void update(NewModel updateNew) {
		// sử dụng stringbuildder để nó ko tốn thời gian và tài nguyên như cộng chuỗi string
		StringBuilder sql =new StringBuilder("Update  news set title = ? ,thumbnail =? ,") ;
		sql.append("shortdescription = ? ,content = ? ,categoryid = ? ,");// câu lệnh sql
		sql.append("createddate = ?, createdby= ? , modifieddate= ? , modifiedby= ? where id = ?");
		update(sql.toString(), updateNew.getTitle(),updateNew.getThumbnail(),updateNew.getShortDescription(),
				updateNew.getContent(),updateNew.getCategoryId(),updateNew.getCreatedDate(),
				updateNew.getCreatedBy(),updateNew.getModifiedDate(), updateNew.getModifiedBy(),
				updateNew.getId()); // lấy dữ liệu từ NewModel gán vào các tham số ? trong câu sql 
		// return lại hàm insert ở bên abstractDAO

	}

	@Override
	public void delete(long id) {
		 String sql = "Delete from news where id=?";
		 update(sql, id);
	}

	/* không sử dụng hàm hàm finAll này vì khi có quá nhiều parameter hoặc muốn thêm param thì hàn code sẽ rất khó
	@Override
	public List<NewModel> findAll(Integer offset , Integer limit , String sortName , String sortBy) { // hàm này dùng chung nên các param truyền vào có thể null nên check if-else
		// 2 parameter offset + limit sinh ra khi phân trang , sortName+sortBy để sắp xếp item
		StringBuilder sql=new StringBuilder("select * from news"); // sd stringBuider cộng chuỗi
		if(sortName != null & sortBy != null) { // vì hàm findAll là hàm chung , nên if để nếu có sắp xếp sẽ nhận thêm cau sql này
			sql.append(" order by "+sortName+" "+sortBy); // truyền thẳng các param vào câu sql
		}
		if(offset != null & limit != null) { // tương tự nếu có phân trang sẽ nhận thêm câu sql limit
			// sql.append(" limit ?,? ");
			// ** return query(sql.toString(), new NewMapper(), offset,limit);
			sql.append(" limit "+offset+" , "+limit);
		}
		// khi ko sử dụng truyền param thông qua hàm query như ở ** thì không cần else
		return query(sql.toString(), new NewMapper()); //hàm query đê thực thi câu lệnh , lấy ra dữ liệu và trả về dạng list
	}
	*/
	@Override
	public List<NewModel> findAll(Pageble pageble) { // hàm này dùng chung nên các param truyền vào có thể null nên check if-else
		// 2 parameter offset + limit sinh ra khi phân trang , sortName+sortBy để sắp xếp item
		StringBuilder sql=new StringBuilder("select * from news"); // sd stringBuider cộng chuỗi
		if(pageble.getSorter() != null) { // vì hàm findAll là hàm chung , nên if để nếu có sắp xếp sẽ nhận thêm cau sql này
			sql.append(" order by "+pageble.getSorter().getSortName()+" "+pageble.getSorter().getSortBy()); // truyền thẳng các param vào câu sql
		}
		if(pageble.getOffset() != null & pageble.getLimit() != null) { // tương tự nếu có phân trang sẽ nhận thêm câu sql limit
			// sql.append(" limit ?,? ");
			// ** return query(sql.toString(), new NewMapper(), offset,limit);
			sql.append(" limit "+pageble.getOffset()+" , "+pageble.getLimit());
		}
		// khi ko sử dụng truyền param thông qua hàm query như ở ** thì không cần else
		return query(sql.toString(), new NewMapper()); //hàm query đê thực thi câu lệnh , lấy ra dữ liệu và trả về dạng list
	}

	@Override
	public Integer getTotalItem() {
		String sql="select count(*) from news "; //khai báo 1 biến dạng chuỗi chứa câu lệnh đếm số hàng bảng new
		return count(sql); //hàm query đê thực thi câu lệnh , lấy ra dữ liệu và trả về dạng integer
 
	}
}
