package groentjes.onzeMoestuin.repository;

import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.GardenInvitation;
import groentjes.onzeMoestuin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author Wim Kruizinga
 */
public interface GardenInvitationRepository extends JpaRepository<GardenInvitation, Integer> {

    List<GardenInvitation> findAllByInvitedUserAndAcceptedNull(User user);

    Optional<GardenInvitation> findByGardenAndInvitedUser(Garden garden, User user);

}
