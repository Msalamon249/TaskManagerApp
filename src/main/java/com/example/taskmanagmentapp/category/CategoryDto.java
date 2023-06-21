package com.example.taskmanagmentapp.category;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

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
