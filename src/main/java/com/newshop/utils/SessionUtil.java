package com.newshop.utils;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {
	// class này để thực thi và duy trì ng dùng khi đăng nhập vào hệ thống
	private static SessionUtil sessionUtil = null;
	//Khởi tạo ra 1 phương thức tĩnh , khai báo khởi tạo object ngay trong class của nó
	public static SessionUtil getInstance() {
		if(sessionUtil == null) {	//nếu nó chưa được khởi tạo thì khởi tạo nó
			sessionUtil = new SessionUtil();
		}
		return sessionUtil;// nếu đã được khởi tạo thì return nó
	}
	
	// khởi tạo ra 1 Httprequest để sử dụng đc session 
	// key tương ứng như là tên của các trường thuộc tính muốn lấy ra 
	//vd: username- key: USERNAME, fullname- key: FULLNAME, ... 
	// Object để chứa dữ liệu giá trị của các key , là Object vì chưa biết kiểu dữ liệu là gì  
	public void putValue( HttpServletRequest request, String key, Object value){
		// lấy giá trị(getSession) của các session từ request và gán giá trị(setAttr) value cho key
		request.getSession().setAttribute(key, value); 
	}
	// hàm lấy dữ liệu , truyền vào request và key-để lấy dữ liệu dựa vào key 
	public Object getValue(HttpServletRequest request, String key) {
	// trả về dữ liệu Object được getSession ra dựa vào key (được định nghĩa và truyền dữ liệu ở hàm putValue) 
		return request.getSession().getAttribute(key);
	}
	
	public void removeValue(HttpServletRequest request, String key) {
		request.getSession().removeAttribute(key); // xóa session
	}
}
