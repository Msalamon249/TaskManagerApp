package com.example.taskmanagmentapp.task;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
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
    @NotNull
    @Size(min = 5, max = 50)
    private String title;
    @NotNull
    @Size(max = 500)
    private String description;
    @NotNull
    @Size(max = 20)
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @FutureOrPresent
    private LocalDate endDate;

    private String categoryName;
    private String userName;
}
