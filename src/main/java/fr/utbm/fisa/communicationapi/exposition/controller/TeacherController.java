package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Teacher;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherRepository teacherRepository;
    @GetMapping("/teachers")
    public Iterable<Teacher> get() {
        return teacherRepository.findAll();
    }

    @PostMapping("/teachers/create")
    public Teacher create(@RequestBody Teacher teacher) {
        Teacher teachr = new Teacher();
        teachr.setIdStaff(teacher.getIdStaff());
        teachr.setStartDate(teacher.getStartDate());
        teachr.setEndDate(teacher.getEndDate());
        teachr.setTeacherClasses(teacher.getTeacherClasses());
        return teacherRepository.save(teachr);
    }

    @GetMapping("/teachers/delete")
    public void delete(@RequestBody Teacher teacher) {
        teacherRepository.deleteById(teacher.getId());
    }

    @PostMapping("/teachers/edit")
    public void edit(@RequestBody Teacher teacher) {
        teacherRepository.findById(teacher.getId()).map(
                teachr -> {
                    teachr.setIdStaff(teacher.getIdStaff());
                    teachr.setStartDate(teacher.getStartDate());
                    teachr.setEndDate(teacher.getEndDate());
                    teachr.setTeacherClasses(teacher.getTeacherClasses());
                    return teacherRepository.save(teachr);
                }
        );
    }
}
