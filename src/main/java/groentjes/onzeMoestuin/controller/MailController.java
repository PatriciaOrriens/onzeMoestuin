package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.Mail;
import groentjes.onzeMoestuin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.internet.MimeMessage;

@Controller
public class MailController {

    @Autowired
    private JavaMailSender sender;

    @GetMapping("/testmail")
    String testmail() {
        String recipient = "wimkruizinga@gmail.com";
        String subject = "Uitnodiging Onze Moestuin";
        String body = "lolol";

        try {
            sendMail(recipient, subject, body);
            System.out.println("Email verstuurd!");
            return "redirect:/";
        } catch (Exception ex) {
            System.out.println("Fout bij sturen mail");
            return "redirect:/";
        }
    }

    @PostMapping("/garden/{gardenId}/sendEmailInvite")
    protected String sendEmailInvite(@PathVariable("gardenId") Integer gardenId,
                                     @AuthenticationPrincipal User user,
                                     @ModelAttribute("invitationMail") Mail invitationMail) {
        System.out.println(invitationMail.getBody());
        System.out.println(invitationMail.getRecipient());
        try {
            sendMail(invitationMail.getRecipient(), invitationMail.getSubject(), invitationMail.getBody());
            System.out.println("Email verstuurd!");
        } catch (Exception ex) {
            System.out.println("Fout bij sturen mail");
            return "redirect:/";
        }
        return "redirect:/garden/" + gardenId;
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
