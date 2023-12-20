package fr.utbm.fisa.communicationapi.infrastructure.repositories;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Pupil;
import org.springframework.data.repository.CrudRepository;

public interface PupilRepository extends CrudRepository<Pupil, Long> {
}