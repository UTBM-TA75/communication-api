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
public class Communication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "communication_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usr sender;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Classroom classroom;

    private String title;

    private String body;

    private Timestamp sendDate;

    @ManyToOne
    @JoinColumn(name = "communication_type_id")
    private Communication_type type;

    @OneToMany(mappedBy = "communication")
    private List<Poll> comPollId;

    public Communication(int id, Usr sender, Classroom classroom, String title, String body, Timestamp sendDate, Communication_type type) {
        this.id = id;
        this.sender = sender;
        this.classroom = classroom;
        this.title = title;
        this.body = body;
        this.sendDate = sendDate;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usr getSender() {
        return sender;
    }

    public void setSender(Usr sender) {
        this.sender = sender;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Timestamp getSendDate() {
        return sendDate;
    }

    public void setSendDate(Timestamp sendDate) {
        this.sendDate = sendDate;
    }

    public Communication_type getType() {
        return type;
    }

    public void setType(Communication_type type) {
        this.type = type;
    }
}