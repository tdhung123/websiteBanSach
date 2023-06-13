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
				String baoLoi = request.getAttribute("baoLoi")+"";
				baoLoi =(baoLoi.equals("null"))?"":baoLoi;
				
			String tenDangNhap =	request.getAttribute("tenDangNhap")+"";
			tenDangNhap =(tenDangNhap.equals("null"))?"":tenDangNhap;
			
			
			String hoVaTen =	request.getAttribute("hoVaTen")+"";
			hoVaTen =(hoVaTen.equals("null"))?"":hoVaTen;
			
			
			String gioiTinh =	request.getAttribute("gioiTinh")+"";
			gioiTinh =(gioiTinh.equals("null"))?"":gioiTinh;
			
			
			String ngaySinh =	request.getAttribute("ngaySinh")+"";
			ngaySinh =(ngaySinh.equals("null"))?"":ngaySinh;
			
			
			String diaChiKhachHang =	request.getAttribute("diaChiKhachHang")+"";
			diaChiKhachHang =(diaChiKhachHang.equals("null"))?"":diaChiKhachHang;
			
			
			String diaChiMuaHang =	request.getAttribute("diaChiMuaHang")+"";
			diaChiMuaHang =(diaChiMuaHang.equals("null"))?"":diaChiMuaHang;
			
			
			String diaChiNhanHang =	request.getAttribute("diaChiNhanHang")+"";
			diaChiNhanHang =(diaChiNhanHang.equals("null"))?"":diaChiNhanHang;
			
			
			String soDienThoai =	request.getAttribute("soDienThoai")+"";
			soDienThoai =(soDienThoai.equals("null"))?"":soDienThoai;
			
			
			String email =	request.getAttribute("email")+"";
			email =(email.equals("null"))?"":email;
			
			
			String dongYNhanEmail =	request.getAttribute("dongYNhanEmail")+"";
			dongYNhanEmail =(dongYNhanEmail.equals("null"))?"":dongYNhanEmail;
			%>
	<form action="../khachhang" method="post">
		<input type="hidden" name="hanhDong" value="dangky">
		<div class="container">
			<div class="row">
						<h3 class="text-center " style="background: aqua; padding:10px 0  " >Đăng ký tài khoản</h3>
			<div class="col-xl-6">
			
				<div class="mb-3">
				<h4>Tài khoản</h4>
				<div class="red" id="baoLoi">
					<%= baoLoi %>
				</div>
				<label for="tenDangNhap" class="form-label">Tên đăng nhập</label>  <span class="red">*</span> 
				<input type="text" class="form-control"id="tenDangNhap" name="tenDangNhap" placeholder="Tên đăng nhập" value="<%=tenDangNhap %>">
		</div>
		<div class="mb-3">
				<label for="matKhau" class="form-label">Mật khẩu</label> <span class="red">*</span>
				<input type="password" class="form-control"id="matKhau" name="matKhau" placeholder="Mật khẩu" onkeyup="kiemTraDangKy()">
		</div>
		<div class="mb-3">
				<label for="nhapLaiMatKhau" class="form-label">Nhập lại mật khẩu</label> <span id="msg" class="red">*</span>
				<input type="password" class="form-control"id="nhapLaiMatKhau" name="nhapLaiMatKhau" placeholder="Nhập lại mật khẩu của bạn" onkeyup="kiemTraDangKy()">
		</div>
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
				<label for="dongYDieuKhoan" class="form-label">Đồng ý với điều khoản mua hàng</label> <span class="red">*</span>
				<input type="checkbox" class="form-check-input"id="dongYDieuKhoan" name="dongYDieuKhoan" onchange="xuLyChonDongY()">
			</div>
			<div class="mb-3">
				<label for="dongYNhanEmail" class="form-label">Đồng ý nhận email</label> 
				<input type="checkbox" class="form-check-input"id="dongYNhanEmail" name="dongYNhanEmail" >
			</div>
			<input class="btn btn-primary form-control" type="submit" value="Đăng ký"
				name="submit"  id="submit" style="visibility: hidden;">
			</div>
		</div>		
	</form>
	
	
	
	
	
	<script type="text/javascript">
	
	function kiemTraDangKy() {
		  let matKhau = document.getElementById("matKhau").value;
		  let nhapLaiMatKhau = document.getElementById("nhapLaiMatKhau").value;
		  let msgElement = document.getElementById("msg");

		  if (matKhau !== nhapLaiMatKhau) {
		    msgElement.innerHTML = "Nhập lại mật khẩu";
		    msgElement.style.color = "red";
		  } else if (matKhau === nhapLaiMatKhau && matKhau !== "" && nhapLaiMatKhau !== "") {
		    msgElement.innerHTML = "Mật khẩu đã khớp. Hãy nhớ mật khẩu của mình nhé!";
		    msgElement.style.color = "#57C5B6";
		  } else {
		    msgElement.innerHTML = "*";
		    msgElement.style.color = "red";
		  }
		}
	
	
	
	
	function xuLyChonDongY() {
		   dongYDieuKhoan = document.getElementById("dongYDieuKhoan");
		  

		  if (dongYDieuKhoan.checked == true) {
		 //   submitButton.style.opacity = "1";
		  //  submitButton.style.cursor = "pointer";
			  document.getElementById("submit").style.visibility= "visible";
		  } else {
		  //  submitButton.style.opacity = "0.7";
		   // submitButton.style.cursor = "default";
			  document.getElementById("submit").style.visibility= "hidden";
		  }
		}
	
	</script>
</body>
</html>