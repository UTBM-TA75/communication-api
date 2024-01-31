package fr.utbm.fisa.communicationapi.infrastructure.repositories;

import fr.utbm.fisa.communicationapi.infrastructure.entities.CommunicationType;
import org.springframework.data.repository.CrudRepository;

public interface CommunicationTypeRepository extends CrudRepository<CommunicationType, Long> {
}
