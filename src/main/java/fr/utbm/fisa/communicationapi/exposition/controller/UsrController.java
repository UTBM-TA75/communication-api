package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Usr;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.UsrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UsrController {
    private final UsrRepository usrRepository;

    /**
     * Fetch all users
     *
     * @return the list of users
     */
    @GetMapping("/users")
    public ResponseEntity<Iterable<Usr>> getAllUsers() {
        return ResponseEntity.ok(usrRepository.findAll());
    }

    /**
     * Fetch a specific user
     *
     * @param id the user's id
     * @return the user with the specific id | 404 if not found
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<Usr> getUser(@PathVariable Long id) {
        Usr user = usrRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + id));
        return ResponseEntity.ok(user);
    }

    /**
     * Create a user with the given data
     *
     * @param data the user data
     * @return the created user
     */
    @PostMapping("/users")
    public ResponseEntity<Usr> createUser(@RequestBody Usr data) {
        Usr user = new Usr();
        user.setUsername(data.getUsername());
        user.setPassword(data.getPassword());
        user.setEmail(data.getEmail());
        user.setIsAdmin(data.getIsAdmin());
        user.setUserType(data.getUserType());

        usrRepository.save(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    /**
     * Update a user by its id
     *
     * @param id   the user's id
     * @param data the user's updated data
     * @return the updated user
     */
    @PutMapping("/users/{id}")
    public ResponseEntity<Usr> updateUser(@PathVariable Long id, @RequestBody Usr data) {
        Usr user = usrRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + id));

        user.setUsername(data.getUsername());
        user.setPassword(data.getPassword());
        user.setEmail(data.getEmail());
        user.setIsAdmin(data.getIsAdmin());
        user.setUserType(data.getUserType());
        user.setProfilePicture(data.getProfilePicture());

        usrRepository.save(user);

        return ResponseEntity.ok(user);
    }

    /**
     * Deletes a user by its id
     *
     * @param id the user's id
     */
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        Usr user = usrRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + id));

        usrRepository.deleteById(user.getId());
    }
}
