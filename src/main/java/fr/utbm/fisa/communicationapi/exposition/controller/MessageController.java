package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.domain.dto.MessageCreationDTO;
import fr.utbm.fisa.communicationapi.domain.dto.MessageDTO;
import fr.utbm.fisa.communicationapi.domain.dto.MessageEditionDTO;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.MessageRepository;
import fr.utbm.fisa.communicationapi.logic.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MessageController {
    private final MessageRepository messageRepository;
    private final MessageService messageService;


    /**
     * Get all the messages of a discussion
     *
     * @param id the discussion id
     * @return the message list of the discussion
     */
    @GetMapping("/discussions/{id}/messages")
    public ResponseEntity<Iterable<MessageDTO>> getMessages(@PathVariable Long id) {
        return ResponseEntity.ok(messageService.getMessages(id));
    }

    @GetMapping("/discussions/{discussionId}/messages/{messageId}")
    public ResponseEntity<MessageDTO> getMessage(@PathVariable Long discussionId, @PathVariable Long messageId) {
        return ResponseEntity.ok(messageService.getMessage(discussionId, messageId));
    }

    /**
     * Create a new message in a discussion
     *
     * @param id      the discussion id
     * @param message the message
     * @return the updated list of messages
     */
    @PostMapping("/discussions/{id}/messages")
    public ResponseEntity<Iterable<MessageDTO>> createMessage(@PathVariable Long id, @RequestBody MessageCreationDTO message) {
        return ResponseEntity.ok(messageService.createMessage(id, message));
    }

    /**
     * Deletes a message in a discussion
     *
     * @param discussionId the discussion id
     * @param messageId    the message id to delete
     * @return the updated list of messages
     */
    @DeleteMapping("/discussions/{discussionId}/messages/{messageId}")
    public ResponseEntity<Iterable<MessageDTO>> deleteMessage(@PathVariable Long discussionId, @PathVariable Long messageId) {
        return ResponseEntity.ok(messageService.deleteMessage(discussionId, messageId));
    }

    /**
     * Updates a message
     *
     * @param discussionId the discussion id
     * @param messageId    the message id to update
     * @param data         the updated message data
     * @return the updated message
     */
    @PatchMapping("/discussions/{discussionId}/messages/{messageId}")
    public ResponseEntity<MessageDTO> updateMessage(
            @PathVariable Long discussionId,
            @PathVariable Long messageId,
            @RequestBody MessageEditionDTO data) {
        return ResponseEntity.ok(messageService.updateMessage(discussionId, messageId, data));

    }
}
