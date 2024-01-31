package fr.utbm.fisa.communicationapi.exposition.specifications.event;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Event;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Date;

@AllArgsConstructor
public class EventStartsBeforeSpecification implements Specification<Event> {
    private Date startsBeforeInterval;

    @Override
    public Predicate toPredicate(Root<Event> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (this.startsBeforeInterval == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // always true, no filtering
        }
        return criteriaBuilder.lessThanOrEqualTo(root.get("beginning"), this.startsBeforeInterval);
    }
}
