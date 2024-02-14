package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.domain.dto.UserCreationDto;
import fr.utbm.fisa.communicationapi.domain.dto.UserDto;
import fr.utbm.fisa.communicationapi.infrastructure.mappers.UserMapper;
import fr.utbm.fisa.communicationapi.logic.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    /**
     * Fetch all users
     *
     * @return the list of users
     */
    @GetMapping("/users")
    public ResponseEntity<Iterable<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    /**
     * Fetch a specific user
     *
     * @param id the user's id
     * @return the user with the specific id | 404 if not found
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(
                userMapper.toUserDTO(userService.getUser(id))
        );
    }

    /**
     * Create a user with the given data
     *
     * @param data the user data
     * @return the created user
     */
    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserCreationDto data) {

        return new ResponseEntity<>(userService.createUser(data), HttpStatus.CREATED);
    }

    /**
     * Update a user by its id
     *
     * @param id   the user's id
     * @param data the user's updated data
     * @return the updated user
     */
    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable Long id,
            @RequestBody UserCreationDto data
    ) {
        return ResponseEntity.ok(userService.updateUser(id, data));
    }

    /**
     * Deletes a user by its id
     *
     * @param id the user's id
     */
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
