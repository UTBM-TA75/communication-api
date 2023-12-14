package fr.utbm.fisa.communicationapi.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Poll_answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "poll_answer_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;

    private String description;

}
