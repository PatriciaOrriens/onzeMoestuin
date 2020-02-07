package groentjes.onzeMoestuin.repository;

import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @Author Patricia Orriens-Spuij
 */
public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findAllByGardenOrderByDateTimeDesc(Garden garden);

    List<Message> findAllByGarden(Garden garden, Pageable pageable);

    Optional<Message> findFirstByGardenOrderByDateTimeDesc(Garden garden);

//    @Query("select case when count(m) > 0 then true else false end from Message m where m.dateTime > :dateTime")
//    boolean existsMessageNewerThanCustomQuery(@Param("dateTime") LocalDateTime dateTime);

//    @Query("select case when count(c)> 0 then true else false end from Car c where lower(c.model) like lower(:model)")
//    boolean existsCarLikeCustomQuery(@Param("model") String model);
}
