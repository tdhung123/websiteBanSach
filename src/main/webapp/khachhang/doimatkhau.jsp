<%@page import="model.KhachHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
	integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
	integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz"
	crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
	<%
	Object obj = session.getAttribute("khachHang");
	KhachHang khachHang = null;
	if (obj != null) {
		khachHang = (KhachHang) obj;
	}
	if (khachHang == null) {
	%>
	<h1>Bận phải đăng nhập tài khoản mới có thể đổi mật khẩu</h1>
	<%
	} else {
	%>
	<main>
		<%
		String baoLoi = request.getAttribute("baoLoi") + "";
		if (baoLoi.equals("null")) {
			baoLoi = "";
		}
		%>
		<form action="../khachhang" method="post">
		<input type="hidden" name="hanhDong" value="doimatkhau">
			<h1>Đổi mật khẩu</h1>
			<span> <%=baoLoi%>
			</span>
			<div class="mb-3">
				<label for="matKhauHienTai" class="form-label">Mật khẩu hiện
					tại</label> <input type="password" class="form-control" id="matKhauHienTai"
					name="matKhauHienTai">
			</div>
			<div class="mb-3">
				<label for="matKhauMoi" class="form-label">Mật khẩu mới</label> <input
					type="password" class="form-control" id="matKhauMoi"
					name="matKhauMoi">
			</div>
			<div class="mb-3">
				<label for="matKhauMoiNhapLai" class="form-label">Mật khẩu
					nhập lại</label> <input type="password" class="form-control"
					id="matKhauMoiNhapLai" name="matKhauMoiNhapLai">
			</div>

			<button type="submit" class="btn btn-primary">Lưu mật khẩu</button>
		</form>

	</main>
	<%
	}
	%>




</body>
</html>