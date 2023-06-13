package utl;

import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.DataHandler;
import javax.activation.DataSource;

public class email1 {
	static final String from = "ngoc146789@gmail.com";
	static final String password = "vdblvfocejkqajxa";
	private static Session session;
	private static Transport transport;
	private static boolean initialized = false;

	static {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		 };

		session = Session.getInstance(props, auth);

		try {
			transport = session.getTransport("smtp");
			transport.connect();
			initialized = true;
			System.out.println("Khởi tạo thành công");
		} catch (Exception e) {
			System.out.println("Gặp lỗi trong quá trình khởi tạo");
			e.printStackTrace();
		}
	}

	public static boolean sendEmail(String to, String tieuDe, String noiDung) {
		MimeMessage msg = new MimeMessage(session);

		try {
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.setFrom(from);
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			msg.setSubject(tieuDe);
			msg.setSentDate(new Date());
			msg.setContent(noiDung, "text/HTML; charset=UTF-8");

			transport.sendMessage(msg, msg.getAllRecipients());

			System.out.println("Gửi email thành công");
			return true;
		} catch (Exception e) {
			System.out.println("Gặp lỗi trong quá trình gửi email");
			e.printStackTrace();
			return false;
		}
	}

	public static void close() {
		try {
			if (transport != null) {
				transport.close();
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	private static boolean isInitialized() {
	    return initialized;
	}

	public static void main(String[] args) {
		String to = "tranduchach2001.com";
		String subject = "Test Email";
		String content = "This is a test email.";

		// Kiểm tra xem khối static initializer đã được thực thi thành công hay không
		if (email1.isInitialized()) {
			// Gửi email
			boolean sent = email1.sendEmail(to, subject, content);
			if (sent) {
				System.out.println("Email đã được gửi thành công.");
			} else {
				System.out.println("Không thể gửi email.");
			}

			// Đóng kết nối
			email1.close();
		} else {
			System.out.println("Không thể khởi tạo kết nối email.");
		}
	}


	
}

