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
public class Communication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    private Staff idSender;

    @ManyToOne
    private Classroom idClassroom;

    private String title;

    private String body;

    private Timestamp sendDate;

    @ManyToOne
    private Communication_type type;

    @OneToMany(mappedBy = "idCommunication")
    private List<Poll> PollList;
}