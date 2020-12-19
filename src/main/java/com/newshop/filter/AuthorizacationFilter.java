package com.newshop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newshop.constant.SystemConstant;
import com.newshop.model.UserModel;
import com.newshop.utils.SessionUtil;

public class AuthorizacationFilter implements Filter {
 // để kiểm tra và lọc url
	private ServletContext context;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		// Lấy ra url đang sử dụng or request tới
		String url = request.getRequestURI();
		// Lấy cái url cần filter url chứ /admin sẽ bị lọc
		if(url.contains("/admin")) {
			// lấy ra cái usermodel được truyền vào sessitonUtil có tên là "USERMODEL"
			UserModel model = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
			if(model != null) {
				//Những userModel nào có Code == ADMIN mới được truy cập
				if(model.getRole().getCode().equals(SystemConstant.ADMIN)) {
					chain.doFilter(request, response);
				}else { //không sẽ redirect lại trang đăng nhập vs thông báo not_permission
					response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=not_permission&alert=danger");
				}
			}else {
				//nếu Model null thì sẽ thông báo chưa đăng nhập
				response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=not_login&alert=danger");
			}
		}else {
			// những trang không cần bảo mật( filter) thì sẽ cho nó đi thoải mái
			chain.doFilter(request, response);
			
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
