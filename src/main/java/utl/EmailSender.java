package utl;

 import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {
    static final String from = "ngoc146789@gmail.com";
    static final String password = "vdblvfocejkqajxa";

    public static boolean sendEmail(String[] to, String subject, String content) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        Session session = Session.getInstance(props, auth);

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));

            InternetAddress[] recipients = new InternetAddress[to.length];
            for (int i = 0; i < to.length; i++) {
                recipients[i] = new InternetAddress(to[i]);
            }

            msg.setRecipients(Message.RecipientType.TO, recipients);
            msg.setSubject(subject);
            msg.setText(content);

            Transport.send(msg);
            System.out.println("Gửi email thành công");
            return true;
        } catch (Exception e) {
            System.out.println("Gặp lỗi trong quá trình gửi email");
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        String[] recipients = {"tranduchach2001@gmail.com"};
        String subject = "Email gửi đến nhiều người";
        String content = "Đây là nội dung email gửi đến nhiều người.";

        sendEmail(recipients, subject, content);
    }
}
