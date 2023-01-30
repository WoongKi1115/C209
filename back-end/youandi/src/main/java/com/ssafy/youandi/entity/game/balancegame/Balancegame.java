package com.ssafy.youandi.entity.game.balancegame;

import javax.persistence.*;

@Entity
@Table(name = "balancegame")
public class Balancegame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "balancegame_id")
    private long bgId;

    @Column(name = "bg_question1")
    private String bgQuestion1;

    @Column(name = "bg_question2")
    private String bgQuestion2;

}
