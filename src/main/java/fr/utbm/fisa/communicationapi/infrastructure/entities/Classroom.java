package fr.utbm.fisa.communicationapi.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "idClassroom")
    private List<Communication> communicationPerClassList;

    @ManyToMany
    private Set<Teacher> classUnderTeacher;

    @ManyToMany
    private Set<Pupil> pupilClasses;
}
