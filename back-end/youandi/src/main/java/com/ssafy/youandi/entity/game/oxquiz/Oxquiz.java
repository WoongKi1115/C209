package com.ssafy.youandi.entity.game.oxquiz;

import javax.persistence.*;

@Entity
@Table(name = "oxquiz")
public class Oxquiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ox_id")
    private long oxId;

    private String quiz;

    @Column(name = "istrue")
    private boolean isTrue;

    private String description;
}
