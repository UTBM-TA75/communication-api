package fr.utbm.fisa.communicationapi.infrastructure.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usr userId;

    @ManyToOne
    @JoinColumn(name = "discussion_id")
    private Discussion discussionId;

    private String body;

    private Timestamp sentDate;

    private Boolean seen;

    private Timestamp seenDate;

    public Message(int id, Usr userId, Discussion discussionId, String body, Timestamp sentDate, Boolean seen, Timestamp seenDate) {
        this.id = id;
        this.userId = userId;
        this.discussionId = discussionId;
        this.body = body;
        this.sentDate = sentDate;
        this.seen = seen;
        this.seenDate = seenDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usr getUserId() {
        return userId;
    }

    public void setUserId(Usr userId) {
        this.userId = userId;
    }

    public Discussion getDiscussionId() {
        return discussionId;
    }

    public void setDiscussionId(Discussion discussionId) {
        this.discussionId = discussionId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Timestamp getSentDate() {
        return sentDate;
    }

    public void setSentDate(Timestamp sentDate) {
        this.sentDate = sentDate;
    }

    public Boolean getSeen() {
        return seen;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
    }

    public Timestamp getSeenDate() {
        return seenDate;
    }

    public void setSeenDate(Timestamp seenDate) {
        this.seenDate = seenDate;
    }
}
