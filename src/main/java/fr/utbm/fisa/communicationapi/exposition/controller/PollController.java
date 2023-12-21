package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Poll;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.PollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PollController {
    private final PollRepository pollRepository;
    @GetMapping("/polls")
    public Iterable<Poll> get() {
        return pollRepository.findAll();
    }

    @PostMapping("/polls/create")
    public Poll create(@RequestBody Poll poll) {
        Poll pll = new Poll();
        pll.setDescription(poll.getDescription());
        pll.setClosingDate(poll.getClosingDate());
        pll.setIdCommunication(poll.getIdCommunication());
        pll.setPollAnwserPerPollList(poll.getPollAnwserPerPollList());
        return pollRepository.save(pll);
    }

    @GetMapping("/polls/delete")
    public void delete(@RequestBody Poll poll) {
        pollRepository.deleteById(poll.getId());
    }

    @PostMapping("/polls/edit")
    public void edit(@RequestBody Poll poll) {
        pollRepository.findById(poll.getId()).map(
                pll -> {
                    pll.setDescription(poll.getDescription());
                    pll.setClosingDate(poll.getClosingDate());
                    pll.setIdCommunication(poll.getIdCommunication());
                    pll.setPollAnwserPerPollList(poll.getPollAnwserPerPollList());
                    return pollRepository.save(pll);
                }
        );
    }
}
