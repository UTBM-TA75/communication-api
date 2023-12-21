package fr.utbm.fisa.communicationapi.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "communication")
public class Communication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private Staff idSender;

    @ManyToOne
    private ClassRoom idClassroom;

    private String title;

    private String body;

    private Timestamp sendDate;

    @ManyToOne
    private CommunicationType type;

    @OneToMany(mappedBy = "idCommunication")
    private Set<Poll> PollList;
}