package com.andras.entity;

import javax.persistence.*;

@Entity
public class Tags {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tag_id")
    private Integer tagId;

    @Column(name = "tag")
    private String tag;
}
