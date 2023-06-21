package com.example.taskmanagmentapp.employee;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;


@Builder
@AllArgsConstructor
@Getter
@Setter
public class EmployeeDto {

    private Long id;
    @NotNull
    @Size(min = 5,max = 50)
    @UniqueElements
    private String userName;
    @NotNull
    @Size(max = 50)
    @UniqueElements
    private String email;
    @NotNull
    @Size(min = 5,max = 30)
    private String password;
}
