package fr.utbm.fisa.communicationapi.infrastructure.repositories;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Discussion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiscussionRepository extends CrudRepository<Discussion, Long> {
    @Query(value = "SELECT * FROM discussion d WHERE d.user1 = :usrId OR d.user2 = :usrId",
            nativeQuery = true)
    List<Discussion> findByUsrId(@Param("usrId") Long userId);

    @Query(value = "SELECT COUNT(*) FROM message m WHERE m.idDiscussion = :idDiscussion AND m.seen =  0",
            nativeQuery = true)
    Integer countUnseenMessages(@Param("idDiscussion") Long idDiscussion);
}
