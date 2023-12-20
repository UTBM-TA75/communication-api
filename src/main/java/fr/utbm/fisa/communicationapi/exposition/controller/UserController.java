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
    public User create(@RequestBody User user) {
        User u = new User();
        u.setName(user.getName());
        u.setEmail(user.getEmail());
        return userRepository.save(u);
    }

    @GetMapping("/users/delete")
    public void delete(@RequestBody User user) {
        userRepository.deleteById(user.getId());
    }
    @PostMapping("/users/edit")
    public void edit(@RequestBody User usr) {
        userRepository.findById(usr.getId()).map(
            user -> {
                user.setEmail(usr.getEmail());
                user.setName(usr.getName());
                return userRepository.save(user);
            }
        );
    }
}
