package com.example.taskmanagmentapp.user;

import com.example.taskmanagmentapp.exceptions.CategoryDoNotExistException;
import com.example.taskmanagmentapp.exceptions.UserDoNotExistException;
import com.example.taskmanagmentapp.task.Task;
import com.example.taskmanagmentapp.task.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private static final int USER_SIZE_ON_PAGE = 5;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public Page<UserDto> findAllUsers(int page) {

        Page<User> allUsers = userRepository.findAll(PageRequest.of(page, USER_SIZE_ON_PAGE));
        return allUsers.map(UserMappers::mapToDto);
    }

    public UserDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserDoNotExistException::new);
        return UserMappers.mapToDto(user);
    }

    public UserDto addUser(UserDto userDto) {
        User user = UserMappers.mapToEntity(userDto);
        User savedUser = userRepository.save(user);
        return UserMappers.mapToDto(savedUser);
    }

    public User updateUser(Long id, UserDto userDto) {
        User toUpdate = userRepository.findById(id).orElseThrow(UserDoNotExistException::new);
        toUpdate.setUserName(userDto.getUserName());
        toUpdate.setPassword(userDto.getPassword());
        toUpdate.setEmail(userDto.getEmail());
        userRepository.save(toUpdate);
        return toUpdate;
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }


    public List<Task> getAllUserTasks(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserDoNotExistException::new);
        return user.getUserTasks();
    }

    public void assignTaskToUser(Long id, Long taskId) {
        User user = userRepository.findById(id).orElseThrow(UserDoNotExistException::new);
        Task task = taskRepository.findById(taskId).orElseThrow(CategoryDoNotExistException::new);

        user.addTask(task);
        userRepository.save(user);
    }

}
