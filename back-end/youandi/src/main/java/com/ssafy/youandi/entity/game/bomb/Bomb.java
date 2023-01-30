package com.ssafy.youandi.entity.game.bomb;

import javax.persistence.*;

@Entity
@Table(name = "bomb")
public class Bomb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bomb_id")
    private long bombId;

    @Column(name = "bomb_question")
    private String bombQuestion;
}
