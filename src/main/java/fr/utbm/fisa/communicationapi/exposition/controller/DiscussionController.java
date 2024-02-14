package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.domain.dto.DiscussionCreationDto;
import fr.utbm.fisa.communicationapi.domain.dto.DiscussionDto;
import fr.utbm.fisa.communicationapi.domain.dto.HomeDiscussionPreview;
import fr.utbm.fisa.communicationapi.infrastructure.entities.Discussion;
import fr.utbm.fisa.communicationapi.infrastructure.entities.Message;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.DiscussionRepository;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.MessageRepository;
import fr.utbm.fisa.communicationapi.logic.services.DiscussionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DiscussionController {
    private final DiscussionRepository discussionRepository;
    private final MessageRepository messageRepository;
    private final DiscussionService discussionService;

    /**
     * Gets all the discussions
     *
     * @return the list of discussions
     */
    @GetMapping("/discussions")
    public ResponseEntity<Iterable<DiscussionDto>> getAllDiscussions() {
        return ResponseEntity.ok(discussionService.getAllDiscussions());
    }

    /**
     * Get a discussion with the given id
     *
     * @param id the discussion's id
     * @return the discussion
     */
    @GetMapping("/discussions/{id}")
    public ResponseEntity<DiscussionDto> getDiscussion(@PathVariable Long id) {
        return ResponseEntity.ok(
                discussionService.getDiscussion(id)
        );
    }

    /**
     * Creates a discussion
     *
     * @param discussion the discussion to create
     * @return the created discussion
     */
    @PostMapping("/discussions")
    public ResponseEntity<DiscussionDto> createDiscussion(@RequestBody DiscussionCreationDto discussion) {
        return new ResponseEntity<>(
                discussionService.createDiscussion(discussion),
                HttpStatus.CREATED
        );
    }

    /**
     * Deletes the discussion with the given id
     *
     * @param id the discussion id
     */
    @DeleteMapping("/discussions/{id}")
    public ResponseEntity<?> deleteDiscussion(@PathVariable Long id) {
        discussionService.deleteDiscussion(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/discussions/preview")
    public ResponseEntity<Iterable<HomeDiscussionPreview>> getPreview(@RequestParam Long usrId) {
        ArrayList<HomeDiscussionPreview> previews = new ArrayList<>();

        List<Discussion> discussions = discussionRepository.findByUsrId(usrId);
        for (Discussion d : discussions) {
            Message lastMessage = messageRepository.findMessageByDiscussionOrderBySentAtDesc(d);
            String otherUser = d.getUser1().getId().equals(usrId) ? d.getUser2().getUsername() : d.getUser1().getUsername();
            Long unseenCount = discussionRepository.countUnseenMessages(d.getId());

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
}
