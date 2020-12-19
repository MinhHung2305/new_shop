package com.newshop.controller.web;


import java.io.IOException;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newshop.model.UserModel;
import com.newshop.service.ICategorySevice;
import com.newshop.service.IUserService;
import com.newshop.utils.FormUtil;
import com.newshop.utils.SessionUtil;

@WebServlet(urlPatterns = { "/trang-chu","/dang-nhap","/thoat"})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private ICategorySevice categorySevice; // inject nhúng dữ liệu từ bên Service

	@Inject
	private IUserService userService;
	
	// gọi đến mess.properties
	ResourceBundle rescBundle = ResourceBundle.getBundle("mess");
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// method doGet lấy dữ liệu từ bên jsp
		String action = req.getParameter("action");
		if(action != null && action.equals("login")) {
			// đăng nhập thành công lấy ra các các param message và alert
			String mess = req.getParameter("message"); // lấy ra dữ liệu param message bên jsp (ở đây là "fail") 
			String alert = req.getParameter("alert"); //alert sẽ được lấy ra ( ở đây là "danger")
			if(mess != null && alert != null) { 
				req.setAttribute("message",rescBundle.getString(mess)); //set mess bên jsp = fail ( trong mess.properties)
				req.setAttribute("alert", alert); //set alert bên jsp = chính alert được get ra
				
			}
			RequestDispatcher rd = req.getRequestDispatcher("/views/login.jsp");
			rd.forward(req, resp);
		}else if(action != null && action.equals("logout")){
			//thoát ra khỏi hệ thống thì sẽ gọi đến hàm removeValue và truyền vào key cần remove vd là USERMODEL-được set ở phần doPost
			SessionUtil.getInstance().removeValue(req, "USERMODEL");
			// khi click vào logout thì sẽ chuyển trang đến url /trang-chu 
			// sử dụng sendRedirect để chuyển hướng , getContextPath là lấy đường dẫn ban đầu vd: http://localhost:8080/new.shop/..
			resp.sendRedirect(req.getContextPath()+"/trang-chu"); // cộng thêm chuỗi trang chủ : http://localhost:8080/new.shop/trang-chu
		}else {
			req.setAttribute("categories", categorySevice.findAll()); // request( yêu cầu ) dữ liệu từ bên service trong hàm findAll() và đổ vào biến tên là categories 
			RequestDispatcher rd = req.getRequestDispatcher("/views/web/home.jsp");
			rd.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if(action != null && action.equals("login")){
			// lấy dữ liệu vào model , sử dụng formUtil để lấy dữ liệu từ bên form đổ vào Model
			UserModel model = FormUtil.toModel(UserModel.class, req);
			model = userService.findByUserNameAndPasswordAndStatus(model.getUserName(),model.getPassword(), 1);
			if(model != null) { 
				SessionUtil.getInstance().putValue(req,"USERMODEL", model);
				if(model.getRole().getCode().equals("USER")) {
					//sử  dụng getContextPath để lấy đường dẫn phần đầu vd:  http://localhost:8080/new.shop/..
					resp.sendRedirect(req.getContextPath()+"/trang-chu");
				}else if (model.getRole().getCode().equals("ADMIN")) {
					resp.sendRedirect(req.getContextPath()+"/admin-home");
				}
				}
				else {
					// trả về lại trang đăng nhập truyền vào 2 param mess và alert
					// mess truyền vào là "fail" và alert = danger để thay đổi css bên login.jsp 
					resp.sendRedirect(req.getContextPath()+"/dang-nhap?action=login&message=mess_login_fail&alert=danger");
				}
			}
		}
	}
