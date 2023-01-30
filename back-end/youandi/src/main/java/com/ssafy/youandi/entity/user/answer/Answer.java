package com.ssafy.youandi.entity.user.answer;

import com.ssafy.youandi.entity.user.survey.Survey;
import com.ssafy.youandi.entity.user.User;

import javax.persistence.*;

@Entity
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private long answerId;

    private String answer;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="survey_id")
    private Survey survey;

    public Answer() {
    }
}
