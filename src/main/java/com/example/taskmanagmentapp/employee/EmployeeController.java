package com.example.taskmanagmentapp.employee;

import com.example.taskmanagmentapp.task.Priority;
import com.example.taskmanagmentapp.task.Task;
import com.example.taskmanagmentapp.task.TaskDto;
import com.example.taskmanagmentapp.task.TaskMappers;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;


    @GetMapping
    public Page<EmployeeDto> findAllEmployees(@RequestParam int page) {
        int requestedPage = page >= 1 ? page : 0;
        return employeeService.findAllEmployees(requestedPage);
    }

    @GetMapping("/{id}")
    public EmployeeDto findById(@PathVariable Long id) {
        return employeeService.findById(id);
    }


    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        EmployeeDto createdEmployee = employeeService.addEmployee(employeeDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdEmployee.getId()).toUri();
        return ResponseEntity.created(uri).body(createdEmployee);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> editEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        Employee updated = employeeService.updateEmployee(id, employeeDto);
        EmployeeDto updatedDto = EmployeeMappers.mapToDto(updated);
        return ResponseEntity.ok(updatedDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Long id) {
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/{id}/tasks/{taskId}")
    public ResponseEntity<String> assignNewTaskToEmployee(@PathVariable Long id, @PathVariable Long taskId) {

        try {
            employeeService.assignTaskToEmployee(id, taskId);
            return ResponseEntity.ok("Task assigned to user successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error assigning task to user: " + e.getMessage());
        }
    }



    @DeleteMapping("/{id}/tasks/{taskId}")
    public ResponseEntity deleteEmployeeTask(@PathVariable Long id, @PathVariable Long taskId) {
        employeeService.deleteTaskFromEmployee(id, taskId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<TaskDto>> getAllEmployeeTasks(@PathVariable Long id) {
        List<TaskDto> allUserTasks = employeeService.getAllEmployeeTasks(id);
        return ResponseEntity.ok(allUserTasks);
    }


    @GetMapping("/{id}/tasks/filter")
    public List<TaskDto> filterEmployeeTask(@PathVariable Long id, @RequestParam(required = false) String categoryName, @RequestParam(required = false) String priority) {
        return employeeService.filterEmployeeTasksByCategoryAndPriority(id, categoryName, priority)
                .stream()
                .map(TaskMappers::mapToDto)
                .collect(Collectors.toList());
    }

}
