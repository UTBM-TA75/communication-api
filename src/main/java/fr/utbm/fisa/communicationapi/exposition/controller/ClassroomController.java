package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Classroom;
import fr.utbm.fisa.communicationapi.infrastructure.entities.Pupil;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.ClassroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ClassroomController {
    private final ClassroomRepository classroomRepository;

    /**
     * Get all classrooms
     *
     * @return the list of classrooms
     */
    @GetMapping("/classrooms")
    public ResponseEntity<Iterable<Classroom>> getAllClassrooms() {
        return ResponseEntity.ok(classroomRepository.findAll());
    }

    /**
     * Get a classroom
     * @param id the classroom's id
     * @return the classroom
     */
    @GetMapping("/classrooms/{id}")
    public ResponseEntity<Classroom> getClassroom(@PathVariable Long id){
        Classroom classroom = classroomRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No classroom found with id " + id));

        return ResponseEntity.ok(classroom);
    }

    /**
     * Creates a classroom
     *
     * @param data the classroom's data
     * @return the created classroom
     */
    @PostMapping("/classrooms")
    public ResponseEntity<Classroom> createClassroom(@RequestBody Classroom data) {
        Classroom classroom = new Classroom();
        classroom.setIdLevel(data.getIdLevel());
        classroom.setPromotion(data.getPromotion());
        classroom.setCommunicationPerClassList(data.getCommunicationPerClassList());
        classroom.setClassUnderTeacher(data.getClassUnderTeacher());
        classroom.setPupilClasses(data.getPupilClasses());

        return new ResponseEntity<>(classroomRepository.save(classroom), HttpStatus.CREATED);
    }

    /**
     * Deletes a classroom
     *
     * @param id the classroom's id
     */
    @DeleteMapping("/classrooms/{id}")
    public void deleteClassroom(@PathVariable Long id) {
        classroomRepository.deleteById(id);
    }

    /**
     * Updates a classroom
     *
     * @param id the classroom's id
     * @return the updated classroom
     */
    @PutMapping("/classrooms/{id}")
    public ResponseEntity<Classroom> updateClassroom(@PathVariable Long id, @RequestBody Classroom data) {
        Classroom classroom = classroomRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No classroom found with the id " + id));

        classroom.setIdLevel(data.getIdLevel());
        classroom.setPromotion(data.getPromotion());
        classroom.setCommunicationPerClassList(data.getCommunicationPerClassList());
        classroom.setClassUnderTeacher(data.getClassUnderTeacher());
        classroom.setPupilClasses(data.getPupilClasses());

        classroomRepository.save(classroom);

        return ResponseEntity.ok(classroom);
    }
}
