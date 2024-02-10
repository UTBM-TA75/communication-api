package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.domain.dto.DiscussionCreationDTO;
import fr.utbm.fisa.communicationapi.domain.dto.DiscussionDTO;
import fr.utbm.fisa.communicationapi.domain.dto.HomeDiscussionPreview;
import fr.utbm.fisa.communicationapi.infrastructure.entities.Discussion;
import fr.utbm.fisa.communicationapi.infrastructure.entities.Message;
import fr.utbm.fisa.communicationapi.infrastructure.mappers.DiscussionMapper;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.DiscussionRepository;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.MessageRepository;
import fr.utbm.fisa.communicationapi.logic.services.DiscussionService;
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
    private final DiscussionService discussionService;
    private final DiscussionMapper discussionMapper;

    /**
     * Gets all the discussions
     *
     * @return the list of discussions
     */
    @GetMapping("/discussions")
    public ResponseEntity<Iterable<DiscussionDTO>> getAllDiscussions() {
        return ResponseEntity.ok(
                discussionMapper.toDiscussionDTOList(discussionService.getAllDiscussions())
        );
    }

    /**
     * Get a discussion with the given id
     *
     * @param id the discussion's id
     * @return the discussion
     */
    @GetMapping("/discussions/{id}")
    public ResponseEntity<DiscussionDTO> getDiscussion(@PathVariable Long id) {
        return ResponseEntity.ok(
                discussionMapper.toDiscussionDTO(discussionService.getDiscussion(id))
        );
    }

    /**
     * Creates a discussion
     *
     * @param discussion the discussion to create
     * @return the created discussion
     */
    @PostMapping("/discussions")
    public ResponseEntity<DiscussionDTO> createDiscussion(@RequestBody DiscussionCreationDTO discussion) {
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
    public void deleteDiscussion(@PathVariable Long id) {
        Discussion d = discussionRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No discussion found with id " + id));

        discussionRepository.deleteById(id);
    }

    @GetMapping("/discussions/preview")
    public ResponseEntity<Iterable<HomeDiscussionPreview>> getPreview(@RequestParam Long usrId) {
        ArrayList<HomeDiscussionPreview> previews = new ArrayList<>();

        List<Discussion> discussions = discussionRepository.findByUsrId(usrId);
        for (Discussion d : discussions) {
            Message lastMessage = messageRepository.findMessageByDiscussion(d);
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
    public ResponseEntity<Iterable<Message>> getMessages(@PathVariable Long id) {
        Discussion discussion = discussionRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No discussion with id " + id));

        return ResponseEntity.ok(discussion.getMessages());
    }
}
