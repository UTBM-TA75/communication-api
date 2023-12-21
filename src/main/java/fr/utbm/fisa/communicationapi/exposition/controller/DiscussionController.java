package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Discussion;
import fr.utbm.fisa.communicationapi.infrastructure.entities.Pupil;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.DiscussionRepository;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.PupilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DiscussionController {
    private final DiscussionRepository discussionRepository;
    @GetMapping("/discussions")
    public Iterable<Discussion> get() {
        return discussionRepository.findAll();
    }

    @PostMapping("/discussions/create")
    public Discussion create(@RequestBody Discussion discussion) {
        Discussion disc = new Discussion();
        disc.setUser1(discussion.getUser1());
        disc.setUser2(discussion.getUser2());
        disc.setDiscussionList(discussion.getDiscussionList());
        return discussionRepository.save(disc);
    }

    @GetMapping("/discussions/delete")
    public void delete(@RequestBody Discussion discussion) {
        discussionRepository.deleteById(discussion.getId());
    }

    @PostMapping("/discussions/edit")
    public void edit(@RequestBody Discussion discussion) {
        discussionRepository.findById(discussion.getId()).map(
                disc -> {
                    disc.setUser1(discussion.getUser1());
                    disc.setUser2(discussion.getUser2());
                    disc.setDiscussionList(discussion.getDiscussionList());
                    return discussionRepository.save(disc);
                }
        );
    }
}
