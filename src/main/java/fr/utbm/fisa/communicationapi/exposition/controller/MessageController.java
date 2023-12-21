package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Message;
import fr.utbm.fisa.communicationapi.infrastructure.entities.Pupil;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MessageController {
    private final MessageRepository messageRepository;
    @GetMapping("/messages")
    public Iterable<Message> get() {
        return messageRepository.findAll();
    }

    @PostMapping("/messages/create")
    public Message create(@RequestBody Message message) {
        Message mess = new Message();
        mess.setIdSender(message.getIdSender());
        mess.setIdDiscussion(message.getIdDiscussion());
        mess.setBody(message.getBody());
        mess.setSentDate(message.getSentDate());
        mess.setSeen(message.getSeen());
        mess.setSeenDate(message.getSeenDate());
        return messageRepository.save(mess);
    }

    @GetMapping("/messages/delete")
    public void delete(@RequestBody Message message) {
        messageRepository.deleteById(message.getId());
    }

    @PostMapping("/messages/edit")
    public void edit(@RequestBody Message message) {
        messageRepository.findById(message.getId()).map(
                mess -> {
                    mess.setIdSender(message.getIdSender());
                    mess.setIdDiscussion(message.getIdDiscussion());
                    mess.setBody(message.getBody());
                    mess.setSentDate(message.getSentDate());
                    mess.setSeen(message.getSeen());
                    mess.setSeenDate(message.getSeenDate());
                    return messageRepository.save(mess);
                }
        );
    }
}
