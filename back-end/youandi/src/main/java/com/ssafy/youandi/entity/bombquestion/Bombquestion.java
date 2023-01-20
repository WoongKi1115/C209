package com.ssafy.youandi.entity.bombquestion;

import javax.persistence.*;

@Entity
@Table(name = "bombquestion")
public class Bombquestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questionid")
    private int questionId;

    private String question;
}
