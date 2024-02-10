package fr.utbm.fisa.communicationapi.infrastructure.mappers;

import fr.utbm.fisa.communicationapi.domain.dto.DiscussionDTO;
import fr.utbm.fisa.communicationapi.domain.dto.MessageDTO;
import fr.utbm.fisa.communicationapi.infrastructure.entities.Discussion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiscussionMapper {

    default DiscussionDTO toDiscussionDTO(Discussion discussion, MessageDTO lastMessage, Long messagesNotSeen) {
        DiscussionDTO dto = new DiscussionDTO();

        dto.setId(discussion.getId());
        dto.setUser1(discussion.getUser1().getId());
        dto.setUser2(discussion.getUser2().getId());
        dto.setLastMessage(lastMessage);
        dto.setMessagesNotSeen(messagesNotSeen);

        return dto;
    }
}
