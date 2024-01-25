package fr.utbm.fisa.communicationapi.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "communication_type")
public class CommunicationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "communication_type_id")
    private Long id;

    private String label;

    @OneToMany(mappedBy = "type")
    private Set<Communication> TypeCommunicationsList;

}
