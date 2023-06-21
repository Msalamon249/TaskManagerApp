package com.example.taskmanagmentapp.employee;


import com.example.taskmanagmentapp.task.Task;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String email;
    private String password;
    @OneToMany(mappedBy = "employee")
    private List<Task> employeeTasks;


    public Employee(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public void addTask(Task task) {

        employeeTasks.add(task);
        task.setEmployee(this);
    }

    public void deleteTask(Task task){
        employeeTasks.remove(task);
        task.setEmployee(null);
    }


}
