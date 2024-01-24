package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.infrastructure.entities.ClassLevel;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.ClassLevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ClassLevelController {
    private final ClassLevelRepository classlevelRepository;

    /**
     * Gets all the class levels
     *
     * @return the list of class levels
     */
    @GetMapping("/class_levels")
    public ResponseEntity<Iterable<ClassLevel>> get() {
        return ResponseEntity.ok(classlevelRepository.findAll());
    }

    /**
     * Gets a class level
     *
     * @param id the class level's id
     * @return the class level
     */
    @GetMapping("/class_level/{id}")
    public ResponseEntity<ClassLevel> getClasslevel(@PathVariable Long id) {
        ClassLevel classLevel = classlevelRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No class level found with id " + id));

        return ResponseEntity.ok(classLevel);
    }

    /**
     * Creates a class level
     *
     * @param data the class level's data
     * @return the created class level
     */
    @PostMapping("/class_levels")
    public ResponseEntity<ClassLevel> create(@RequestBody ClassLevel data) {
        ClassLevel classLevel = new ClassLevel();
        classLevel.setName(data.getName());

        classlevelRepository.save(classLevel);

        return new ResponseEntity<>(classLevel, HttpStatus.CREATED);
    }

    /**
     * Deletes a class level
     *
     * @param id the class level's id
     */
    @DeleteMapping("/class_levels/{id}")
    public void delete(@PathVariable Long id) {
        ClassLevel classLevel = classlevelRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No class level found with id " + id));

        classlevelRepository.deleteById(classLevel.getId());
    }


    /**
     * Updates a class level
     *
     * @param id   the class level's id
     * @param data the class level's updated data
     * @return the updated class level
     */
    @PutMapping("/class_levels/{id}")
    public ResponseEntity<ClassLevel> edit(@PathVariable Long id, @RequestBody ClassLevel data) {
        ClassLevel classLevel = classlevelRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No class level found with id " + id));

        classLevel.setName(data.getName());
        classLevel.setIdLevelList(data.getIdLevelList());

        classlevelRepository.save(classLevel);

        return ResponseEntity.ok(classLevel);
    }
}