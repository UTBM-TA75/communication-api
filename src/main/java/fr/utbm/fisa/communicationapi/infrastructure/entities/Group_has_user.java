package fr.utbm.fisa.communicationapi.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Group_has_user {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usr userId;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Classroom classroomId;

}
