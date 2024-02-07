package fr.utbm.fisa.communicationapi.infrastructure.repositories;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Communication;
import fr.utbm.fisa.communicationapi.infrastructure.entities.CommunicationType;
import org.springframework.data.repository.CrudRepository;

public interface CommunicationRepository extends CrudRepository<Communication, Long> {
    public Iterable<Communication> findCommunicationsByType(CommunicationType type);
}
