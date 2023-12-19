package fr.utbm.fisa.communicationapi.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class User_answer {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usr user;

    @ManyToOne
    @JoinColumn(name = "poll_answer_id")
    private Poll_answer answer;

    public User_answer(Usr user, Poll_answer answer) {
        this.user = user;
        this.answer = answer;
    }

    public Usr getUser() {
        return user;
    }

    public void setUser(Usr user) {
        this.user = user;
    }

    public Poll_answer getAnswer() {
        return answer;
    }

    public void setAnswer(Poll_answer answer) {
        this.answer = answer;
    }
}
