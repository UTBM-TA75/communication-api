package fr.utbm.fisa.communicationapi.logic.services;

import fr.utbm.fisa.communicationapi.domain.dto.DiscussionCreationDTO;
import fr.utbm.fisa.communicationapi.domain.dto.DiscussionDTO;
import fr.utbm.fisa.communicationapi.domain.dto.MessageDTO;
import fr.utbm.fisa.communicationapi.infrastructure.entities.Discussion;
import fr.utbm.fisa.communicationapi.infrastructure.entities.Usr;
import fr.utbm.fisa.communicationapi.infrastructure.mappers.DiscussionMapper;
import fr.utbm.fisa.communicationapi.infrastructure.mappers.MessageMapper;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.DiscussionRepository;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class DiscussionService {
    private final DiscussionRepository discussionRepository;
    private final DiscussionMapper discussionMapper;
    private final UserService userService;
    private final MessageService messageService;
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;


    /**
     * Gets all the discussions
     *
     * @return the list of discussions
     */
    public Iterable<DiscussionDTO> getAllDiscussions() {
        Iterable<Discussion> discussions = discussionRepository.findAll();

        ArrayList<DiscussionDTO> discussionDTOS = new ArrayList<>();
        discussions.forEach(discussion -> {
            discussionDTOS.add(
                    discussionMapper.toDiscussionDTO(
                            discussion,
                            getLastMessage(discussion),
                            countUnseenMessages(discussion)
                    )
            );
        });

        return discussionDTOS;
    }

    /**
     * Gets a discussion by its id
     *
     * @param id the discussion id
     * @return the discussion
     */
    public DiscussionDTO getDiscussion(Long id) {
        Discussion discussion = discussionRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No discussion found with the id " + id));

        return discussionMapper.toDiscussionDTO(
                discussion,
                getLastMessage(discussion),
                countUnseenMessages(discussion)
        );
    }

    /**
     * Creates discussion
     *
     * @param discussionCreationDTO the discussion data
     * @return the created discussion
     */
    public DiscussionDTO createDiscussion(DiscussionCreationDTO discussionCreationDTO) {
        Discussion discussionToCreate = new Discussion();

        // checks if users exist
        discussionToCreate.setUser1(userService.getUser(discussionCreationDTO.getUser1()));
        discussionToCreate.setUser2(userService.getUser(discussionCreationDTO.getUser2()));

        // checks if the discussion already exist
        if (discussionAlreadyExists(discussionToCreate.getUser1(), discussionToCreate.getUser2())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Discussion between theses two users already exist");
        }

        discussionRepository.save(discussionToCreate);

        // We fetch again the discussion to have the correct dto
        return getDiscussion(discussionToCreate.getId());
    }

    /**
     * Checks if a conversation between two users already exists
     *
     * @param user1 the first user
     * @param user2 the second user
     * @return true if no conversation, or else
     */
    public Boolean discussionAlreadyExists(Usr user1, Usr user2) {
        return !(discussionRepository.findDiscussionByUser1AndUser2(user1, user2).isEmpty()
                && discussionRepository.findDiscussionByUser1AndUser2(user2, user1).isEmpty());
    }

    /**
     * Returns the number of unseen messages for a discussion
     *
     * @param discussion the discussion
     * @return the number of unseen messages
     */
    public Long countUnseenMessages(Discussion discussion) {
        return discussionRepository.countUnseenMessages(discussion.getId());
    }

    /**
     * Deletes a discussion
     *
     * @param discussionId the discussion id
     */
    public void deleteDiscussion(Long discussionId) {
        Discussion d = discussionRepository
                .findById(discussionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No discussion found with id " + discussionId));

        discussionRepository.deleteById(discussionId);
    }

    private MessageDTO getLastMessage(Discussion discussion) {
        return messageMapper.toDTO(messageRepository.findFirstByDiscussionOrderBySentAtDesc(discussion));
    }
}
