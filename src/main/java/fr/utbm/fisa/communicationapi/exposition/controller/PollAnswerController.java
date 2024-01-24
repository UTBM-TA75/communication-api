package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.infrastructure.entities.PollAnswer;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.PollAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PollAnswerController {
    private final PollAnswerRepository poll_answerRepository;

    /**
     * Creates a poll answer
     *
     * @param data the poll answer's data
     * @return the created poll answer
     */
    @PostMapping("/poll_answers")
    public ResponseEntity<PollAnswer> createPollAnswer(@RequestBody PollAnswer data) {
        PollAnswer pollAnswer = new PollAnswer();

        pollAnswer.setDescription(data.getDescription());

        poll_answerRepository.save(pollAnswer);

        return new ResponseEntity<>(pollAnswer, HttpStatus.CREATED);
    }

    /**
     * Deletes a poll answer
     *
     * @param id the poll answer's id
     */
    @DeleteMapping("/poll_answers/{id}")
    public void deletePollAnswer(@PathVariable int id) {
        PollAnswer pollAnswer = poll_answerRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No poll answer found with id " + id));

        poll_answerRepository.deleteById(pollAnswer.getId());
    }

    /**
     * Updates a poll answer
     *
     * @param id   the poll answer id
     * @param data the poll answer updated data
     * @return the updated poll answer
     */
    @PutMapping("/poll_answers/{id}")
    public ResponseEntity<PollAnswer> editPollAnswer(@PathVariable int id, @RequestBody PollAnswer data) {
        PollAnswer pollAnswer = poll_answerRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No poll answer found with id " + id));

        pollAnswer.setDescription(data.getDescription());
        pollAnswer.setParentHasAnswered(data.getParentHasAnswered());

        poll_answerRepository.save(pollAnswer);

        return ResponseEntity.ok(pollAnswer);
    }
}
