package fr.utbm.fisa.communicationapi.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idLevel")
    private Classlevel idLevel;

    private String promotion;

    @OneToMany(mappedBy = "idClass")
    private List<Teacher_classes> classList;

    @OneToMany(mappedBy = "idClassroom")
    private List<Communication> communicationPerClassList;

    @OneToMany(mappedBy = "idClass")
    private List<Pupil_classes> classPupilList;
}
