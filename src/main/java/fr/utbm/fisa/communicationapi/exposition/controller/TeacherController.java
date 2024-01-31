package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Teacher;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherRepository teacherRepository;

    /**
     * Gets all teachers
     *
     * @return the list of teachers
     */
    @GetMapping("/teachers")
    public ResponseEntity<Iterable<Teacher>> get() {
        return ResponseEntity.ok(teacherRepository.findAll());
    }

    /**
     * Gets a teacher
     *
     * @param id the teacher's id
     * @return the teacher
     */
    @GetMapping("/teachers/{id}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable Long id) {
        Teacher teacher = teacherRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No teacher found with id " + id));

        return ResponseEntity.ok(teacher);
    }

    /**
     * Creates a teacher
     *
     * @param data the teacher's data
     * @return the created teacher
     */
    @PostMapping("/teachers")
    public ResponseEntity<Teacher> create(@RequestBody Teacher data) {
        Teacher teacher = new Teacher();
        teacher.setIdStaff(data.getIdStaff());
        teacher.setStartDate(data.getStartDate());
        teacher.setEndDate(data.getEndDate());

        teacherRepository.save(teacher);

        return new ResponseEntity<>(teacher, HttpStatus.CREATED);
    }


    @DeleteMapping("/teachers/{id}")
    public void delete(@PathVariable Long id) {
        Teacher teacher = teacherRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No teacher found with id " + id));

        teacherRepository.deleteById(teacher.getId());
    }

    @PutMapping("/teachers/{id}")
    public ResponseEntity<Teacher> edit(@PathVariable Long id, @RequestBody Teacher data) {
        Teacher teacher = teacherRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No teacher found with id " + id));

        teacher.setStartDate(data.getStartDate());
        teacher.setEndDate(data.getEndDate());

        teacherRepository.save(teacher);

        return ResponseEntity.ok(teacher);
    }
}
