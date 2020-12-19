package com.newshop.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.newshop.dao.GenericDAO;
import com.newshop.mapper.RowMapper;

public class AbstractDAO<T> implements GenericDAO<T>{
	//sử dụng resourceBundle để gọi đến phần resource , ở đây là gọi đến db.properties
	ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
	public Connection getConnection() {
		try {
//			Class.forName("com.mysql.jdbc.Driver"); //dùng để load driver từ thư viện , sau khi khai báo thư viện mysql ở file pom.xml
//			String url = "jdbc:mysql://localhost:3333/new_shop"; //khai báo tên database
//			String user = "root"; // tên của user để đăng nhập vào database
//			String password = "123456"; // mật khẩu của user
			
			Class.forName(resourceBundle.getString("driverName")); // lấy dữ liệu từ db.properties thông qua resourceBundle
			String url = resourceBundle.getString("url"); // với key "url"
			String user = resourceBundle.getString("user");
			String password = resourceBundle.getString("password"); 
			
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {  // sử dụng multiple exceptions cho try catch cho cả forName và Driver.Manager
			return null;
		}
	}

	@Override
	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
		List<T> results = new ArrayList<>(); //Khai báo 1 list để chứa dữ liệu sau khi ta có lấy từ db
		Connection connection = null;//Kết nối db
		PreparedStatement statement = null; //hàm thực thi câu lệnh sql
		ResultSet resultSet = null ; // khai báo 1 object resultSet như 1 dữ liệu dạng Table chứa dữ liệu vừa truy vấn
		try {
			connection = getConnection();  //Kết nối db
			statement = connection.prepareStatement(sql); // thực thi câu lệnh sql
			setParameter(statement,parameters); // tạo 1 phương thức set giá trị cho các parameters
			//set parameter
			resultSet = statement.executeQuery() ; // dữ liệu ở statement đổ vào resultSet ở dạng bảng
			while (resultSet.next()){	// sử dụng vòng lặp while để chạy dữ liệu của từng dòng mapRow trong resultSet ,  
				results.add(rowMapper.mapRow(resultSet)); // add các dữ liệu ở trên các mapRow vào results(dữ liệu ở đây tùy vào bạn truyền model nào vào List<T>
			}
			return results; // trả dữ liệu về ArrayList
		} catch (SQLException e) {
			return null;
		}finally { //finally luôn được thực hiện dù có phần trên có lỗi hay không
			//luôn luôn đóng kết nối và các object để tránh bị lỗi trùng lặp dữ liệu(duplicate) 
			try { //phải sử dụng tiếp try catch vì connection co thể exception
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			}catch (SQLException e2) {
				return null;
			}
		}

	}

	private void setParameter(PreparedStatement statement, Object... parameters) {
		//để ý xem kiểu dữ liệu insert vào db là kiểu gì để check 
		try {
			for(int i =0 ; i <= parameters.length ; i++) {
				Object parameter = parameters[i];
				int index = i+1;
				if(parameter instanceof Long) {
					statement.setLong(index, (Long) parameter);
				}else if(parameter instanceof String) {
					statement.setString(index, (String) parameter);
				}else if(parameter instanceof Integer) {
					statement.setInt(index, (Integer)parameter);
				}else if(parameter instanceof Timestamp) {
					statement.setTimestamp(index, (Timestamp)parameter);
				} /* không cần check dữ liệu null nữa vì null sẽ ko cho vào db
					 * else if(parameter == null) { statement.setNull(index, Types.NULL ); }
					 */
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(String sql, Object... parameters) {
		Connection connection = null;//Kết nối db
		PreparedStatement statement = null; //hàm thực thi câu lệnh sql
		try {

			connection = getConnection();  //Kết nối db
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql); // thực thi câu lệnh sql
			setParameter(statement,parameters); // tạo 1 phương thức set giá trị cho các parameters
			statement.executeUpdate();
			connection.commit();
		}catch (SQLException e) {if (connection !=null ) {
			try {
				connection.rollback(); //rollback để kiểm tra , nếu 1 trong cá thao tác bị lỗi thì rollback và trở về trạng thái cũ , tất cả ko đc lưu vào db
			} catch (SQLException e1) { 
				e1.printStackTrace();
			}
		}
		}finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
	@Override
	public Long insert(String sql, Object... parameters) {
		Long id = null;
		ResultSet resultSet = null;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection(); // mở kết nối dữ liệu
			connection.setAutoCommit(false); // set commit ko cho tự động commit , để có thể rollback
			statement = connection.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);// statement.RETURN_GENERATED_KEYS) //sử dụng preparedStatement để truyền câu lệnh truy vấn (query) sql vào statement, và phải lấy ra khóa chính generated_key
			setParameter(statement, parameters);//truyền các tham số vào câu lệnh sql
			statement.executeUpdate(); //với thêm sửa xóa - để thực thi câu lệnh query với executeUpdate
			resultSet = statement.getGeneratedKeys(); // vì id trong db là khóa chính và tự tạo , tự tăng  nên sử dụng getGenerateKeys để lấy được nó
			if (resultSet.next()) {
				id = resultSet.getLong(1); //trả về cái id của bài viết đó , tự tăng
			}
			connection.commit(); //phải commit để dữ liệu trong database thay đổi
			return id;
		} catch (SQLException e) {
			if (connection !=null ) {
				try {
					connection.rollback(); //rollback để kiểm tra , nếu 1 trong cá thao tác bị lỗi thì rollback và trở về trạng thái cũ , tất cả ko đc lưu vào db
				} catch (SQLException e1) { 
					e1.printStackTrace();
				} 
			} 
		}finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}

			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public int count(String sql, Object... parameters) {
		Connection connection = null;//Kết nối db
		PreparedStatement statement = null; //hàm thực thi câu lệnh sql
		ResultSet resultSet = null ; // khai báo 1 object resultSet như 1 dữ liệu dạng Table chứa dữ liệu vừa truy vấn
		try {
			int count = 0 ;
			connection = getConnection();  //Kết nối db
			statement = connection.prepareStatement(sql); // thực thi câu lệnh sql
			setParameter(statement,parameters); // tạo 1 phương thức set giá trị cho các parameters
			//set parameter
			resultSet = statement.executeQuery() ; // dữ liệu ở statement đổ vào resultSet ở dạng bảng
			while (resultSet.next()){	// sử dụng vòng lặp while để chạy dữ liệu của từng dòng mapRow trong resultSet ,  
				count = resultSet.getInt(1);
			}
			return count; // trả về 1 số nguyên (int)
		} catch (SQLException e) {
			return 0;
		}finally { //finally luôn được thực hiện dù có phần trên có lỗi hay không
			//luôn luôn đóng kết nối và các object để tránh bị lỗi trùng lặp dữ liệu(duplicate) 
			try { //phải sử dụng tiếp try catch vì connection co thể exception
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			}catch (SQLException e2) {
				return 0;
			}
		}
	}
}
