package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Poll;
import fr.utbm.fisa.communicationapi.infrastructure.entities.PollAnswer;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.PollAnswerRepository;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.PollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PollController {
    private final PollRepository pollRepository;
    private final PollAnswerRepository pollAnswerRepository;

    /**
     * Gets all the polls
     *
     * @return the list of polls
     */
    @GetMapping("/polls")
    public ResponseEntity<Iterable<Poll>> getAllPoll() {
        return ResponseEntity.ok(pollRepository.findAll());
    }

    /**
     * Gets a poll
     *
     * @param id the poll's id
     * @return the poll
     */
    @GetMapping("/polls/{id}")
    public ResponseEntity<Poll> getPoll(@PathVariable int id) {
        Poll poll = pollRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No poll found with id " + id));

        return ResponseEntity.ok(poll);
    }

    /**
     * Creates a poll
     *
     * @param data the poll's data
     * @return the created poll
     */
    @PostMapping("/polls")
    public ResponseEntity<Poll> createPoll(@RequestBody Poll data) {
        Poll poll = new Poll();
        poll.setDescription(data.getDescription());
        poll.setClosingDate(data.getClosingDate());
        poll.setIdCommunication(data.getIdCommunication());
        poll.setPollAnwserPerPollList(data.getPollAnwserPerPollList());

        pollRepository.save(poll);

        return new ResponseEntity<>(poll, HttpStatus.CREATED);
    }


    /**
     * Deletes a poll
     *
     * @param id the poll's id
     */
    @DeleteMapping("/polls/{id}")
    public void deletePoll(@PathVariable int id) {
        Poll poll = pollRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No poll found with id " + id));

        pollRepository.deleteById(poll.getId());
    }

    /**
     * Updates a poll
     *
     * @param id   the poll's id
     * @param data the poll's updated data
     * @return the updated poll|
     */
    @PutMapping("/polls/{id}")
    public ResponseEntity<Poll> edit(@PathVariable int id, @RequestBody Poll data) {
        Poll poll = pollRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No poll found with id " + id));

        poll.setDescription(data.getDescription());
        poll.setClosingDate(data.getClosingDate());
        poll.setIdCommunication(data.getIdCommunication());
        poll.setPollAnwserPerPollList(data.getPollAnwserPerPollList());

        pollRepository.save(poll);

        return ResponseEntity.ok(poll);
    }

    /**
     * Gets all possible answers for a specific poll
     *
     * @param id the poll's id
     * @return the list of possible answers
     */
    @GetMapping("/polls/{id}/possible_answers")
    public ResponseEntity<Iterable<PollAnswer>> getPollPossibleAnswers(@PathVariable int id) {
        Poll poll = pollRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No poll found with id " + id));

        return ResponseEntity.ok(pollAnswerRepository.findAllByIdPoll(poll));
    }
}
