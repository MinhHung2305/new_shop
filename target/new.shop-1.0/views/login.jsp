<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
</head>
<body>
	<div class="container">
		<div class="login-form"> 
		<!-- boottrap css class "alert alert-success" là thành công , "alert alert-success" là thất bại
				nên nếu thành công alert rẽ là success , thất bại sẽ là danger , và in ra mess tương ứng 
				alert và mess sẽ được truyền qua url từ web/HomeController -->
			
			<div class="main-div">
			<c:if test="${not empty message}">
				<div class="alert alert-${alert}">
					${message}
				</div>
			</c:if>
			  
				<form action="<c:url value='/dang-nhap'/>" id="formLogin"
					method="POST">
					<div class="form-group">
						<!-- name của các input phải trùng với tên các param trong Model -->
						<input type="text" class="form-control" id="userName"
							name="userName" placeholder="Tên đăng nhập">
					</div>

					<div class="form-group">
						<input type="password" class="form-control" id="password"
							name="password" placeholder="Mật khẩu">
					</div>
					<!-- 				khai báo 1 input (name = action) chứa dữ liệu của hành động với value=login , trả về bên controller để bên controller kiểm tra-->
					<input type="hidden" value="login" name="action" />

					<!-- button submit khi click vào button đăng nhập sẽ submit form và đi đến url(/dang-nhap) và vào phương thức post    -->
					<button type="submit" class="btn btn-primary">Đăng nhập</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>