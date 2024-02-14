package fr.utbm.fisa.communicationapi.infrastructure.mappers;

import fr.utbm.fisa.communicationapi.domain.dto.MessageDto;
import fr.utbm.fisa.communicationapi.infrastructure.entities.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    @Mapping(target = "sentBy", source = "sender.id")
    @Mapping(target = "content", source = "body")
    MessageDto toDTO(Message message);

    Iterable<MessageDto> toDTOList(Iterable<Message> messages);
}
