package fr.utbm.fisa.communicationapi.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscussionDTO {
    private Long id;
    private String user;
    private String lastMessage;
    private String messageDate;
    private Long messagesNotSeen;
}
