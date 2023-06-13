package com.example.taskmanagmentapp.user;

import com.example.taskmanagmentapp.task.Task;
import com.example.taskmanagmentapp.task.TaskRepository;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "userix")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String email;
    private String password;
    @OneToMany
    private List<Task> userTasks;


    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public void addTask(Task task) {
        userTasks.add(task);
        task.setAssignee(this);
    }
}
