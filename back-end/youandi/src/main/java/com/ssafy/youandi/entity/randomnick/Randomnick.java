package com.ssafy.youandi.entity.randomnick;

import javax.persistence.*;

@Entity
@Table(name = "randomnick")
public class Randomnick {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nickid")
    private int nickId;

    private String name;
}
