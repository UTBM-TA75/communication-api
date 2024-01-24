package fr.utbm.fisa.communicationapi.infrastructure.repositories;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Poll;
import fr.utbm.fisa.communicationapi.infrastructure.entities.PollAnswer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PollAnswerRepository extends CrudRepository<PollAnswer, Integer> {
    List<PollAnswer> findAllByIdPoll(Poll poll);
}
