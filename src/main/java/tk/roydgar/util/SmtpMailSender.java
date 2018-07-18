package tk.roydgar.util;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class SmtpMailSender {

    private JavaMailSender javaMailSender;

    public JavaMailSender send(String to, String subject, String body)  {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;

        try {
            helper = new MimeMessageHelper(message, true);

            helper.setSubject(subject);
            helper.setTo(to);
            helper.setText(body, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(message);
        return javaMailSender;
    }
}

