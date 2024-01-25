package fr.utbm.fisa.communicationapi.exposition.specifications.event;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Event;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Date;
import java.time.LocalDate;

@AllArgsConstructor
public class EventStartsAfterSpecification implements Specification<Event> {
    private Date startsAfterInterval;

    @Override
    public Predicate toPredicate(Root<Event> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (this.startsAfterInterval == null) {
            this.startsAfterInterval = Date.valueOf(LocalDate.now());
        }
        return criteriaBuilder.greaterThanOrEqualTo(root.get("beginning"), this.startsAfterInterval);
    }
}
