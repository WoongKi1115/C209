package com.ssafy.youandi.entity.keyword;

import javax.persistence.*;

@Entity
@Table(name = "keyword")
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_id")
    private long keywordId;

    private String keyword;
}
