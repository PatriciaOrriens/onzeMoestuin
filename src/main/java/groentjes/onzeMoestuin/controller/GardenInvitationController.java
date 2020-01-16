package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.GardenInvitation;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.GardenInvitationRepository;
import groentjes.onzeMoestuin.repository.GardenRepository;
import groentjes.onzeMoestuin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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


}
