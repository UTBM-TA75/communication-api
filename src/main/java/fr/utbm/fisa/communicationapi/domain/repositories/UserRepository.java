package fr.utbm.fisa.communicationapi.domain.repositories;

import fr.utbm.fisa.communicationapi.infrastructure.db.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
}