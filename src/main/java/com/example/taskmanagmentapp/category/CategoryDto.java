package com.example.taskmanagmentapp.category;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CategoryDto {

    private Long id;
    private String name;
    private String description;
}
