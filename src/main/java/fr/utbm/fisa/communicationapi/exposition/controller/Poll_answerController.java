package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Poll_answer;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.Poll_answerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class Poll_answerController {
    private final Poll_answerRepository poll_answerRepository;
    @GetMapping("/poll_answers")
    public Iterable<Poll_answer> get() {
        return poll_answerRepository.findAll();
    }

    @PostMapping("/poll_answers/create")
    public Poll_answer create(@RequestBody Poll_answer poll_answer) {
        Poll_answer poll_answr = new Poll_answer();
        poll_answr.setIdPoll(poll_answer.getIdPoll());
        poll_answr.setDescription(poll_answer.getDescription());
        poll_answr.setParentHasAnswered(poll_answer.getParentHasAnswered());
        return poll_answerRepository.save(poll_answr);
    }

    @GetMapping("/poll_answers/delete")
    public void delete(@RequestBody Poll_answer poll_answer) {
        poll_answerRepository.deleteById(poll_answer.getId());
    }

    @PostMapping("/poll_answers/edit")
    public void edit(@RequestBody Poll_answer poll_answer) {
        poll_answerRepository.findById(poll_answer.getId()).map(
                poll_answr -> {
                    poll_answr.setIdPoll(poll_answer.getIdPoll());
                    poll_answr.setDescription(poll_answer.getDescription());
                    poll_answr.setParentHasAnswered(poll_answer.getParentHasAnswered());
                    return poll_answerRepository.save(poll_answr);
                }
        );
    }
}
