package com.ssafy.youandi.entity.balancegame;

import javax.persistence.*;

@Entity
@Table(name = "balancegame")
public class Balancegame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bgid")
    private int bgId;

    @Column(name = "bgquestion1")
    private String bgQuestion1;

    @Column(name = "bgquestion2")
    private String bgQuestion2;

}
