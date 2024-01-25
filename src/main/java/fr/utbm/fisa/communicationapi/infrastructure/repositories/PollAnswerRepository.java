package fr.utbm.fisa.communicationapi.infrastructure.repositories;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Poll;
import fr.utbm.fisa.communicationapi.infrastructure.entities.PollAnswer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PollAnswerRepository extends CrudRepository<PollAnswer, Long> {
    List<PollAnswer> findAllByIdPoll(Poll poll);
}
