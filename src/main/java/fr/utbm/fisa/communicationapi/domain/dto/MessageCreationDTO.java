package fr.utbm.fisa.communicationapi.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageCreationDTO {
    private String content;
    private Long sentBy;
}
