package fr.utbm.fisa.communicationapi.infrastructure.repositories;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MessageRepository extends CrudRepository<Message, Integer> {

    @Query(value = "SELECT * FROM message m WHERE m.idDiscussion = :discussionId ORDER BY sentDate, DESC LIMIT 1",
            nativeQuery = true)
    Message findByDiscussionId(@Param("discussionId") int discussionId);
}
