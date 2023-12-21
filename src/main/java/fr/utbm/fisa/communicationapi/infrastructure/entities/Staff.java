package fr.utbm.fisa.communicationapi.infrastructure.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private Usr idUser;

    private String lastName;

    private String firstName;

    @OneToMany(mappedBy = "idSender")
    private Set<Communication> senderList;

    @OneToMany(mappedBy = "idStaff")
    private Set<Teacher> teacherList;
}
