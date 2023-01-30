package com.ssafy.youandi.entity.game.liargame;

import javax.persistence.*;

@Entity
@Table(name = "liargame")
public class Liargame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lg_id")
    private long lgId;

    @Column(name = "lg_type")
    private String lgType;

    @Column(name = "lg_word")
    private String lgWord;
}
