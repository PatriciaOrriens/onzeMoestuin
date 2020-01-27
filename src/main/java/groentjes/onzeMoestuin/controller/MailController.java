package groentjes.onzeMoestuin.controller;

import com.google.common.collect.ImmutableMap;
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

    // Send Invitation mail
    @PostMapping("/garden/{gardenId}/sendEmailInvite")
    protected String sendEmailInvite(@PathVariable("gardenId") Integer gardenId,
                                     @AuthenticationPrincipal User user,
                                     @ModelAttribute("invitationMail") Mail invitationMail) {
        try {
            GardenInvitation newInvitation = new GardenInvitation();
            User invitingUser = userRepository.findById(user.getUserId()).get();
            // Set fields
            newInvitation.setGarden(gardenRepository.findById(gardenId).get());
            newInvitation.setEmailAddress(invitationMail.getRecipient());
            newInvitation.setUser(invitingUser);

            Template t = freemarkerConfig.getTemplate("gardenInvitation.ftl");
            invitationMail.setMessage(FreeMarkerTemplateUtils.processTemplateIntoString(t, ImmutableMap.of(
                    "body", invitationMail.getBody(),
                    "invitation", newInvitation,
                    "garden", newInvitation.getGarden(),
                    "sender", invitingUser
            )));

            sendMail(invitationMail);
            System.out.println("Email verstuurd!");

            gardenInvitationRepository.save(newInvitation);

        } catch (Exception ex) {
            System.out.println("Fout bij sturen mail");
            return "redirect:/";
        }
        return "redirect:/garden/" + gardenId;
    }

    private void sendMail(Mail mail) throws Exception {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(mail.getRecipient());
        helper.setSubject(mail.getSubject());
        helper.setText(mail.getMessage(), true);
        sender.send(message);
    }
}
