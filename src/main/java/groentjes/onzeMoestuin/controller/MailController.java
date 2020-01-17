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
        try {
            sendTestMail();
            System.out.println("Email verstuurd!");
            return "redirect:/";
        } catch (Exception ex) {
            System.out.println("Fout bij sturen mail");
            return "redirect:/";
        }
    }

    private void sendTestMail() throws Exception {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo("wimkruizinga@gmail.com");
        helper.setText("DOLOR DOLOR DOLOR");
        helper.setSubject("Testmail");

        sender.send(message);

    }
}
