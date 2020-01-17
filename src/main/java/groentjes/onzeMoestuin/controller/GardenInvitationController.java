package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.GardenInvitation;
import groentjes.onzeMoestuin.model.Mail;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.GardenInvitationRepository;
import groentjes.onzeMoestuin.repository.GardenRepository;
import groentjes.onzeMoestuin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

/**
 * @author Wim Kruizinga
 */
@Controller
public class GardenInvitationController {

    @Autowired
    private GardenInvitationRepository gardenInvitationRepository;

    @Autowired
    private GardenRepository gardenRepository;

    @Autowired
    private UserRepository userRepository;

    // Invite existing member to garden
    @PostMapping("/garden/{gardenId}/invite")
    protected String inviteExistingMember(@ModelAttribute("foundUser") User newMember,
                                          @PathVariable("gardenId") Integer gardenId,
                                          @AuthenticationPrincipal User user) {
        GardenInvitation newInvitation = new GardenInvitation();
        User invitingUser = userRepository.findById(user.getUserId()).get();

        // Set fields
        newInvitation.setInvitedUser(newMember);
        newInvitation.setGarden(gardenRepository.findById(gardenId).get());
        newInvitation.setUser(invitingUser);

        gardenInvitationRepository.save(newInvitation);
        return "redirect:/garden/" + gardenId;
    }

    @GetMapping("/garden/{gardenId}/acceptInvitation")
    protected String acceptInvitation(@PathVariable("gardenId") Integer gardenId,
                                      @AuthenticationPrincipal User user) {
        Garden garden = gardenRepository.findById(gardenId).get();
        Optional<GardenInvitation> invitation = gardenInvitationRepository.findByGardenAndInvitedUser(garden, user);

        if (invitation.isPresent()) {
            // Check if current user is invited user
            if (user.getUserId().equals(invitation.get().getInvitedUser().getUserId())) {
                invitation.get().setAccepted(true);
                gardenInvitationRepository.save(invitation.get());
                garden.addGardenMember(user);
                gardenRepository.save(garden);
            }
        }
        return "redirect:/garden/" + gardenId;
    }

    @GetMapping("/garden/{gardenId}/refuseInvitation")
    protected String refuseInvitation(@PathVariable("gardenId") Integer gardenId,
                                      @AuthenticationPrincipal User user) {
        Garden garden = gardenRepository.findById(gardenId).get();
        Optional<GardenInvitation> invitation = gardenInvitationRepository.findByGardenAndInvitedUser(garden, user);

        if (invitation.isPresent()) {
            // Check if current user is invited user
            if (user.getUserId().equals(invitation.get().getInvitedUser().getUserId())) {
                invitation.get().setAccepted(false);
                gardenInvitationRepository.save(invitation.get());
            }
        }
        return "redirect:/userManageGardens";
    }

    @PostMapping("/garden/{gardenId}/sendEmailInvite")
    protected String sendEmailInvite(@PathVariable("gardenId") Integer gardenId,
                                     @AuthenticationPrincipal User user,
                                     @ModelAttribute("invitationMail") Mail invitationMail) {
        System.out.println(invitationMail.getBody());
        System.out.println(invitationMail.getRecipient());
        return "redirect:/garden/" + gardenId;
    }
}
