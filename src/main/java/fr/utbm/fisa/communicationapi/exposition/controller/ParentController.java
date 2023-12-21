package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Parent;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ParentController {
    private final ParentRepository parentRepository;
    @GetMapping("/parents")
    public Iterable<Parent> get() {
        return parentRepository.findAll();
    }

    @PostMapping("/parents/create")
    public Parent create(@RequestBody Parent parent) {
        Parent parnt = new Parent();
        parnt.setIdUser(parent.getIdUser());
        parnt.setLastName(parent.getLastName());
        parnt.setFirstName(parent.getFirstName());
        parnt.setCreatedAt(parent.getCreatedAt());
        parnt.setUpdateAt(parent.getUpdateAt());
        parnt.setDeleteAt(parent.getDeleteAt());
        parnt.setParentOfPupil(parent.getParentOfPupil());
        parnt.setUserAnswer(parent.getUserAnswer());
        return parentRepository.save(parnt);
    }

    @GetMapping("/parents/delete")
    public void delete(@RequestBody Parent parent) {
        parentRepository.deleteById(parent.getId());
    }

    @PostMapping("/parents/edit")
    public void edit(@RequestBody Parent parent) {
        parentRepository.findById(parent.getId()).map(
                parnt -> {
                    parnt.setIdUser(parent.getIdUser());
                    parnt.setLastName(parent.getLastName());
                    parnt.setFirstName(parent.getFirstName());
                    parnt.setCreatedAt(parent.getCreatedAt());
                    parnt.setUpdateAt(parent.getUpdateAt());
                    parnt.setDeleteAt(parent.getDeleteAt());
                    parnt.setParentOfPupil(parent.getParentOfPupil());
                    parnt.setUserAnswer(parent.getUserAnswer());
                    return parentRepository.save(parnt);
                }
        );
    }
}
