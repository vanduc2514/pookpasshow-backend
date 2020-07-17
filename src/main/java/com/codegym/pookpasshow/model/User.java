package com.codegym.pookpasshow.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user", schema = "pookpasshow")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    private String email;

    private String phone;

    @OneToMany
    private Set<Todo> todos;

    @OneToMany
    private Set<Blog> blogs;
}
