package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Communication_type;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.CommunicationTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommunicationTypeController {
    private final CommunicationTypeRepository communication_typeRepository;

    /**
     * Gets all the communication types
     *
     * @return the list of communication types
     */
    @GetMapping("/communications_types")
    public ResponseEntity<Iterable<Communication_type>> getAllCommunicationTypes() {
        return ResponseEntity.ok(communication_typeRepository.findAll());
    }

}
