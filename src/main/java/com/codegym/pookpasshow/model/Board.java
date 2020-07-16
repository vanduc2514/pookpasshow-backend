package com.codegym.pookpasshow.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "board", schema = "pookpasshow")
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private User user;

    @OneToMany
    private Set<Todo> todos;

    @OneToMany
    private Set<Board> boards;
}
