package groentjes.onzeMoestuin.repository;

import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.GardenInvitation;
import groentjes.onzeMoestuin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Wim Kruizinga
 */
public interface GardenInvitationRepository extends JpaRepository<GardenInvitation, Integer> {

    List<GardenInvitation> findAllByInvitedUserAndAcceptedNull(User user);

    List<GardenInvitation> findAllByEmailAddress(String emailAddress);

    Optional<GardenInvitation> findByGardenAndInvitedUser(Garden garden, User user);

    Optional<GardenInvitation> findOneByInvitationTokenAndAcceptedNull(UUID uuid);

}
