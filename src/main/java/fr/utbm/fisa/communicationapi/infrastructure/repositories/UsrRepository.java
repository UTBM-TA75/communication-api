package fr.utbm.fisa.communicationapi.infrastructure.repositories;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Usr;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsrRepository extends CrudRepository<Usr, Long> {
    Optional<Usr> findByEmail(String email);
}