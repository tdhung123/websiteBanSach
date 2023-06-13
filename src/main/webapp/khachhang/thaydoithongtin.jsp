<%@page import="model.KhachHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Form đăng ký</title>
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
<style type="text/css">
	.red{
		color: red;	
	}

</style>
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
				<%
				String baoLoi = request.getAttribute("baoLoi")+"";
				baoLoi =(baoLoi.equals("null"))?"":baoLoi;
				

			
			
			String hoVaTen =	khachHang.getHoVaTen();
			
			
			String gioiTinh =	khachHang.getHoVaTen();
			
			
			String ngaySinh =	khachHang.getNgaySinh().toString();
			
			
			String diaChiKhachHang =	khachHang.getDiaChi();
			
			
			String diaChiMuaHang =	khachHang.getDiaChiMuaHang();
			
			
			String diaChiNhanHang =	khachHang.getDiaChiNhanHang();
			
			
			String soDienThoai =	khachHang.getSoDienThoai();
			
			
			String email =	khachHang.getEmail();
			
			
			Boolean dongYNhanEmail =	khachHang.isDangKyNhanBangTin();
			%>
	<form action="../khachhang" method="post">
	<input type="hidden" name="hanhDong" value="thaydoithongtin">
		<div class="container">
			<div class="row">
						<h3 class="text-center " style="background: aqua; padding:10px 0  " >Thay đổi thông tin</h3>
			<div class="col-xl-6">
			

			<div class="mb-3">
				<h4>Thông tin khách hàng</h4>
				<label for="hoVaTen" class="form-label">Họ và tên</label> <span class="red">*</span>
				<input type="text" class="form-control"id="hoVaTen" name="hoVaTen" placeholder="Nhập họ và tên của bạn" value="<%=hoVaTen %>">
			</div>
			<div class="mb-3">
				<label for="gioiTinh" class="form-label">Danh xưng</label> <span class="red">*</span> 	
				<select class="form-control" id="gioiTinh" name="gioiTinh">
						<option></option>
						<option value="ông" <%=(gioiTinh.equals("ông"))?"selected='selected'":"" %>>Ông</option>
						<option value="bà" <%=(gioiTinh.equals("bà"))?"selected='selected'":"" %>>Bà</option>
						<option value="anh" <%=(gioiTinh.equals("anh"))?"selected='selected'":"" %>>anh</option>
						<option value="chị" <%=(gioiTinh.equals("chị"))?"selected='selected'":"" %>>chị</option>
						
				</select>
			</div>
			<div class="mb-3">
				<label for="ngaySinh" class="form-label">Ngày Sinh</label> 
				<input type="date" class="form-control"id="ngaySinh" name="ngaySinh" value="<%=ngaySinh %>" >
			</div>
			</div>
			<div class="col-xl-6">
							<h4>Địa chỉ</h4>
			<div class="mb-3">
				<label for="diaChiKhachHang" class="form-label">Địa chỉ khách hàng</label> 
				<input type="text" class="form-control"id="diaChiKhachHang" name="diaChiKhachHang" value="<%=diaChiKhachHang %>" >
			</div>
			<div class="mb-3">
				<label for="diaChiMuaHang" class="form-label">Địa chỉ mua hàng</label> 
				<input type="text" class="form-control"id="diaChiMuaHang" name="diaChiMuaHang" value="<%=diaChiMuaHang %>">
			</div>
			<div class="mb-3">
				<label for="diaChiNhanHang" class="form-label">Địa chỉ nhận hàng</label> 
				<input type="text" class="form-control"id="diaChiNhanHang" name="diaChiNhanHang" value="<%=diaChiNhanHang %>" >
			</div>
			<div class="mb-3">
				<label for="soDienThoai" class="form-label">Số điện thoại</label> 
				<input type="tel" class="form-control"id="soDienThoai" name="soDienThoai" value="<%=soDienThoai %>" >
			</div>
			<div class="mb-3">
				<label for="email" class="form-label">Email</label> 
				<input type="email" class="form-control"id="email" name="email" value="<%=email %>" >
			</div>
			</div>

			<hr>

			<div class="mb-3">
				<label for="dongYNhanEmail" class="form-label">Đồng ý nhận email</label> 
				<input type="checkbox" class="form-check-input"id="dongYNhanEmail" name="dongYNhanEmail" >
			</div>
			<input class="btn btn-primary form-control" type="submit" value="Đăng ký"
				name="submit"  id="submit" >
			</div>
		</div>		
	</form>
	<%} %>
</body>
</html>