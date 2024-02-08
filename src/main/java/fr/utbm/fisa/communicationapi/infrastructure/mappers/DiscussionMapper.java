package fr.utbm.fisa.communicationapi.infrastructure.mappers;

import fr.utbm.fisa.communicationapi.domain.dto.DiscussionDTO;
import fr.utbm.fisa.communicationapi.infrastructure.entities.Discussion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DiscussionMapper {
    @Mapping(target = "user", source = "discussion.user1.id")
    DiscussionDTO toDiscussionDTO(Discussion discussion);
}
