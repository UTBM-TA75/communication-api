package fr.utbm.fisa.communicationapi.infrastructure.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usr idSender;

    @ManyToOne
    private Discussion idDiscussion;

    private String body;

    private Timestamp sentDate;

    private Boolean seen;

    private Timestamp seenDate;
}
