package fr.utbm.fisa.communicationapi.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageEditionDto {
    @NotBlank(message = "The content of the message cannot be empty")
    private String content;
}
