package com.ssafy.youandi.entity.liargame;

import javax.persistence.*;

@Entity
@Table(name = "liargame")
public class Liargame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lgid")
    private int lgId;

    @Column(name = "lgtype")
    private String lgType;

    @Column(name = "lgword")
    private String lgWord;
}
