package com.ssafy.youandi.entity.randomnick;

import javax.persistence.*;

@Entity
@Table(name = "randomnick")
public class Randomnick {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nick_id")
    private long nickId;

    @Column(name = "random_nickname")
    private String randomNickname;
}
