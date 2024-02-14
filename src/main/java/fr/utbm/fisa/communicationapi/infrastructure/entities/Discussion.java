package fr.utbm.fisa.communicationapi.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Discussion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user1")
    private Usr user1;

    @ManyToOne
    @JoinColumn(name = "user2")
    private Usr user2;

    @OneToMany(mappedBy = "discussion", cascade = CascadeType.ALL)
    private Set<Message> messages;
}