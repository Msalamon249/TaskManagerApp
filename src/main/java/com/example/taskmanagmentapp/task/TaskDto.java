package com.example.taskmanagmentapp.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
@Builder
@AllArgsConstructor
@Getter
@Setter
public class TaskDto {


    private Long id;
    private String title;
    private String description;
    private Priority priority;
    private LocalDate endDate;
    private String categoryName;
    private String userName;
}
