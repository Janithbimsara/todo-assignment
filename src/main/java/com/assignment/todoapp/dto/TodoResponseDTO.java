package com.assignment.todoapp.dto;

import java.time.LocalDate;

public class TodoResponseDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private Boolean completed;

    public TodoResponseDTO(Long id, String title, String description, LocalDate dueDate, Boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.completed = completed;
    }

    // Getters and Setters
}
