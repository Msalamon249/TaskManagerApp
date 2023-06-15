package com.example.taskmanagmentapp.task;

import com.example.taskmanagmentapp.category.Category;
import com.example.taskmanagmentapp.category.CategoryRepository;
import com.example.taskmanagmentapp.user.User;
import com.example.taskmanagmentapp.user.UserRepository;


public class TaskMappers {


    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public TaskMappers(UserRepository userRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
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
                .userName(task.getAssignee().getUserName())
                .build();
    }


    public Task mapToEntity(TaskDto taskDto) {

        Category category = categoryRepository.findByNameIgnoreCase(taskDto.getCategoryName()).orElseThrow(IllegalArgumentException::new);
        User user = userRepository.findByUserNameIgnoreCase(taskDto.getUserName()).orElseThrow(IllegalArgumentException::new);

        return Task.builder()
                .id(taskDto.getId())
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .endDate(taskDto.getEndDate())
                .priority(taskDto.getPriority())
                .category(category)
                .assignee(user)
                .build();
    }
}
