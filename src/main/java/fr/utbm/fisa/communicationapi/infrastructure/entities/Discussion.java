package fr.utbm.fisa.communicationapi.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Discussion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discussion_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user1_id")
    private Usr user1;

    @ManyToOne
    @JoinColumn(name = "user2_id")
    private Usr user2;

    @OneToMany(mappedBy = "discussionId")
    private List<Message> discussion;

    public Discussion(int id, Usr user1, Usr user2) {
        this.id = id;
        this.user1 = user1;
        this.user2 = user2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usr getUser1() {
        return user1;
    }

    public void setUser1(Usr user1) {
        this.user1 = user1;
    }

    public Usr getUser2() {
        return user2;
    }

    public void setUser2(Usr user2) {
        this.user2 = user2;
    }
}
