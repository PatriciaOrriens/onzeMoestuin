package groentjes.onzeMoestuin.repository;

import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.GardenInvitation;
import groentjes.onzeMoestuin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

/**
 * @author Wim Kruizinga
 */
public interface GardenInvitationRepository extends JpaRepository<GardenInvitation, Integer> {

    ArrayList<Garden> findAllByInvitedUser(User user);

}
