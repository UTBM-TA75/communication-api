package fr.utbm.fisa.communicationapi.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscussionDTO {
    private Long id;
    private String user1;
    private String user2;
    private MessageDTO lastMessage;
    private Long messagesNotSeen;
}
