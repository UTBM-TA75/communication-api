package fr.utbm.fisa.communicationapi.domain.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class HomeDiscussionPreview {

    private String name;
    private LocalDate lastMessageReceived;
    private String message;
    private Long unseenCount;
}
