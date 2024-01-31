package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Parent;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ParentController {
    private final ParentRepository parentRepository;

    /**
     * Gets all parents
     *
     * @return the list of parents
     */
    @GetMapping("/parents")
    public ResponseEntity<Iterable<Parent>> getAllParents() {
        return ResponseEntity.ok(parentRepository.findAll());
    }

    /**
     * Gets a parent
     *
     * @param id the parent's id
     * @return the parent
     */
    @GetMapping("/parents/{id}")
    public ResponseEntity<Parent> getParent(@PathVariable Long id) {
        Parent parent = parentRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No parent found with id " + id));

        return ResponseEntity.ok(parent);
    }

    /**
     * Creates a parent
     *
     * @param data the parent's data
     * @return the created parent
     */
    @PostMapping("/parents")
    public ResponseEntity<Parent> createParent(@RequestBody Parent data) {
        Parent parent = new Parent();
        parent.setLastName(data.getLastName());
        parent.setFirstName(data.getFirstName());
        parent.setIdUser(data.getIdUser());

        parentRepository.save(parent);

        return new ResponseEntity<>(parent, HttpStatus.CREATED);
    }

    /**
     * Deletes a parent
     *
     * @param id the parent's id
     */
    @DeleteMapping("/parents/{id}")
    public void deleteParent(@PathVariable Long id) {
        Parent parent = parentRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No parent found with id " + id));

        parentRepository.deleteById(parent.getId());
    }

    /**
     * Updates a parent
     * @param id the parent's id
     * @param data the parent's updated data
     * @return the updated parent
     */
    @PutMapping("/parents/{id}")
    public ResponseEntity<Parent> editParent(@PathVariable Long id, @RequestBody Parent data) {
        Parent parent = parentRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No parent found with id " + id));

        parent.setLastName(data.getLastName());
        parent.setFirstName(data.getFirstName());

        parentRepository.save(parent);

        return ResponseEntity.ok(parent);
    }
}
