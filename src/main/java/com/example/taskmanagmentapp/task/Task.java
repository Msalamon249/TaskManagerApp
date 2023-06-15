package com.example.taskmanagmentapp.task;


import com.example.taskmanagmentapp.category.Category;
import com.example.taskmanagmentapp.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Enumerated
    private Priority priority;
    private LocalDate endDate;
    @ManyToOne
    private Category category;
    @ManyToOne
    private User assignee;


    public Task(String title, String description, Priority priority, LocalDate endDate) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.endDate = endDate;
    }
}
