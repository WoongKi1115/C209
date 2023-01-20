package com.ssafy.youandi.entity.survey;

import javax.persistence.*;

@Entity
@Table(name = "survey")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "surveyid")
    private int surveyId;

    private String survey;
}
