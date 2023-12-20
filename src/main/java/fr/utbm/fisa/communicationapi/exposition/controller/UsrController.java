package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Usr;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.UsrRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.UUID;

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
        u.setEmail(user.getEmail());
        return usrRepository.save(u);
    }

    @GetMapping("/users/delete")
    public void delete(@RequestBody Usr user) {
        usrRepository.deleteById(user.getId());
    }
    @PostMapping("/users/edit")
    public void edit(@RequestBody Usr usr) {
        usrRepository.findById(usr.getId()).map(
            user -> {
                user.setEmail(usr.getEmail());
                user.setUsername(usr.getUsername());
                return usrRepository.save(user);
            }
        );
    }
}
