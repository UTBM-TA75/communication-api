package fr.utbm.fisa.communicationapi.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "usr")
public class Usr implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, length = 100, nullable = false)
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public enum UserType{
        Parent,
        Staff
    }
}
