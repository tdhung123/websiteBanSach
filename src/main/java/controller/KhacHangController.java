package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.KhachHangDAO;
import model.KhachHang;
import utl.MaHoa;

/**
 * Servlet implementation class KhacHangController
 */
@WebServlet("/khachhang")
public class KhacHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KhacHangController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String hanhDong = request.getParameter("hanhDong");
		if(hanhDong.equals("dangky")) {
			dangKy(request, response);
		}else if(hanhDong.equals("dangxuat")) {
			dangXuat(request, response);
		}else if (hanhDong.equals("dangnhap")) {
			dangNhap(request, response);
		}else if (hanhDong.equals("doimatkhau")) {
			doiMatKhau(request, response);
		}else if (hanhDong.equals("thaydoithongtin")) {
			thayDoiThongTin(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
	//-----Đăng ký
	private void dangKy(HttpServletRequest request, HttpServletResponse response)  {
		try {
			// Gọi các yêu cầu từ người dùng khi đăng nhập
			String tenDangNhap = request.getParameter("tenDangNhap");
			String matKhau = request.getParameter("matKhau");
			String nhapLaiMatKhau = request.getParameter("nhapLaiMatKhau");
			String hoVaTen = request.getParameter("hoVaTen");
			String gioiTinh = request.getParameter("gioiTinh");
			String ngaySinh = request.getParameter("ngaySinh");
			String diaChiKhachHang = request.getParameter("diaChiKhachHang");
			String diaChiMuaHang = request.getParameter("diaChiMuaHang");
			String diaChiNhanHang = request.getParameter("diaChiNhanHang");
			String soDienThoai = request.getParameter("soDienThoai");
			String email = request.getParameter("email");
			String dongYDieuKhoan = request.getParameter("dongYDieuKhoan");
			String dongYNhanEmail = request.getParameter("dongYNhanEmail");
			
			// -----------------------------------------
			// Nhận các thông tin người dùng
			
//	request.setAttribute("matKhau",matKhau);
			request.setAttribute("tenDangNhap",tenDangNhap);
			request.setAttribute("hoVaTen",hoVaTen);
			request.setAttribute("gioiTinh",gioiTinh);
			request.setAttribute("ngaySinh",ngaySinh);
			request.setAttribute("diaChiKhachHang",diaChiKhachHang);
			request.setAttribute("diaChiMuaHang",diaChiMuaHang);
			request.setAttribute("diaChiNhanHang",diaChiNhanHang);
			request.setAttribute("soDienThoai",soDienThoai);
			request.setAttribute("email",email);
			request.setAttribute("dongYNhanEmail",dongYNhanEmail);
			

			//-----------------------------------
			String url="";
			String baoLoi="";
			boolean dongYNhanEmail1 = Boolean.parseBoolean(request.getParameter("dongYNhanEmail"));
			//---------------------------------
// Bắt lỗi tên đăng nhập
			KhachHangDAO khachHangDao = new KhachHangDAO();
			if(khachHangDao.kiemTraDangNhap(tenDangNhap)){
				baoLoi+="Tên đăng nhập đã tồn tại, vui lòng nhập tên đăng nhập khác</br>";
			}
			if (matKhau != null && !matKhau.equals(nhapLaiMatKhau)) {
			    baoLoi += "Mật khẩu không khớp</br>";
			}else {
				matKhau = MaHoa.toSHA1(matKhau);
			}

			request.setAttribute("baoLoi",baoLoi);
			if(baoLoi.length()>0) {
				url = "/khachhang/dangky.jsp";
			}else {
				Random rd = new Random();
				String maKhachHang = System.currentTimeMillis()+rd.nextInt(1000)+"";
				
				java.sql.Date ngaySinhDate = null;
				try {
				    ngaySinhDate = Date.valueOf(ngaySinh);
				} catch (IllegalArgumentException e) {
				    // Xử lý lỗi khi ngaySinh không hợp lệ
				    // Ví dụ: gán ngaySinhDate một giá trị mặc định hoặc hiển thị thông báo lỗi
				}

				KhachHang kh = new KhachHang(maKhachHang, tenDangNhap, matKhau, hoVaTen, gioiTinh, diaChiKhachHang, diaChiNhanHang, diaChiMuaHang, ngaySinhDate, soDienThoai, email, dongYNhanEmail1);
				khachHangDao.insert(kh);
				url="/khachhang/thanhcong.jsp";
			}
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//----- Đăng xuất
private void dangXuat(HttpServletRequest request, HttpServletResponse response) {
		try {
			String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect(url+"/index.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//---- Đăng nhập
private void dangNhap(HttpServletRequest request, HttpServletResponse response) {
	try {
		// Sử lý trong doPost
		String tenDangNhap = request.getParameter("tenDangNhap");
		String matKhau = request.getParameter("matKhau");
		matKhau = MaHoa.toSHA1(matKhau);
		// Tìm ra 1 ông khách hàng
		KhachHang kh = new KhachHang();
		kh.setTenDangNhap(tenDangNhap);
		kh.setMatKhau(matKhau);
		// Kiểm tra
		KhachHangDAO khd = new KhachHangDAO();
		KhachHang khachHang = khd.selectByUseAndPassWord(kh);
		String url;
		
		


		if(khachHang != null) {
			// Tạo ra 1 phiên làm viên
			
			  HttpSession session = request.getSession();
			session.setAttribute("khachHang", khachHang);
			url ="/index.jsp";
		}else {
			request.setAttribute("baoLoi", "Tên đăng nhập hoặc mật khẩu không đúng");
			url ="/khachhang/dangnhap.jsp";
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
}
//--- đổi mật khẩu
private void doiMatKhau(HttpServletRequest request, HttpServletResponse response) {
	try {
		String matKhauHienTai = request.getParameter("matKhauHienTai");
		String matKhauMoi = request.getParameter("matKhauMoi");
		String matKhauMoiNhapLai = request.getParameter("matKhauMoiNhapLai");
		
		String matKhauHienTai_Sha1 = MaHoa.toSHA1(matKhauHienTai);
		
		String baoLoi = "";
		String url = "/khachhang/doimatkhau.jsp";
		
		// Kiem tra mat khau cu co giong hay khong
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("khachHang");
		KhachHang khachHang = null;
		if (obj!=null)
			khachHang = (KhachHang)obj;		
		if(khachHang==null) {
			baoLoi = "Bạn chưa đăng nhập vào hệ thống!";
		}else {
			// Neu khach hang da dang nhap
			if(!matKhauHienTai_Sha1.equals(khachHang.getMatKhau())){
				baoLoi = "Mật khẩu hiện tại không chính xác!";
			}else {
				if(!matKhauMoi.equals(matKhauMoiNhapLai)) {
					baoLoi = "Mật khẩu nhập lại không khớp!";
				}else {
					String matKhauMoi_Sha1 = MaHoa.toSHA1(matKhauMoi);
					khachHang.setMatKhau(matKhauMoi_Sha1);
					KhachHangDAO khd = new KhachHangDAO();
					if(khd.capNhatMatKhau(khachHang)) {
						baoLoi = "Mật khẩu đã thay đổi thành công!";

					}else {
						baoLoi = "Quá trình thay đổi mật khẩu không thành công!";
					}
				}
			}
		}
		
		request.setAttribute("baoLoi", baoLoi);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
// ---thay đổi thông tin 
private void thayDoiThongTin (HttpServletRequest request, HttpServletResponse response) {

	try {
		// Gọi các yêu cầu từ người dùng khi đăng nhập
		String hoVaTen = request.getParameter("hoVaTen");
		String gioiTinh = request.getParameter("gioiTinh");
		String ngaySinh = request.getParameter("ngaySinh");
		String diaChiKhachHang = request.getParameter("diaChiKhachHang");
		String diaChiMuaHang = request.getParameter("diaChiMuaHang");
		String diaChiNhanHang = request.getParameter("diaChiNhanHang");
		String soDienThoai = request.getParameter("soDienThoai");
		String email = request.getParameter("email");
		String dongYDieuKhoan = request.getParameter("dongYDieuKhoan");
		String dongYNhanEmail = request.getParameter("dongYNhanEmail");
		
		// -----------------------------------------
		// Nhận các thông tin người dùng
		
//	request.setAttribute("matKhau",matKhau);

		request.setAttribute("hoVaTen",hoVaTen);
		request.setAttribute("gioiTinh",gioiTinh);
		request.setAttribute("ngaySinh",ngaySinh);
		request.setAttribute("diaChiKhachHang",diaChiKhachHang);
		request.setAttribute("diaChiMuaHang",diaChiMuaHang);
		request.setAttribute("diaChiNhanHang",diaChiNhanHang);
		request.setAttribute("soDienThoai",soDienThoai);
		request.setAttribute("email",email);
		request.setAttribute("dongYNhanEmail",dongYNhanEmail);
		

		//-----------------------------------
		String url="";
		String baoLoi="";
		boolean dongYNhanEmail1 = Boolean.parseBoolean(request.getParameter("dongYNhanEmail"));
		//---------------------------------
// Bắt lỗi tên đăng nhập
		KhachHangDAO khachHangDao = new KhachHangDAO();

		request.setAttribute("baoLoi", baoLoi);
		if (baoLoi.length() > 0) {
		    url = "/khachhang/dangky.jsp";
		} else {
		    Object obj = request.getSession().getAttribute("khachHang");
		    KhachHang khachHang = null;
		    if (obj != null) {
		        khachHang = (KhachHang) obj;
		        if (khachHang != null) {
		            String maKhachHang = khachHang.getMaKhacHang();
		            java.sql.Date ngaySinhDate = null;
		            
		            try {
		                ngaySinhDate = java.sql.Date.valueOf(ngaySinh);
		            } catch (IllegalArgumentException e) {
		                // Xử lý lỗi khi ngaySinh không hợp lệ
		                // Ví dụ: gán ngaySinhDate một giá trị mặc định hoặc hiển thị thông báo lỗi
		            }

		            KhachHang kh = new KhachHang(maKhachHang, "", "", hoVaTen, gioiTinh, diaChiKhachHang, diaChiNhanHang, diaChiMuaHang, ngaySinhDate, soDienThoai, email, dongYNhanEmail1);
		            khachHangDao.updateThongTin(kh);
		            KhachHang kh2 = khachHangDao.selectById(kh);
		            request.getSession().setAttribute("khachHang", kh2);
		            url = "/khachhang/thanhcong.jsp";
		        }
		    }
		}

		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
	
