package fr.utbm.fisa.communicationapi.infrastructure.repositories;

import org.springframework.data.repository.CrudRepository;
import fr.utbm.fisa.communicationapi.infrastructure.entities.Group;

public interface GroupRepository extends CrudRepository<Group, Integer> {

}
