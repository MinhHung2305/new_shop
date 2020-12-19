package com.newshop.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newshop.constant.SystemConstant;
import com.newshop.model.NewModel;
import com.newshop.paging.PageRequest;
import com.newshop.service.INewService;
import com.newshop.sort.Sorter;
import com.newshop.utils.FormUtil;

@WebServlet(urlPatterns = {"/admin-new" })
public class NewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private INewService newService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		NewModel new_Model = new NewModel(); // khởi tạo 1 biến model để chứa dữ liệu từ db
//		String pageStr = req.getParameter("page"); // lấy giá trị của param từ bên jsp ,
//		String maxPageItemStr = req.getParameter("maxPageItem");
//		if(pageStr != null) {
//			new_Model.setPage(Integer.parseInt(pageStr));
//		}else {
//			new_Model.setPage(1);
//		}
//		if(maxPageItemStr != null) {
//			new_Model.setMaxPageItem(Integer.parseInt(maxPageItemStr));
//		}
//		
		NewModel new_Model = FormUtil.toModel(NewModel.class, req);// gọi đến FormUtil để map các key value từ string sang model
		//ko thực hiện logic trong controller nên chuyển sang class riêng
//		Integer offset = ((new_Model.getPage() -1) * new_Model.getMaxPageItem()); // offset là số thứ tự phần tử đầu tiên được bắt đầu in ra trong sql findAll
		
		// lấy ra số thứ tự phần tử bắt đầu được select ra trong 1 trang là offset  
		
		PageRequest pageRequest = new PageRequest(new_Model.getPage(), new_Model.getMaxPageItem(), 
							new Sorter(new_Model.getSortName(), new_Model.getSortBy()));
		// offset được tính ở trên = (số trang hiện tại) nhân với (số item trong 1 trang)
		
		// lấy dữ liệu từ db đổ vào model = cách sử dụng hàm findAll trong service
		// getmaxPageItem đc lấy ở ra ở trên = là số item hiển thị trong 1 trang , đc truyền vào mặc định từ jsp or từ url
		//new_Model.setListResult(newService.findAll(offset,new_Model.getMaxPageItem(),new_Model.getSortName() , new_Model.getSortBy())); // các param được thêm sẽ lấy từ model(được truyền vào từ url - menu.jsp)
		new_Model.setListResult(newService.findAll(pageRequest));// thay vì truyền từng tham số thì findAll giờ chỉ cần truyền pageResult(chứa các tham số đã được set từ ở trên) 
		new_Model.setTotalItem(newService.getTotalItem()); // 1lấy dữ liệu từ sql , số hàng trong table news
		// tính tổng số page = (tổng số item trong db) chia cho (số item trong 1 page)
		// sử dụng hàm Math.ceil để làm tròn số lên và gán kiểu số nguyên
		new_Model.setTotalPage((int) Math.ceil((double)new_Model.getTotalItem()/new_Model.getMaxPageItem())); 
		// set giá trị hàm model vừa lấy được vào 1 Attribute là 1 string , SystemConstant.MODEL là 1 biến tĩnh kiểu String
		req.setAttribute(SystemConstant.MODEL, new_Model); 
		
		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/new/list.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}
