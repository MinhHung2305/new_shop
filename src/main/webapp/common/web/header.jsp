<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Navigation -->
<%@ include file="/common/taglib.jsp"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">
		<a class="navbar-brand" href="#">Start Bootstrap</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link" href="#">Trang chủ
						<span class="sr-only">(current)</span>
				</a>
				</li> 
				
				<!-- kiểm tra xem USERMODEL có null không ,từ bên HomeController trả về , 
				nếu không null thì get ra fullName --> 
				<c:if test="${not empty USERMODEL}" >
					<li class="nav-item">
						<a class="nav-link" href="#">Wellcome , ${USERMODEL.fullName}</a>
					</li>
					<li class="nav-item">
					
					<!-- gọi đến path là /thoat với param action=logout -->
					<a class="nav-link" href='<c:url value="/thoat?action=logout"/>'>Thoát</a>
				</li>
				</c:if>
				<c:if test="${empty USERMODEL}" >
				<li class="nav-item">
				<!-- gọi đến path là /dang-nhap với param action=login -->
					<a class="nav-link" href='<c:url value="/dang-nhap?action=login"/>'>Đăng nhập</a>
				</li>
				</c:if>
			</ul>
		</div>
	</div>
</nav>