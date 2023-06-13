package utl;

import java.security.MessageDigest;

import org.apache.tomcat.util.codec.binary.Base64;




public class MaHoa {
	//mã hoá md5
	// mã hoá sha-1
	public static String toSHA1 (String str) {
		//Chuỗi ngẫu nhiên cộng mật khẩu cho phức tạp hơn
		
		//Từ mật khẩu của khách hàng thêm đuôi cho mật khẩu
		// --> để làm phức tạp hơn ko giản mã được
		String salt = "poiuyetqlajdgxm;'.zgdlaoqhcnskamxj";
		String result =null;
		str = str+salt;
		try {
			byte[] dataBytes =str.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			result= Base64.encodeBase64String(md.digest(dataBytes));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	public static void main(String[] args) {
		System.out.println(toSHA1("123"));
	}
}
