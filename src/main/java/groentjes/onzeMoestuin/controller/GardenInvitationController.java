package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.GardenInvitation;
import groentjes.onzeMoestuin.model.Mail;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.GardenInvitationRepository;
import groentjes.onzeMoestuin.repository.GardenRepository;
import groentjes.onzeMoestuin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Invite existing member to garden
    @PostMapping("/garden/{gardenId}/invite")
    protected String inviteExistingMember(@ModelAttribute("foundUser") User newMember,
                                          @PathVariable("gardenId") Integer gardenId) {

        User user = getUser();
        User invitingUser = userRepository.findById(user.getUserId()).get();

        GardenInvitation newInvitation = new GardenInvitation();
        // Set invitation fields
        newInvitation.setInvitedUser(newMember);
        newInvitation.setGarden(gardenRepository.findById(gardenId).get());
        newInvitation.setUser(invitingUser);

        gardenInvitationRepository.save(newInvitation);
        return "redirect:/garden/" + gardenId;
    }

    @GetMapping("/garden/{gardenId}/acceptInvitation")
    protected String acceptInvitation(@PathVariable("gardenId") Integer gardenId) {

        User user = getUser();

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
    protected String refuseInvitation(@PathVariable("gardenId") Integer gardenId) {

        User user = getUser();

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

    private User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userRepository.findByUsername(currentPrincipalName).orElseThrow(() -> new UsernameNotFoundException(currentPrincipalName));
    }
}