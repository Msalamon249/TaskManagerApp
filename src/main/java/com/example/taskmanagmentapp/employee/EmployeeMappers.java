package com.example.taskmanagmentapp.employee;

import com.example.taskmanagmentapp.employee.Employee;
import com.example.taskmanagmentapp.employee.EmployeeDto;

public class EmployeeMappers {


    public static EmployeeDto mapToDto(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .userName(employee.getUserName())
                .email(employee.getEmail())
                .password(employee.getPassword())
                .build();
    }

    public static Employee mapToEntity(EmployeeDto employeeDto) {
        return Employee.builder()
                .id(employeeDto.getId())
                .userName(employeeDto.getUserName())
                .password(employeeDto.getPassword())
                .email(employeeDto.getEmail())
                .build();
    }
}
