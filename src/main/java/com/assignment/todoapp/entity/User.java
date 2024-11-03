package com.assignment.todoapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;

    // Getters and Setters
}
