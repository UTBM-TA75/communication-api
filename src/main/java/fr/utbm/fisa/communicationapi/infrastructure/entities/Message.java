package fr.utbm.fisa.communicationapi.infrastructure.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

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
    private Usr sender;

    @ManyToOne
    private Discussion discussion;

    private String body;

    @Column(updatable = false)
    @CreationTimestamp
    private Timestamp sentAt;

    private Timestamp seenAt;

    private Boolean seen = false;
}
