package com.newshop.utils;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil {
	//class này được tạo ra để chuyển dữ liệu qua lại từ cilent và db ở dạng json

	private String value;

	public HttpUtil (String value) {
		this.value = value;
	}
	public <T> T toModel(Class<T> tClass){  
		// hàm này chuyển từ chuổi từ string ( lấy ở hàm of() có dạng json(client) -> string ) sang Model(<T>)
		try {
			return new ObjectMapper().readValue(value, tClass);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	// hàm này để chuyển từ json( bên client ) sang string
	public static HttpUtil of(BufferedReader reader) { 
		StringBuilder sb = new StringBuilder();
		String line ;
		try {
			while((line = reader.readLine()) != null) {
				sb.append(line); 
			}	
		} catch (IOException e) {
			System.out.print(e.getMessage());
		}
		// trả về kiểu dữ liệu kiểu là contructor class HttpUtil truyền vào string
		return new HttpUtil(sb.toString());	
	} 
}

