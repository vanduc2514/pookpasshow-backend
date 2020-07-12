package com.codegym.pookpasshow.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "todo", schema = "pookpasshow")
@Data
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "title", nullable = false)
    private String title;

    @Basic
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Basic
    @Column(name = "completed", nullable = false)
    private boolean completed = false;

    @Basic
    @Column(name = "user_id", nullable = false)
    private int userId;
}
