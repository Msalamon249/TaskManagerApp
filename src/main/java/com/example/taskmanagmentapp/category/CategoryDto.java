package com.example.taskmanagmentapp.category;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CategoryDto {

    private Long id;
    @NotNull
    @Size(min = 5,max = 50)
    private String name;
    @NotNull
    @Size(max = 500)
    private String description;
}
