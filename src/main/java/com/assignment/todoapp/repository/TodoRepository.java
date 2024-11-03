package com.assignment.todoapp.repository;

import com.assignment.todoapp.entity.Todo;
import com.assignment.todoapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Long> {
    List<Todo> findAllByOrderByDueDateAsc();
    List<Todo> findAllByUser(User user);
}
