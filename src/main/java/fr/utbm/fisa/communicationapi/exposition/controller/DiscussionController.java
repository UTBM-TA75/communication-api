package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.domain.dto.HomeDiscussionPreview;
import fr.utbm.fisa.communicationapi.infrastructure.entities.Discussion;
import fr.utbm.fisa.communicationapi.infrastructure.entities.Message;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.DiscussionRepository;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DiscussionController {
    private final DiscussionRepository discussionRepository;
    private final MessageRepository messageRepository;
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

    @GetMapping("/discussions/preview")
    public Iterable<HomeDiscussionPreview> getPreview(@RequestParam Long usrId) {
        ArrayList<HomeDiscussionPreview> previews = new ArrayList<>();

        List<Discussion> discussions = discussionRepository.findByUsrId(usrId);
        for (Discussion d: discussions) {
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

        return previews;
    }

    @GetMapping("/discussions/messages")
    public Iterable<Message> getMessages(@RequestParam int discussionId){
        Discussion discussion = discussionRepository.findByDiscussionId(discussionId);
        return discussionRepository.getMessages(discussion);
    }

}
