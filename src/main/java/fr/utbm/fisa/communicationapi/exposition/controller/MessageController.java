package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Message;
import fr.utbm.fisa.communicationapi.infrastructure.entities.Pupil;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MessageController {
    private final MessageRepository messageRepository;

    /**
     * Create a message
     *
     * @param data the message's data to create
     * @return the created message
     */
    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message data) {
        Message message = new Message();
        message.setIdSender(data.getIdSender());
        message.setIdDiscussion(data.getIdDiscussion());
        message.setBody(data.getBody());

        messageRepository.save(message);

        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    /**
     * Deletes a message
     *
     * @param id the message's id
     */
    @GetMapping("/messages/{id}")
    public void delete(@PathVariable int id) {
        Message message = messageRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No message found with id " + id));

        messageRepository.deleteById(message.getId());
    }

    /**
     * Updates a message
     *
     * @param id   the message's id
     * @param data the message's updated data
     * @return the updated message
     */
    @PutMapping("/messages/{id}")
    public ResponseEntity<Message> edit(@PathVariable int id, @RequestBody Message data) {
        Message message = messageRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No message found with id " + id));

        message.setIdSender(data.getIdSender());
        message.setIdDiscussion(data.getIdDiscussion());
        message.setBody(data.getBody());
        message.setSentDate(data.getSentDate());
        message.setSeen(data.getSeen());
        message.setSeenDate(data.getSeenDate());

        messageRepository.save(message);

        return ResponseEntity.ok(message);
    }
}
