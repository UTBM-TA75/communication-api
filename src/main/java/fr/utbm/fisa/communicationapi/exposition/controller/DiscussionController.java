package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.domain.dto.HomeDiscussionPreview;
import fr.utbm.fisa.communicationapi.infrastructure.entities.Discussion;
import fr.utbm.fisa.communicationapi.infrastructure.entities.Message;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.DiscussionRepository;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DiscussionController {
    private final DiscussionRepository discussionRepository;
    private final MessageRepository messageRepository;

    /**
     * Gets all the discussions
     *
     * @return the list of discussions
     */
    @GetMapping("/discussions")
    public ResponseEntity<Iterable<Discussion>> getAllDiscussions() {
        return ResponseEntity.ok(discussionRepository.findAll());
    }

    /**
     * Get a discussion with the given id
     *
     * @param id the discussion's id
     * @return the discussion
     */
    @GetMapping("/discussion/{id}")
    public ResponseEntity<Discussion> getDiscussion(@PathVariable int id) {
        Discussion d = discussionRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No discussion found with the id " + id));

        return ResponseEntity.ok(d);
    }

    /**
     * Creates a discussion
     *
     * @param discussion the discussion to create
     * @return the created discussion
     */
    @PostMapping("/discussions")
    public ResponseEntity<Discussion> createDiscussion(@RequestBody Discussion discussion) {
        // TODO: Add validation to test if the discussion between theses two users already exists
        Discussion disc = new Discussion();
        disc.setUser1(discussion.getUser1());
        disc.setUser2(discussion.getUser2());
        disc.setDiscussionList(discussion.getDiscussionList());

        discussionRepository.save(disc);

        return new ResponseEntity<>(disc, HttpStatus.CREATED);
    }

    /**
     * Deletes the discussion with the given id
     *
     * @param id the discussion id
     */
    @DeleteMapping("/discussions/{id}")
    public void deleteDiscussion(@PathVariable int id) {
        Discussion d = discussionRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No discussion found with id " + id));

        discussionRepository.deleteById(id);
    }

    // TODO: comment me @Simon Eveillé
    @GetMapping("/discussions/preview")
    public ResponseEntity<Iterable<HomeDiscussionPreview>> getPreview(@RequestParam Long usrId) {
        ArrayList<HomeDiscussionPreview> previews = new ArrayList<>();

        List<Discussion> discussions = discussionRepository.findByUsrId(usrId);
        for (Discussion d : discussions) {
            Message lastMessage = messageRepository.findByDiscussionId(d.getId());
            String otherUser = d.getUser1().getId().equals(usrId) ? d.getUser2().getUsername() : d.getUser1().getUsername();
            int unseenCount = discussionRepository.countUnseenMessages(d.getId());

            previews.add(
                    HomeDiscussionPreview.builder()
                            .name(otherUser)
                            .message(lastMessage.getBody())
                            .unseenCount(unseenCount)
                            .build()
            );
        }

        return ResponseEntity.ok(previews);
    }

    /**
     * Get all the messages of a discussion
     *
     * @param id the discussion id
     * @return the message list of the discussion
     */
    @GetMapping("/discussions/{id}/messages")
    public ResponseEntity<Iterable<Message>> getMessages(@PathVariable int id) {
        Discussion discussion = discussionRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No discussion with id " + id));

        return ResponseEntity.ok(discussionRepository.getMessages(id));
    }
}
