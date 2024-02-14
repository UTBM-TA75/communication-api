package fr.utbm.fisa.communicationapi.logic.services;

import fr.utbm.fisa.communicationapi.domain.dto.UserCreationDto;
import fr.utbm.fisa.communicationapi.domain.dto.UserDto;
import fr.utbm.fisa.communicationapi.infrastructure.entities.Usr;
import fr.utbm.fisa.communicationapi.infrastructure.mappers.UserMapper;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.UsrRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class UserService {
    private final UsrRepository usrRepository;
    private final UserMapper userMapper;

    public Iterable<UserDto> getUsers() {
        return userMapper.toUserDTOList(
                usrRepository.findAll()
        );
    }

    public Usr getUser(Long id) {
        return usrRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + id));

    }

    public UserDto createUser(UserCreationDto userCreationDTO) {
        Usr userToCreate = userMapper.toUsr(userCreationDTO);

        return userMapper.toUserDTO(
                usrRepository.save(userToCreate)
        );
    }

    public UserDto updateUser(Long id, UserCreationDto userCreationDTO) {
        Usr userToUpdate = usrRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + id));

        Usr userData = userMapper.toUsr(userCreationDTO);

        userToUpdate.setUsername(userData.getUsername());
        userToUpdate.setEmail(userData.getEmail());
        userToUpdate.setPassword(userData.getPassword());
        userToUpdate.setIsAdmin(userData.getIsAdmin());
        userToUpdate.setUserType(userData.getUserType());

        return userMapper.toUserDTO(
                usrRepository.save(userToUpdate)
        );
    }

    public void deleteUser(Long id) {
        Usr user = usrRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + id));

        usrRepository.deleteById(user.getId());
    }
}
