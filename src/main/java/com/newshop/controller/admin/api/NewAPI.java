package com.newshop.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.newshop.model.NewModel;
import com.newshop.service.INewService;
import com.newshop.utils.HttpUtil;

@WebServlet(urlPatterns = ("/api-admin-new"))
public class NewAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private INewService newService;


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Phương thức để thêm 
		req.setCharacterEncoding("UTF-8"); //định nghĩ kiểu dữ liệu đổ về db có dấu
		resp.setContentType("application/json"); //định nghĩ kiểu dữ liệu để client có thể hiển đc dữ liệu từ db đổ lên
		NewModel newModel = HttpUtil.of(req.getReader()).toModel(NewModel.class);
		// HttpUtil.of để lấy dữ liệu sang json sang chuỗi , sau đó sử dụng hàm toModel để chuyển từ String sang kiểu dữ liệu NewModel
		newModel = newService.save(newModel);
		ObjectMapper mapper = new ObjectMapper(); //tạo ra ObjectMapper để có thể lưu dữ liệu đọc từ Model đổ về
		mapper.writeValue(resp.getOutputStream(), newModel); //wirteValue để chuyển dữ liệu Model về dạng Json đã dịnh nghĩa ở trên (application.json) để gửi về cilent

	}


	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TPhương thức để sửa
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8"); //định nghĩ kiểu dữ liệu đổ về db có dấu
		resp.setContentType("application/json"); //định nghĩ kiểu dữ liệu để client có thể hiển đc dữ liệu từ db đổ lên
		NewModel updateNew = HttpUtil.of(req.getReader()).toModel(NewModel.class);
		updateNew = newService.update(updateNew);
		mapper.writeValue(resp.getOutputStream(), updateNew);
	}




	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8"); //định nghĩ kiểu dữ liệu đổ về db có dấu
		resp.setContentType("application/json"); //định nghĩ kiểu dữ liệu để client có thể hiển đc dữ liệu từ db đổ lên
		NewModel newModel = HttpUtil.of(req.getReader()).toModel(NewModel.class);
		newService.delete(newModel.getIds());
		mapper.writeValue(resp.getOutputStream(), "{}");
	}

}
