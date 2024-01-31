package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Pupil;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.PupilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PupilController {
    private final PupilRepository pupilRepository;

    /**
     * Gets all the pupils
     *
     * @return the list of pupils
     */
    @GetMapping("/pupils")
    public ResponseEntity<Iterable<Pupil>> getAllPupils() {
        return ResponseEntity.ok(pupilRepository.findAll());
    }

    /**
     * Gets a pupil
     *
     * @param id the pupil's id
     * @return the pupil
     */
    @GetMapping("/pupils/{id}")
    public ResponseEntity<Pupil> getPupil(@PathVariable Long id) {
        Pupil pupil = pupilRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No pupil found with id " + id));

        return ResponseEntity.ok(pupil);
    }

    /**
     * Creates a pupil
     *
     * @param data the pupil's data
     * @return the created pupil
     */
    @PostMapping("/pupils")
    public ResponseEntity<Pupil> createPupil(@RequestBody Pupil data) {
        Pupil pupil = new Pupil();
        pupil.setLastName(data.getLastName());
        pupil.setFirstName(data.getFirstName());
        pupil.setStartDate(data.getStartDate());
        pupil.setEndDate(data.getEndDate());
        pupil.setCurrentPromotion(data.getCurrentPromotion());
        pupil.setRegistration_Status(data.getRegistration_Status());

        pupilRepository.save(pupil);

        return new ResponseEntity<>(pupil, HttpStatus.CREATED);
    }

    /**
     * Deletes a pupil
     *
     * @param id the pupil's id
     */
    @DeleteMapping("/pupils/{id}")
    public void deletePupil(@PathVariable Long id) {
        Pupil pupil = pupilRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No pupil found with id " + id));

        pupilRepository.deleteById(pupil.getId());
    }

    /**
     * Updates a pupil
     *
     * @param id   the pupil's id
     * @param data the pupil's updated data
     * @return the updated pupil
     */
    @PutMapping("/pupils/{id}")
    public ResponseEntity<Pupil> editPupil(@PathVariable Long id, @RequestBody Pupil data) {
        Pupil pupil = pupilRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No pupil found with id " + id));

        pupil.setLastName(data.getLastName());
        pupil.setFirstName(data.getFirstName());
        pupil.setStartDate(data.getStartDate());
        pupil.setEndDate(data.getEndDate());
        pupil.setCurrentPromotion(data.getCurrentPromotion());
        pupil.setRegistration_Status(data.getRegistration_Status());

        pupilRepository.save(pupil);

        return ResponseEntity.ok(pupil);
    }
}
