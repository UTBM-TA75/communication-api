package fr.utbm.fisa.communicationapi.infrastructure.repositories;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Staff;
import org.springframework.data.repository.CrudRepository;

public interface StaffRepository extends CrudRepository<Staff, Long> {
}
