package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.infrastructure.entities.User;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    @GetMapping("/users")
    public Iterable<User> get() {
        return userRepository.findAll();
    }

    @PostMapping("/users/create")
    public User create(@RequestParam String name,
                           @RequestParam String email) {
        User u = new User();
        u.setName(name);
        u.setEmail(email);
        return userRepository.save(u);
    }

    @GetMapping("/users/delete")
    public void delete(@RequestParam UUID id) {
        userRepository.deleteById(id);
    }

    @PostMapping("/users/edit")
    public void edit(@RequestParam UUID id,
                             @RequestParam String name,
                             @RequestParam String email) {
        userRepository.findById(id).map(
            user -> {
                user.setEmail(email);
                user.setName(name);
                return userRepository.save(user);
            }
        );
    }



}
