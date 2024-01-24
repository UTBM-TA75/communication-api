package fr.utbm.fisa.communicationapi.infrastructure.repositories;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Communication_type;
import org.springframework.data.repository.CrudRepository;

public interface CommunicationTypeRepository extends CrudRepository<Communication_type, Long> {
}
