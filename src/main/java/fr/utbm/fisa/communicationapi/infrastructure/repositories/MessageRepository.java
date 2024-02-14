package fr.utbm.fisa.communicationapi.infrastructure.repositories;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Discussion;
import fr.utbm.fisa.communicationapi.infrastructure.entities.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    Message findMessageByDiscussionOrderBySentAtDesc(Discussion discussion);

    Message findFirstByDiscussionOrderBySentAtDesc(Discussion discussion);

    List<Message> findMessagesByDiscussion(Discussion discussion);
}
