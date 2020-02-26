package groentjes.onzeMoestuin.service;

import groentjes.onzeMoestuin.model.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
 * @author Wim Kruizinga
 * Email sender service
 */
@Service
public class EmailService {

    @Autowired
    private JavaMailSender sender;

    public void sendMail(Mail mail) throws Exception {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(mail.getRecipient());
        helper.setSubject(mail.getSubject());
        helper.setText(mail.getMessage(), true);
        sender.send(message);
    }

}
