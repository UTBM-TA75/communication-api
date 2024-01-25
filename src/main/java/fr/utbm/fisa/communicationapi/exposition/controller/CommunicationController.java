package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Communication;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.CommunicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommunicationController {
    private final CommunicationRepository communicationRepository;

    /**
     * Gets all the communications
     *
     * @return the list of communications
     */
    @GetMapping("/communications")
    public ResponseEntity<Iterable<Communication>> getAllCommunication() {
        return ResponseEntity.ok(communicationRepository.findAll());
    }

    /**
     * Create a communication
     *
     * @param data the communication's data
     * @return the created communication
     */
    @PostMapping("/communications")
    public ResponseEntity<Communication> createCommunication(@RequestBody Communication data) {
        Communication communication = new Communication();
        communication.setIdSender(data.getIdSender());
        communication.setIdClassroom(data.getIdClassroom());
        communication.setTitle(data.getTitle());
        communication.setBody(data.getBody());
        communication.setSendDate(data.getSendDate());
        communication.setType(data.getType());
        communication.setPollList(data.getPollList());

        communicationRepository.save(communication);

        return new ResponseEntity<>(communication, HttpStatus.CREATED);
    }

    /**
     * Deletes a communication
     *
     * @param id the communication's id
     */
    @DeleteMapping("/communications/{id}")
    public void delete(@PathVariable Long id) {
        Communication communication = communicationRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No communication found with id " + id));

        communicationRepository.deleteById(communication.getId());
    }

    /**
     * Updates a communication
     *
     * @param id   the communication's id
     * @param data the communication's updated data
     * @return the updated communication
     */
    @PutMapping("/communications/{id}")
    public ResponseEntity<Communication> edit(@PathVariable Long id, @RequestBody Communication data) {
        Communication communication = communicationRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No communication found with id " + id));

        communication.setIdSender(data.getIdSender());
        communication.setIdClassroom(data.getIdClassroom());
        communication.setTitle(data.getTitle());
        communication.setBody(data.getBody());
        communication.setSendDate(data.getSendDate());
        communication.setType(data.getType());
        communication.setPollList(data.getPollList());

        communicationRepository.save(communication);

        return ResponseEntity.ok(communication);
    }
}
