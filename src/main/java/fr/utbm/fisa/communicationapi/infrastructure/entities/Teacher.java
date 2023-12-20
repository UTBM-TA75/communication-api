package fr.utbm.fisa.communicationapi.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private Staff idStaff;

    private Timestamp startDate;

    private Timestamp endDate;

    @OneToMany(mappedBy = "idTeacher")
    private List<Teacher_classes> teacherList;
}
