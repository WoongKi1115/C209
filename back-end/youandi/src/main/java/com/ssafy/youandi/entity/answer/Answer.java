package com.ssafy.youandi.entity.answer;

import com.ssafy.youandi.entity.survey.Survey;
import com.ssafy.youandi.entity.user.User;

import javax.persistence.*;

@Entity
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answerid")
    private int answerId;

    private String answer;

    @ManyToOne
    @JoinColumn(name="uid")
    private User user;

    @ManyToOne
    @JoinColumn(name="surveyid")
    private Survey survey;

    public Answer() {
    }
}
