package com.newshop.utils;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class FormUtil  {
	
	/*// class này tạo ra để lấy dữ liệu từ bên jsp( dạng string) trả về đúng kiểu dữ liệu để controller xử lý
	// lưu ý các key-value phải trùng với trên của các param trong Model
	@SuppressWarnings({ "unchecked" })
	public static <T> T toModel(Class<T> clazz, HttpServletRequest request){   
		// sử dụng thư viện HttpServletRequest(chứa tất cả các key value được set bên jsp) 
		T object = null;
		try {
			//tạo ra 1 (T) object , và khởi để chứa dữ liệu kiểu T ( các Model)
			object =(T) clazz.newInstance(); 
			//object = (T)clazz.getDeclaredConstructor().newInstance();
			// sử dụng beanUtils.populate lấy ra tất cả các param , map tất cả các key-value từ bên jsp vào trong (T) object (Model)
			BeanUtils.populate(object, request.getParameterMap());  
		} 
		catch (InstantiationException | IllegalAccessException | InvocationTargetException  | SecurityException e) {
			 System.out.println(e.getMessage());
		}
		return object;
	}*/
	@SuppressWarnings("unchecked")
	public static <T> T toModel(Class<T> clazz, HttpServletRequest request) {
		T object = null;
		try {
			object = clazz.newInstance();
			BeanUtils.populate(object, request.getParameterMap());
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			System.out.print(e.getMessage());
		}
		return object;
	}
}
