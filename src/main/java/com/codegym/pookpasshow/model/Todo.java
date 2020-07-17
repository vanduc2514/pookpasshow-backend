package com.codegym.pookpasshow.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "todo", schema = "pookpasshow")
@Data
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Size(min = 5)
    @Column(name = "title", nullable = false)
    private String title;

    @Basic
    @Size(min = 10)
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Basic
    @Column(name = "completed", nullable = false)
    private boolean completed = false;
}
