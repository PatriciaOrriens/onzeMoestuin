package groentjes.onzeMoestuin.controller;

import freemarker.template.Template;
import groentjes.onzeMoestuin.model.GardenInvitation;
import groentjes.onzeMoestuin.model.Mail;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.GardenInvitationRepository;
import groentjes.onzeMoestuin.repository.GardenRepository;
import groentjes.onzeMoestuin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import freemarker.template.Configuration;

import javax.mail.internet.MimeMessage;

@Controller
public class MailController {

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private Configuration freemarkerConfig;

    @Autowired
    private GardenInvitationRepository gardenInvitationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GardenRepository gardenRepository;

    @PostMapping("/garden/{gardenId}/sendEmailInvite")
    protected String sendEmailInvite(@PathVariable("gardenId") Integer gardenId,
                                     @AuthenticationPrincipal User user,
                                     @ModelAttribute("invitationMail") Mail invitationMail) {
        try {
            sendMail(invitationMail.getRecipient(), invitationMail.getSubject(), invitationMail.getBody());
            System.out.println("Email verstuurd!");

            GardenInvitation newInvitation = new GardenInvitation();
            User invitingUser = userRepository.findById(user.getUserId()).get();

            // Set fields
            newInvitation.setGarden(gardenRepository.findById(gardenId).get());
            newInvitation.setEmailAddress(invitationMail.getRecipient());
            newInvitation.setUser(invitingUser);

            gardenInvitationRepository.save(newInvitation);

        } catch (Exception ex) {
            System.out.println("Fout bij sturen mail");
            return "redirect:/";
        }
        return "redirect:/garden/" + gardenId;
    }

    private void sendMail(String recipient, String subject, String body) throws Exception {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        Template t = freemarkerConfig.getTemplate("gardenInvitation.ftl");
//        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, body);

        helper.setTo(recipient);
        helper.setSubject(subject);
        helper.setText(t.toString(), true);
        sender.send(message);
    }
}
