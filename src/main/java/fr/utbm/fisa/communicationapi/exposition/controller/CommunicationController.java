package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Communication;
import fr.utbm.fisa.communicationapi.infrastructure.entities.Pupil;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.CommunicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommunicationController {
    private final CommunicationRepository communicationRepository;
    @GetMapping("/communications")
    public Iterable<Communication> get() {
        return communicationRepository.findAll();
    }

    @PostMapping("/communications/create")
    public Communication create(@RequestBody Communication communication) {
        Communication comm = new Communication();
        comm.setIdSender(communication.getIdSender());
        comm.setIdClassroom(communication.getIdClassroom());
        comm.setTitle(communication.getTitle());
        comm.setBody(communication.getBody());
        comm.setSendDate(communication.getSendDate());
        comm.setType(communication.getType());
        comm.setPollList(communication.getPollList());
        return communicationRepository.save(comm);
    }

    @GetMapping("/communications/delete")
    public void delete(@RequestBody Communication communication) {
        communicationRepository.deleteById(communication.getId());
    }

    @PostMapping("/communications/edit")
    public void edit(@RequestBody Communication communication) {
        communicationRepository.findById(communication.getId()).map(
                comm -> {
                    comm.setIdSender(communication.getIdSender());
                    comm.setIdClassroom(communication.getIdClassroom());
                    comm.setTitle(communication.getTitle());
                    comm.setBody(communication.getBody());
                    comm.setSendDate(communication.getSendDate());
                    comm.setType(communication.getType());
                    comm.setPollList(communication.getPollList());
                    return communicationRepository.save(comm);
                }
        );
    }
}
