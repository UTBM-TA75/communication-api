package fr.utbm.fisa.communicationapi.infrastructure.mappers;

import fr.utbm.fisa.communicationapi.domain.dto.DiscussionDto;
import fr.utbm.fisa.communicationapi.domain.dto.MessageDto;
import fr.utbm.fisa.communicationapi.infrastructure.entities.Discussion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiscussionMapper {

    default DiscussionDto toDiscussionDTO(Discussion discussion, MessageDto lastMessage, Long messagesNotSeen) {
        DiscussionDto dto = new DiscussionDto();

        dto.setId(discussion.getId());
        dto.setUser1(discussion.getUser1().getId());
        dto.setUser2(discussion.getUser2().getId());
        dto.setLastMessage(lastMessage);
        dto.setMessagesNotSeen(messagesNotSeen);

        return dto;
    }
}
