package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.infrastructure.entities.CommunicationType;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.CommunicationTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommunicationTypeController {
    private final CommunicationTypeRepository communicationTypeRepository;

    /**
     * Gets all the communication types
     *
     * @return the list of communication types
     */
    @GetMapping("/communications_types")
    public ResponseEntity<Iterable<CommunicationType>> getAllCommunicationTypes() {
        return ResponseEntity.ok(communicationTypeRepository.findAll());
    }

}
