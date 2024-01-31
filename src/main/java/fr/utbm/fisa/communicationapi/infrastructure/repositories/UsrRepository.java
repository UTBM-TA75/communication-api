package fr.utbm.fisa.communicationapi.infrastructure.repositories;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Usr;
import org.springframework.data.repository.CrudRepository;

public interface UsrRepository extends CrudRepository<Usr, Long> {
}