package fr.utbm.fisa.communicationapi.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String description;

    private Timestamp closingDate;

    @ManyToOne
    private Communication idCommunication;

    @OneToMany(mappedBy = "idPoll")
    private List<PollAnswer> pollAnwserPerPollList;
}
