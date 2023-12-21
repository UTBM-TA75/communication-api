package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Classroom;
import fr.utbm.fisa.communicationapi.infrastructure.entities.Pupil;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.ClassroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ClassroomController {
    private final ClassroomRepository classroomRepository;
    @GetMapping("/classrooms")
    public Iterable<Classroom> get() {
        return classroomRepository.findAll();
    }

    @PostMapping("/classrooms/create")
    public Classroom create(@RequestBody Classroom classroom) {
        Classroom classrm = new Classroom();
        classrm.setIdLevel(classroom.getIdLevel());
        classrm.setPromotion(classrm.getPromotion());
        classrm.setCommunicationPerClassList(classrm.getCommunicationPerClassList());
        classrm.setClassUnderTeacher(classrm.getClassUnderTeacher());
        classrm.setPupilClasses(classrm.getPupilClasses());
        return classroomRepository.save(classrm);
    }

    @GetMapping("/classrooms/delete")
    public void delete(@RequestBody Classroom classroom) {
        classroomRepository.deleteById(classroom.getId());
    }

    @PostMapping("/classrooms/edit")
    public void edit(@RequestBody Classroom classroom) {
        classroomRepository.findById(classroom.getId()).map(
                classrm -> {
                    classrm.setIdLevel(classroom.getIdLevel());
                    classrm.setPromotion(classrm.getPromotion());
                    classrm.setCommunicationPerClassList(classrm.getCommunicationPerClassList());
                    classrm.setClassUnderTeacher(classrm.getClassUnderTeacher());
                    classrm.setPupilClasses(classrm.getPupilClasses());
                    return classroomRepository.save(classrm);
                }
        );
    }
}
