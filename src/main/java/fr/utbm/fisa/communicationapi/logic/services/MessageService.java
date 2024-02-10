package fr.utbm.fisa.communicationapi.logic.services;

import fr.utbm.fisa.communicationapi.domain.dto.MessageCreationDTO;
import fr.utbm.fisa.communicationapi.domain.dto.MessageDTO;
import fr.utbm.fisa.communicationapi.domain.dto.MessageEditionDTO;
import fr.utbm.fisa.communicationapi.infrastructure.entities.Discussion;
import fr.utbm.fisa.communicationapi.infrastructure.entities.Message;
import fr.utbm.fisa.communicationapi.infrastructure.entities.Usr;
import fr.utbm.fisa.communicationapi.infrastructure.mappers.MessageMapper;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.DiscussionRepository;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.MessageRepository;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.UsrRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class MessageService {
    private final DiscussionRepository discussionRepository;
    private final MessageRepository messageRepository;
    private final UsrRepository userRepository;
    private final MessageMapper messageMapper;

    /**
     * Returns the list of messages
     *
     * @param discussionId the discussion id
     * @return the list of messages (DTO)
     */
    public Iterable<MessageDTO> getMessages(Long discussionId) {
        Discussion discussion = getDiscussion(discussionId);

        // Here we use the getMessages() method because it is less code
        return messageMapper.toDTOList(discussion.getMessages());
    }

    /**
     * Returns a specific message of a discussion
     *
     * @param discussionId the discussion if
     * @param messageId    the message id
     * @return the message
     */
    public MessageDTO getMessage(Long discussionId, Long messageId) {
        // We fetch the discussion to test if the caller is part of it.
        Discussion discussion = getDiscussion(discussionId);

        return messageMapper.toDTO(getMessage(messageId));
    }

    /**
     * Create a message
     *
     * @param discussionId the discussion id to create the message into
     * @param message      the message
     * @return the created message (DTO)
     */
    public Iterable<MessageDTO> createMessage(Long discussionId, MessageCreationDTO message) {
        Discussion discussion = getDiscussion(discussionId);

        Usr sender = getUser(message.getSentBy());

        Message messageToCreate = new Message();

        messageToCreate.setBody(message.getContent());
        messageToCreate.setDiscussion(discussion);
        messageToCreate.setSender(sender);

        messageRepository.save(messageToCreate);

        return getMessages(discussionId);
    }

    public MessageDTO updateMessage(Long discussionId, Long messageId, MessageEditionDTO messageEditionDTO) {
        Discussion discussion = getDiscussion(discussionId);

        Message message = getMessage(messageId);
        message.setBody(messageEditionDTO.getContent());

        return messageMapper.toDTO(messageRepository.save(message));
    }

    /**
     * Deletes a message in a discussion
     *
     * @param discussionId the discussion id
     * @param messageId    the message id
     */
    public Iterable<MessageDTO> deleteMessage(Long discussionId, Long messageId) {
        Discussion discussion = getDiscussion(discussionId);

        messageRepository.deleteById(getMessage(messageId).getId());

        return getMessages(discussionId);
    }

    private Discussion getDiscussion(Long discussionId) {
        return discussionRepository.findById(discussionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No discussion found with the id " + discussionId));
    }

    private Message getMessage(Long messageId) {
        return messageRepository.findById(messageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No message found with id " + messageId));
    }

    private Usr getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No user found with id " + userId));
    }
}
