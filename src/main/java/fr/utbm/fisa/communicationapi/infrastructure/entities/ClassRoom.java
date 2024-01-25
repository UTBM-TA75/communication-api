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
@Table(name = "classroom")
public class ClassRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idLevel")
    private ClassLevel idLevel;

    private String promotion;

    @OneToMany(mappedBy = "idClassroom")
    private Set<Communication> communicationPerClassList;

    @ManyToMany
    private Set<Teacher> classUnderTeacher;

    @ManyToMany
    private Set<Pupil> pupilClasses;
}
