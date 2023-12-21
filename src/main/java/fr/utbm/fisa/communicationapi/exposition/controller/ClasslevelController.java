package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Classlevel;
import fr.utbm.fisa.communicationapi.infrastructure.entities.Pupil;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.ClasslevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ClasslevelController {
    private final ClasslevelRepository classlevelRepository;
    @GetMapping("/classlevels")
    public Iterable<Classlevel> get() {
        return classlevelRepository.findAll();
    }

    @PostMapping("/classlevels/create")
    public Classlevel create(@RequestBody Classlevel classlevel) {
        Classlevel classlvl = new Classlevel();
        classlvl.setName(classlevel.getName());
        classlvl.setIdLevelList(classlevel.getIdLevelList());
        return classlevelRepository.save(classlvl);
    }

    @GetMapping("/classlevels/delete")
    public void delete(@RequestBody Classlevel classlevel) {
        classlevelRepository.deleteById(classlevel.getId());
    }

    @PostMapping("/classlevels/edit")
    public void edit(@RequestBody Classlevel classlevel) {
        classlevelRepository.findById(classlevel.getId()).map(
                classlvl -> {
                    classlvl.setName(classlevel.getName());
                    classlvl.setIdLevelList(classlevel.getIdLevelList());
                    return classlevelRepository.save(classlvl);
                }
        );
    }
}