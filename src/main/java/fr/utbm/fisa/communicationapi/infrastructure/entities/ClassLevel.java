package fr.utbm.fisa.communicationapi.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "class_level")
public class ClassLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "idLevel")
    private List<ClassRoom> idLevelList;
}
