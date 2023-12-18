package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.infrastructure.entities.CommunicationType;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.CommunicationTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommunicationTypeController {
    private final CommunicationTypeRepository communicationTypeRepository;
    @GetMapping("/communicationTypes")
    public Iterable<CommunicationType> get() {
        return communicationTypeRepository.findAll();
    }
    @PostMapping("/communicationTypes/create")
    public CommunicationType create(@RequestParam Integer id,
                        @RequestParam String label) {
        CommunicationType ct = new CommunicationType();
        ct.setId(id);
        ct.setLabel(label);
        return communicationTypeRepository.save(ct);
    }
    @GetMapping("/communicationTypes/delete")
    public void delete(@RequestParam Integer id) {
        communicationTypeRepository.deleteById(id);
    }
    @PostMapping("/communicationTypes/edit")
    public void edit(@RequestParam Integer id,
                     @RequestParam String label) {
        communicationTypeRepository.findById(id).map(
                ct -> {
                    ct.setId(id);
                    ct.setLabel(label);
                    return communicationTypeRepository.save(ct);
                }
        );
    }
}
