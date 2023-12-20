package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Group;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GroupController {
    private final GroupRepository groupRepository;

    @GetMapping("/groups")
    public Iterable<Group> get() {
        return groupRepository.findAll();
    }

    @PostMapping("/groups/create")
    public Group create(@RequestParam Long id,
                        @RequestParam String label) {
        Group g = new Group();
        g.setId(id);
        g.setLabel(label);
        return groupRepository.save(g);
    }

    @GetMapping("/groups/delete")
    public void delete(@RequestParam Long id) {
        groupRepository.deleteById(id);
    }

    @PostMapping("/groups/edit")
    public void edit(@RequestParam Long id,
                     @RequestParam String label) {
        groupRepository.findById(id).map(
                group -> {
                    group.setId(id);
                    group.setLabel(label);
                    return groupRepository.save(group);
                }
        );
    }
}