package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.domain.dto.UserCreationDTO;
import fr.utbm.fisa.communicationapi.domain.dto.UserDTO;
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

    /**
     * Fetch all users
     *
     * @return the list of users
     */
    @GetMapping("/users")
    public ResponseEntity<Iterable<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    /**
     * Fetch a specific user
     *
     * @param id the user's id
     * @return the user with the specific id | 404 if not found
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    /**
     * Create a user with the given data
     *
     * @param data the user data
     * @return the created user
     */
    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserCreationDTO data) {

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
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable Long id,
            @RequestBody UserCreationDTO data
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
