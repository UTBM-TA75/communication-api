package fr.utbm.fisa.communicationapi.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscussionDTO {
    private Long id;
    private Long user1;
    private Long user2;
    private MessageDTO lastMessage;
    private Long messagesNotSeen;
}
