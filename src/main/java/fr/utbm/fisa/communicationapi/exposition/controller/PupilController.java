package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Pupil;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.PupilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PupilController {
    private final PupilRepository pupilRepository;
    @GetMapping("/pupils")
    public Iterable<Pupil> get() {
        return pupilRepository.findAll();
    }

    @PostMapping("/pupils/create")
    public Pupil create(@RequestBody Pupil pupil) {
        Pupil pup = new Pupil();
        pup.setLastName(pupil.getLastName());
        pup.setFirstName(pupil.getFirstName());
        pup.setCreatedAt(pupil.getCreatedAt());
        pup.setUpdateAt(pupil.getUpdateAt());
        pup.setDeleteAt(pupil.getDeleteAt());
        pup.setStartDate(pupil.getStartDate());
        pup.setEndDate(pupil.getEndDate());
        pup.setCurrentPromotion(pupil.getCurrentPromotion());
        pup.setRegistration_Status(pupil.getRegistration_Status());
        return pupilRepository.save(pup);
    }

    @GetMapping("/pupils/delete")
    public void delete(@RequestBody Pupil pupil) {
        pupilRepository.deleteById(pupil.getId());
    }

    @PostMapping("/pupils/edit")
    public void edit(@RequestBody Pupil pupil) {
        pupilRepository.findById(pupil.getId()).map(
                pup -> {
                    pup.setLastName(pupil.getLastName());
                    pup.setFirstName(pupil.getFirstName());
                    pup.setCreatedAt(pupil.getCreatedAt());
                    pup.setUpdateAt(pupil.getUpdateAt());
                    pup.setDeleteAt(pupil.getDeleteAt());
                    pup.setStartDate(pupil.getStartDate());
                    pup.setEndDate(pupil.getEndDate());
                    pup.setCurrentPromotion(pupil.getCurrentPromotion());
                    pup.setRegistration_Status(pupil.getRegistration_Status());
                    return pupilRepository.save(pup);
                }
        );
    }
}
