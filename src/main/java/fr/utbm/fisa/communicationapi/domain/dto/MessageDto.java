package fr.utbm.fisa.communicationapi.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDto {
    private Long id;
    private String content;
    private Long sentBy;
    private String sentAt;
    private String seenAt;
}
