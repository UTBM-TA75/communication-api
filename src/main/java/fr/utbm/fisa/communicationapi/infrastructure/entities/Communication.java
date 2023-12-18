package fr.utbm.fisa.communicationapi.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class Communication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "communication_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    private String title;

    private String body;

    private Timestamp sendDate;

    @ManyToOne
    @JoinColumn(name = "communication_type_id")
    @Column(name = "type")
    private Communication_type type;
}