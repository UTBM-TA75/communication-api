package fr.utbm.fisa.communicationapi.domain.dto;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Message;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;

@Builder
@Getter
public class DiscussionMessages {
    private ArrayList<Message> messages;
}
