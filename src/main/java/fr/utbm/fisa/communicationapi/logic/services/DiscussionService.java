package fr.utbm.fisa.communicationapi.logic.services;

import fr.utbm.fisa.communicationapi.domain.dto.DiscussionCreationDTO;
import fr.utbm.fisa.communicationapi.domain.dto.DiscussionDTO;
import fr.utbm.fisa.communicationapi.infrastructure.entities.Discussion;
import fr.utbm.fisa.communicationapi.infrastructure.entities.Usr;
import fr.utbm.fisa.communicationapi.infrastructure.mappers.DiscussionMapper;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.DiscussionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class DiscussionService {
    private final DiscussionRepository discussionRepository;
    private final DiscussionMapper discussionMapper;
    private final UserService userService;


    public Iterable<Discussion> getAllDiscussions() {
        return discussionRepository.findAll();
    }

    public Discussion getDiscussion(Long id) {
        return discussionRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No discussion found with the id " + id));
    }

    public DiscussionDTO createDiscussion(DiscussionCreationDTO discussionCreationDTO) {
        Discussion discussionToCreate = new Discussion();

        // checks if users exist
        discussionToCreate.setUser1(userService.getUser(discussionCreationDTO.getUser1()));
        discussionToCreate.setUser2(userService.getUser(discussionCreationDTO.getUser2()));

        // checks if the discussion already exist
        if (discussionAlreadyExists(discussionToCreate.getUser1(), discussionToCreate.getUser2())) {
            System.out.println("exists");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Discussion between theses two users already exist");
        }

        System.out.println("not exist");
        return discussionMapper.toDiscussionDTO(
                discussionRepository.save(discussionToCreate)
        );
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
}
