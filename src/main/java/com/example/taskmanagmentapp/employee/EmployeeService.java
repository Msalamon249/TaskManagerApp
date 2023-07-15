package com.example.taskmanagmentapp.employee;

import com.example.taskmanagmentapp.exceptions.CategoryDoNotExistException;
import com.example.taskmanagmentapp.exceptions.EmployeeDoNotExistException;
import com.example.taskmanagmentapp.task.*;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService {
    private static final int USER_SIZE_ON_PAGE = 5;
    private final EmployeeRepository employeeRepository;
    private final TaskRepository taskRepository;

    public Page<EmployeeDto> findAllEmployees(int page) {

        Page<Employee> allUsers = employeeRepository.findAll(PageRequest.of(page, USER_SIZE_ON_PAGE));
        return allUsers.map(EmployeeMappers::mapToDto);
    }

    public EmployeeDto findById(Long id) {
        Employee user = employeeRepository.findById(id).orElseThrow(EmployeeDoNotExistException::new);
        return EmployeeMappers.mapToDto(user);
    }

    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        Employee user = EmployeeMappers.mapToEntity(employeeDto);
        Employee savedUser = employeeRepository.save(user);
        return EmployeeMappers.mapToDto(savedUser);
    }

    public Employee updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee toUpdate = employeeRepository.findById(id).orElseThrow(EmployeeDoNotExistException::new);
        toUpdate.setUserName(employeeDto.getUserName());
        toUpdate.setPassword(employeeDto.getPassword());
        toUpdate.setEmail(employeeDto.getEmail());
        employeeRepository.save(toUpdate);
        return toUpdate;
    }

    public void deleteById(Long id) {
        Optional<Employee> byId = employeeRepository.findById(id);
        if (byId.isPresent()) {
            Employee employee = byId.get();
            employee.getEmployeeTasks()
                    .forEach(task -> task.setEmployee(null));
        }
        employeeRepository.deleteById(id);

    }


    public List<TaskDto> getAllEmployeeTasks(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(EmployeeDoNotExistException::new);
        return employee.getEmployeeTasks()
                .stream()
                .map(TaskMappers::mapToDto)
                .collect(Collectors.toList());
    }

    public void assignTaskToEmployee(Long id, Long taskId) {
        Employee employee = employeeRepository.findById(id).orElseThrow(EmployeeDoNotExistException::new);
        Task task = taskRepository.findById(taskId).orElseThrow(CategoryDoNotExistException::new);
        if (task.getEmployee() != null) {
            throw new IllegalArgumentException("Podane zadanie jest juz przydzielone");
        } else {
            employee.addTask(task);
            employeeRepository.save(employee);
        }
    }

    @Transactional
    public void deleteTaskFromEmployee(Long id, Long taskId) {
        Employee employee = employeeRepository.findById(id).orElseThrow(EmployeeDoNotExistException::new);
        Task task = taskRepository.findById(taskId).orElseThrow(CategoryDoNotExistException::new);
        employee.deleteTask(task);
    }

    public List<Task> filterEmployeeTasksByCategoryAndPriority(Long id, String categoryName, String priority) {
        Employee employee = employeeRepository.findById(id).orElseThrow(EmployeeDoNotExistException::new);
        List<Task> userTasks = employee.getEmployeeTasks();

        if (categoryName != null && priority != null) {
            return userTasks.stream()
                    .filter(task -> task.getCategory().getName().equals(categoryName) && task.getPriority().name().equals(priority))
                    .collect(Collectors.toList());
        } else if (categoryName != null) {

            return userTasks.stream()
                    .filter(task -> task.getCategory().getName().equals(categoryName))
                    .collect(Collectors.toList());

        } else if (priority != null) {

            return userTasks.stream()
                    .filter(task -> task.getPriority().name().equals(priority))
                    .collect(Collectors.toList());

        } else {

            return userTasks;
        }
    }
}
