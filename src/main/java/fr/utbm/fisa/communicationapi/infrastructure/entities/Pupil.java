package fr.utbm.fisa.communicationapi.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Getter
@Setter
public class Pupil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String lastName;

    private String firstName;

    private Timestamp createdAt;

    private Timestamp updateAt;

    private Timestamp deleteAt;

    private Timestamp startDate;

    private Timestamp endDate;

    private String currentPromotion;

    @Enumerated(EnumType.STRING)
    private RegistrationStatus registration_Status;

    @ManyToMany
    private Set<Parent> pupilUnderParent;

    @ManyToMany
    private Set<Classroom> classroomOfPupil;

    public enum RegistrationStatus{
        OnGoing,
        Accepted,
        Refused
    }
}
