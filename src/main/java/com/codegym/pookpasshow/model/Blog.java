package com.codegym.pookpasshow.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "blog", schema = "pookpasshow")
@Data
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String content;

    @ManyToOne
    private User user;
}
