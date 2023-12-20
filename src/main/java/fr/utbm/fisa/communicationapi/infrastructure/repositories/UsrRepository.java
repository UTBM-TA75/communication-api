package fr.utbm.fisa.communicationapi.infrastructure.repositories;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Usr;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UsrRepository extends CrudRepository<Usr, Long> {
}