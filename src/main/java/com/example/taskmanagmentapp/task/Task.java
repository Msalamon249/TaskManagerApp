package com.example.taskmanagmentapp.task;


import jakarta.annotation.Priority;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
//    @Enumerated
//    private Priority priority;
//    @Enumerated
//    private String status ;        //
    private LocalDate endDate;

    //assignee -> uzytownik przyspiany do wykonanai zadania
    //category -> kategoria do ktorej nalezy zadanie




//    public Task(String title, String description, Priority priority, String status, LocalDate endDate) {
//        this.title = title;
//        this.description = description;
//        this.priority = priority;
//        this.status = status;
//        this.endDate = endDate;
//    }

}
