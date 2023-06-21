package com.example.taskmanagmentapp.employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Optional<Employee> findByUserNameIgnoreCase(String userName);

   
}

