package fr.utbm.fisa.communicationapi.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "poll_answer")
public class PollAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private Poll idPoll;

    private String description;

    @ManyToMany
    private Set<Parent> parentHasAnswered;
}
