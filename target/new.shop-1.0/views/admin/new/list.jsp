<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách bài viết</title>
</head>
<body>
	<div class="main-content">
		<form action='<c:url value="/admin-new"/>' id='formSubmit'
			method="get">
			<div class="main-content-inner">
				<div class="breadcrumbs" id="breadcrumbs">
					<ul class="breadcrumb">
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Trang
								chủ</a></li>
					</ul>
				</div>
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<div class="table-responsive">

								<table class="table table-bordered">
									<thead>
										<tr>
											<th>Tên bài viết</th>
											<th>Mô tả ngắn</th>
										</tr>
									</thead>
									<tbody>
										<!-- sử dụng forEach để in ra các model từ 1 listResult(ListModel-trong AbstractModel) 
									 var="item": là từng model con của mỗi vòng forEach trong 1 listResult(listModel),
									 model.listResult : model lấy từ bên Controller được setAttribute vào biến tĩnh SystemConstant.MODEL(là "model")  -->
										<c:forEach var="item" items="${model.listResult}">
											<tr>
												<td>${item.title}</td>
												<!-- gọi ra từng thuộc tính trong model con lấy ra từ listResult -->
												<td>${item.shortDescription}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<!-- tạo ra 1 input chứa value page là page muốn tới, nhưng ẩn đi ( hidden) -->
								<input type="hidden" value="" id="page" name="page">  <!-- các model sẽ nhận dữ liệu thông qua phần name -->
								<input type="hidden" value="" id="maxPageItem" name="maxPageItem"> <!-- sử dụng lớp FormUtil để lấy dữ liệu từ đây đổ về model-->
								<input type="hidden" value="" id="sortName" name="sortName">  <!-- nên phải đặt tên các name trùng vs tên attr trong model -->
								<input type="hidden" value="" id="sortBy" name="sortBy">
								<ul class="pagination" id="pagination"></ul>
								<!-- hiển thị ra thanh phân trang -->

							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		var totalPage = ${model.totalPage}; // tổng số page sẽ được lấy từ bên model
		var currentPage = ${model.page}; // là trang hiện tại , currentPage = page bắt đầu (gọi từ Model) 
		var limit = 2; // limit là số item trong 1 trang , gán cứng = 2 có thể thay đổi trên url
		$(function() {
			window.pagObj = $('#pagination').twbsPagination({
				totalPages : totalPage, //tổng số page
				visiblePages : 10, // số page hiển thị lên thanh phân trang
				startPage : currentPage, // page bắt đầu khi vào trang 
					onPageClick : function(event, page) {
						if ( currentPage != page){  // nếu currentPage = Page tức trang hiện tại đúng bằng trang muốn tới thì sẽ không cho submit
						$('#maxPageItem').val(limit); // gán giá trị tổng số item(bài viết ) trên 1 trang
						$('#page').val(page); //gán giá trị của cái page muốn tới vào trong biến id='page' - khai báo ở trên
						$('#sortName').val('title'); //hàn code ở đây cũng được chứ ko nên làm trong phần model(back-end)
						$('#sortBy').val('desc');
						$('#formSubmit').submit(); // sử dụng để submit form có id="formSubmit"
					}
				}
			});
		});
	</script>
</body>
</html>