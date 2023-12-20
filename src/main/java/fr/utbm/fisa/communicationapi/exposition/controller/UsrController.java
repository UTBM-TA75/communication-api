package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Usr;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.UsrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UsrController {
    private final UsrRepository usrRepository;
    @GetMapping("/users")
    public Iterable<Usr> get() {
        return usrRepository.findAll();
    }

    @PostMapping("/users/create")
    public Usr create(@RequestBody Usr user) {
        Usr u = new Usr();
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        u.setCreatedAt(user.getCreatedAt());
        u.setUpdatedAt(user.getUpdatedAt());
        u.setDeletedAt(user.getDeletedAt());
        u.setIsAdmin(user.getIsAdmin());
        u.setUserType(user.getUserType());
        u.setProfilePicture(user.getProfilePicture());
        u.setSenderList(user.getSenderList());
        u.setCommunicationSenderList(user.getCommunicationSenderList());
        u.setDiscussionUser1List(user.getDiscussionUser1List());
        u.setDiscussionUser2List(user.getDiscussionUser2List());
        u.setUserList(user.getUserList());
        return usrRepository.save(u);
    }

    @GetMapping("/users/delete")
    public void delete(@RequestBody Usr user) {
        usrRepository.deleteById(user.getId());
    }

    @PostMapping("/users/edit")
    public void edit(@RequestBody Usr user) {
        usrRepository.findById(user.getId()).map(
            u -> {
                u.setUsername(user.getUsername());
                u.setPassword(user.getPassword());
                u.setEmail(user.getEmail());
                u.setCreatedAt(user.getCreatedAt());
                u.setUpdatedAt(user.getUpdatedAt());
                u.setDeletedAt(user.getDeletedAt());
                u.setIsAdmin(user.getIsAdmin());
                u.setUserType(user.getUserType());
                u.setProfilePicture(user.getProfilePicture());
                u.setSenderList(user.getSenderList());
                u.setCommunicationSenderList(user.getCommunicationSenderList());
                u.setDiscussionUser1List(user.getDiscussionUser1List());
                u.setDiscussionUser2List(user.getDiscussionUser2List());
                u.setUserList(user.getUserList());
                return usrRepository.save(u);
            }
        );
    }
}
