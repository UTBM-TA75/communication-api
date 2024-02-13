package fr.utbm.fisa.communicationapi.infrastructure.mappers;

import fr.utbm.fisa.communicationapi.domain.dto.UserCreationDTO;
import fr.utbm.fisa.communicationapi.domain.dto.UserDTO;
import fr.utbm.fisa.communicationapi.infrastructure.entities.Usr;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "userType", target = "type")
    @Mapping(source = "isAdmin", target = "isAdmin")
    UserDTO toUserDTO(Usr usr);

    Iterable<UserDTO> toUserDTOList(Iterable<Usr> users);

    @Mapping(source = "isAdmin", target = "isAdmin")
    @Mapping(source = "type", target = "userType")
    Usr toUsr(UserCreationDTO userCreationDTO);
}
