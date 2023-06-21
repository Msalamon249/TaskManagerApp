package com.example.taskmanagmentapp.task;

import com.example.taskmanagmentapp.category.CategoryRepository;
import com.example.taskmanagmentapp.employee.EmployeeRepository;
import com.example.taskmanagmentapp.employee.EmployeeService;
import com.example.taskmanagmentapp.exceptions.TaskDoNotExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;
    private final CategoryRepository categoryRepository;

    public List<TaskDto> findAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(TaskMappers::mapToDto)
                .collect(Collectors.toList());
    }

    public TaskDto findTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(TaskDoNotExistException::new);
        return TaskMappers.mapToDto(task);
    }

    public TaskDto addTask(TaskDto taskDto) {
        TaskMappers taskMappers = new TaskMappers(employeeRepository, categoryRepository);
        Task task = taskMappers.mapToEntity(taskDto);
        Task created = taskRepository.save(task);
        return TaskMappers.mapToDto(created);

    }


    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }


}
