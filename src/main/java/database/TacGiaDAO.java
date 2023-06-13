package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.Result;

import model.TacGia;

public class TacGiaDAO implements IDAO<TacGia> {

	ArrayList<TacGia> data = new ArrayList<TacGia>();

//		public ArrayList<TacGia> selectAll(){

	@Override
	public ArrayList<TacGia> selectAll() {
		ArrayList<TacGia> ketQua = new ArrayList<TacGia>();
		try {
			// Bưới 1: tậo kết nối đến cơ sở dữ liệu
			Connection con = JDBCUtil.getConnection();

			// Bưới 2; Tạo ra đối tượng statement
//		 String sql = "SELECT * FROM tagia";
			String sql = "SELECT * FROM tacgia";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3 thực thi cậu lệnh sql
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4
			while (rs.next()) {
				String maTacGia = rs.getString("matacgia");
				String hoVaTen = rs.getString("hovaten");
				Date ngaySinh = rs.getDate("ngaysinh");
				String tieuSu = rs.getString("tieusu");

				TacGia tg = new TacGia(maTacGia, hoVaTen, null, tieuSu);
				ketQua.add(tg);
			}

			// Bước 5
			JDBCUtil.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

// -------------------------------------------------------------------------
	@Override
	public TacGia selectById(TacGia t) {
		TacGia ketQua = null;
		try {
			// Bưới 1: tậo kết nối đến cơ sở dữ liệu
			Connection con = JDBCUtil.getConnection();

			// Bưới 2; Tạo ra đối tượng statement
			String sql = "SELECT * FROM tacgia WHERE matacgia=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaTacGia());
			// Bước 3 thực thi cậu lệnh sql
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4
			while (rs.next()) {
				String maTacGia = rs.getString("matacgia");
				String hoVaTen = rs.getString("hovaten");
				Date ngaySinh = rs.getDate("ngaysinh");
				String tieuSu = rs.getString("tieusu");

				ketQua = new TacGia(maTacGia, hoVaTen, null, tieuSu);
				break;
			}

			// Bước 5
			JDBCUtil.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}
// -------------------------------------------------------------
	@Override
	public int insert(TacGia t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "INSERT INTO tacgia (matacgia, hovaten, ngaysinh, tieusu) " + " VALUES (?,?,?,?)";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaTacGia());
			st.setString(2, t.getHoVaTen());
			st.setDate(3, t.getNgaySinh());
			st.setString(4, t.getTieuSu());

			// Bước 3: thực thi câu lệnh SQL
			ketQua = st.executeUpdate();

			// Bước 4:
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + ketQua + " dòng bị thay đổi!");

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
//-------------------------------------------------------------
	@Override
	public int insertAll(ArrayList<TacGia> arr) {
		int dem = 0;
		for (TacGia tacGia : arr) {
			dem += this.insert(tacGia);
		}
		return dem;
	}
// ----------------------------------------------------------
	@Override
	public int delete(TacGia t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();
			
			// Bước 2: tạo ra đối tượng statement
			String sql = "DELETE from tacgia "+
					 " WHERE matacgia=?";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaTacGia());
			
			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ketQua = st.executeUpdate();
			
			// Bước 4:
			System.out.println("Bạn đã thực thi: "+ sql);
			System.out.println("Có "+ ketQua+" dòng bị thay đổi!");
			
			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ketQua;
	}
// ----------------------------------------------------------------------
	@Override
	public int daleteAll(ArrayList<TacGia> arr) {
		int dem = 0;
		for (TacGia tacGia : arr) {
			dem += this.delete(tacGia);
		}
		return dem;
	}
//--------------------------------------------------------------------------
	@Override
	public int update(TacGia t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();
			
			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE tacgia "+
					 " SET " +
					 " hovaten=?"+
					 ", ngaysinh=?"+
					 ", tieusu=?"+
					 " WHERE matacgia=?";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getHoVaTen());
			st.setDate(2, t.getNgaySinh());
			st.setString(3, t.getTieuSu());
			st.setString(4, t.getMaTacGia());
			// Bước 3: thực thi câu lệnh SQL

			System.out.println(sql);
			ketQua = st.executeUpdate();
			
			// Bước 4:
			System.out.println("Bạn đã thực thi: "+ sql);
			System.out.println("Có "+ ketQua+" dòng bị thay đổi!");
			
			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ketQua;
	}
	

	public static void main(String[] args) {
		TacGiaDAO tgd = new TacGiaDAO();
		ArrayList<TacGia> kq = tgd.selectAll();
		for (TacGia tacGia : kq) {
			System.out.println(tacGia.toString());
		}
		TacGia tg = tgd.selectById(new TacGia("TG1", "", null, ""));
		System.out.println(tg);
		
//		TacGia tg_new = new TacGia("TG10", "Đức Hùng", new Date(101,06,22), "");
//		tgd.insert(tg_new);
//		tgd.delete(tg_new);
		tg.setTieuSu("Tiểu sử đây giờ sẽ là như thế này");
		tgd.update(tg);
		
	}
}
