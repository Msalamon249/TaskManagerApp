package com.example.taskmanagmentapp.task;

import com.example.taskmanagmentapp.exceptions.TaskDoNotExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;


    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public Task findTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(TaskDoNotExistException::new);
    }

    public Task addTask(Task task) {
        return taskRepository.save(task);
    }



    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }



}
