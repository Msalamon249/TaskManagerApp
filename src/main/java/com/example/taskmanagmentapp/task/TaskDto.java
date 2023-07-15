package com.example.taskmanagmentapp.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
