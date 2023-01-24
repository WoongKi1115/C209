package com.ssafy.youandi.entity.bomb;

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
