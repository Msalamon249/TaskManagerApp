package com.example.taskmanagmentapp.employee;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


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
