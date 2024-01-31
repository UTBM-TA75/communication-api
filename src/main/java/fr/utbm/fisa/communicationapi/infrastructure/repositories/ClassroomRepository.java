package fr.utbm.fisa.communicationapi.infrastructure.repositories;

import fr.utbm.fisa.communicationapi.infrastructure.entities.ClassRoom;
import org.springframework.data.repository.CrudRepository;

public interface ClassroomRepository extends CrudRepository<ClassRoom, Long> {
}
