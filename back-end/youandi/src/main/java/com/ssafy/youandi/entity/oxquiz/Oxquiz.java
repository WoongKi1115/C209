package com.ssafy.youandi.entity.oxquiz;

import javax.persistence.*;

@Entity
@Table(name = "oxquiz")
public class Oxquiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oxid")
    private int oxId;

    private String quiz;

    @Column(name = "istrue")
    private boolean isTrue;

    private String description;
}
