package fr.utbm.fisa.communicationapi.infrastructure.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "discussion_id")
    private Discussion discussionId;

    private String body;

    private Timestamp sentDate;

    private Boolean seen;

    private Timestamp seenDate;

}
