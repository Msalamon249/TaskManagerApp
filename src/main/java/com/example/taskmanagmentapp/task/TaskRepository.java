package com.example.taskmanagmentapp.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByEmployee_Id(Long id);
}
