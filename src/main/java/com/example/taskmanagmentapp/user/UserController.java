package com.example.taskmanagmentapp.user;

import com.example.taskmanagmentapp.task.Task;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;


    @GetMapping
    public Page<UserDto> findAllUsers(@RequestParam int page) {
        return userService.findAllUsers(page);
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.addUser(userDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getUserId()).toUri();
        return ResponseEntity.created(uri).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> editUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        User updated = userService.updateUser(id, userDto);
        UserDto updatedDto = UserMappers.mapToDto(updated);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/tasks")
    public List<Task> getAllUserTasks(@PathVariable Long id) {
        return userService.getAllUserTasks(id);
    }

    @PostMapping("/{id}/tasks/{taskId}")
    public ResponseEntity<String> assignNewTaskToUser(@PathVariable Long id, @PathVariable Long taskId) {

        try {
            userService.assignTaskToUser(id, taskId);
            return ResponseEntity.ok("Task assigned to user successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error assigning task to user: " + e.getMessage());
        }
    }


}
