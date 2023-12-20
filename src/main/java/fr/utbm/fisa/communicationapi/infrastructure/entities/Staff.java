package fr.utbm.fisa.communicationapi.infrastructure.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

import java.util.List;

@Entity
@Getter
@Setter
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
    private List<Communication> senderList;

    @OneToMany(mappedBy = "idStaff")
    private List<Teacher> teacherList;
}
