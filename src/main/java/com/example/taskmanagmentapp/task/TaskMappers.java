package com.example.taskmanagmentapp.task;

import com.example.taskmanagmentapp.category.Category;
import com.example.taskmanagmentapp.category.CategoryRepository;
import com.example.taskmanagmentapp.employee.Employee;
import com.example.taskmanagmentapp.employee.EmployeeRepository;


public class TaskMappers {


    private final EmployeeRepository employeeRepository;
    private final CategoryRepository categoryRepository;

    public TaskMappers(EmployeeRepository employeeRepository, CategoryRepository categoryRepository) {
        this.employeeRepository = employeeRepository;
        this.categoryRepository = categoryRepository;
    }

    public static TaskDto mapToDto(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .endDate(task.getEndDate())
                .priority(task.getPriority())
                .categoryName(task.getCategory().getName())
                .userName(task.getEmployee().getUserName())
                .build();
    }


    public Task mapToEntity(TaskDto taskDto) {

        Category category = categoryRepository.findByNameIgnoreCase(taskDto.getCategoryName()).orElseThrow(IllegalArgumentException::new);
        Employee employee = employeeRepository.findByUserNameIgnoreCase(taskDto.getUserName()).orElseThrow(IllegalArgumentException::new);

        return Task.builder()
                .id(taskDto.getId())
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .endDate(taskDto.getEndDate())
                .priority(taskDto.getPriority())
                .category(category)
                .employee(employee)
                .build();
    }
}
