package fr.utbm.fisa.communicationapi.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private Usr idUser;

    private String lastName;

    private String firstName;

    private Timestamp createdAt;

    private Timestamp updateAt;

    private Timestamp deleteAt;

    @OneToMany(mappedBy = "idParent")
    private List<User_answer> parentAnswerList;

    @OneToMany(mappedBy = "idParent")
    private List<Parent_children> parentChildrenList;
}
