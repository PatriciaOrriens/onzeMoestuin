package groentjes.onzeMoestuin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.mail.internet.MimeMessage;

@Controller
public class MailController {

    @Autowired
    private JavaMailSender sender;

    @GetMapping("/testmail")
    String testmail() {
        String recipient = "wimkruizinga@gmail.com";
        String subject = "Uitnodiging Onze Moestuin";
        String message = "lolol";

        try {
            sendMail(recipient, subject, message);
            System.out.println("Email verstuurd!");
            return "redirect:/";
        } catch (Exception ex) {
            System.out.println("Fout bij sturen mail");
            return "redirect:/";
        }
    }

    private void sendMail(String recipient, String subject, String body) throws Exception {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(recipient);
        helper.setSubject(subject);
        helper.setText(body);
        sender.send(message);
    }
}
