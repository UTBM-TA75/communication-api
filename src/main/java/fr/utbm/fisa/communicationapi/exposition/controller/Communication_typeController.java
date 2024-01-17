package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Communication_type;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.Communication_typeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class Communication_typeController {
    private final Communication_typeRepository communication_typeRepository;
    @GetMapping("/communications_types")
    public Iterable<Communication_type> get() {
        return communication_typeRepository.findAll();
    }

}
