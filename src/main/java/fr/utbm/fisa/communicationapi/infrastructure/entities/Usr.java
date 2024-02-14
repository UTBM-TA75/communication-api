package fr.utbm.fisa.communicationapi.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(insertable = false)
    private Timestamp updatedAt;

    private Timestamp deletedAt;

    @Column(nullable = false)
    private Boolean isAdmin;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    private String profilePicture;

    @OneToMany(mappedBy = "sender")
    private Set<Message> senderList;

    @OneToMany(mappedBy = "idUser")
    private Set<Staff> communicationSenderList;

    @OneToMany(mappedBy = "user1")
    private Set<Discussion> discussionUser1List;

    @OneToMany(mappedBy = "user2")
    private Set<Discussion> discussionUser2List;

    @OneToMany(mappedBy = "idUser")
    private Set<Parent> userList;
}
