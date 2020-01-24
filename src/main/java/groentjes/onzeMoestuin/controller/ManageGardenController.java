package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.*;
import groentjes.onzeMoestuin.repository.GardenInvitationRepository;
import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.GardenRepository;
import groentjes.onzeMoestuin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 * @author Eric van Dalen, Wim Kruizinga, Patricia Orriens, Gjalt Wybenga
 * Controller class to manage gardens
 */
@Controller
public class ManageGardenController {

    @Autowired
    private GardenRepository gardenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GardenInvitationRepository gardenInvitationRepository;

    @GetMapping("/userManageGardens")
    public String allGardensByMember(Model model, @AuthenticationPrincipal User currentUser) {
        model.addAttribute("allYourGardens", gardenRepository.findAllByGardenMembers(currentUser));
        User user = (User) userRepository.findByUsername(currentUser.getUsername()).get();
        model.addAttribute("currentUser", user);

        List<GardenInvitation> invitations = gardenInvitationRepository.findAllByInvitedUserAndAcceptedNull(user);

        if (!invitations.isEmpty()) {
            model.addAttribute("invitations", invitations);
        }
        return "manageGarden";
    }

    @GetMapping("/user/garden/delete/{gardenId}")
    public String deleteGarden(@ModelAttribute("gardenId") Integer gardenId,
                               @AuthenticationPrincipal User user, BindingResult result) {

        Optional<Garden> garden = gardenRepository.findById(gardenId);
        if (garden.isPresent()) {
            if(garden.get().isGardenMember(user)) {
                gardenRepository.delete(garden.get());
            }
        }
        return "redirect:/userManageGardens";
    }

    @GetMapping("/garden/update/{gardenId}")
    protected String showUpdateGarden(@PathVariable("gardenId") final Integer gardenId, Model model){
        Optional<Garden> foundGarden = gardenRepository.findById(gardenId);
        if (foundGarden.isPresent()) {
            model.addAttribute("garden", foundGarden.get());
            return "userChangeGarden";
        }
        return "redirect:/userManageGardens";
    }

    @PostMapping("/garden/update/{gardenId}")
    protected String updateGarden(@ModelAttribute("garden") Garden garden,
                                  @AuthenticationPrincipal User user, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/garden/update";
        } else {
            User owner = userRepository.getOne(user.getUserId());
            garden.addGardenMember(owner);
            gardenRepository.save(garden);
            return "redirect:/userManageGardens";
        }
    }

    @GetMapping("/garden/{gardenId}/invite")
    protected String inviteToGarden(Model model, @PathVariable("gardenId") final Integer gardenId,
                                    @RequestParam(value = "search") Optional<String> search,
                                    @AuthenticationPrincipal User user) {
        Optional<Garden> garden = gardenRepository.findById(gardenId);

        // Object for passing message to user
        Notification notification = new Notification();

        if (garden.isPresent()) {
            model.addAttribute("garden", garden.get());
            if (search.isPresent()) {
                Optional<User> foundUser = userRepository.findByEmail(search.get());
                if (foundUser.isPresent()) {
                    model.addAttribute("foundUser", foundUser.get());
                    // check whether user is already member of garden
                    if (garden.get().isGardenMember(foundUser.get())) {
                        notification.setMessage("Gebruiker <b>" + foundUser.get().getFirstName()
                                + "</b> is al lid van deze tuin");
                    }
                } else {
                    notification.setMessage("Geen gebruiker gevonden voor <b>" + search.get() + "</b>");
                    Mail invitationMail = new Mail();
                    invitationMail.setRecipient(search.get());
                    invitationMail.setSender(userRepository.getOne(user.getUserId()));
                    model.addAttribute("invitationMail", invitationMail);

                }
            model.addAttribute("message", notification);
            }
            return "inviteGardenMember";
        }
        return "redirect:/";
    }
}