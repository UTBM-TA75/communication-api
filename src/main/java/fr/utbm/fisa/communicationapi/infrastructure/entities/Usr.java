package fr.utbm.fisa.communicationapi.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
    private List<Message> senderList;

    @OneToMany(mappedBy = "idUser")
    private List<Staff> communicationSenderList;

    @OneToMany(mappedBy = "user1")
    private List<Discussion> discussionUser1List;

    @OneToMany(mappedBy = "user2")
    private List<Discussion> discussionUser2List;

    @OneToMany(mappedBy = "idUser")
    private List<Parent> userList;

    public enum UserType{
        Parent,
        Staff
    }
}
