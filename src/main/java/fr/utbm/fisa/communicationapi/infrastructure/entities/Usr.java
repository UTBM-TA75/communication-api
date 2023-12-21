package fr.utbm.fisa.communicationapi.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "usr")
public class Usr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String username;

    private String password;

    private String email;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Timestamp deletedAt;

    private Boolean isAdmin;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private String profilePicture;

    @OneToMany(mappedBy = "idSender")
    private Set<Message> senderList;

    @OneToMany(mappedBy = "idUser")
    private Set<Staff> communicationSenderList;

    @OneToMany(mappedBy = "user1")
    private Set<Discussion> discussionUser1List;

    @OneToMany(mappedBy = "user2")
    private Set<Discussion> discussionUser2List;

    @OneToMany(mappedBy = "idUser")
    private Set<Parent> userList;

    public enum UserType{
        Parent,
        Staff
    }
}
