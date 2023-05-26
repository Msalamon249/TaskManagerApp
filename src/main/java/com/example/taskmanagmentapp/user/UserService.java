package com.example.taskmanagmentapp.user;

import com.example.taskmanagmentapp.exceptions.UserDoNotExistException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private static final int USER_SIZE_ON_PAGE = 5;
    private final UserRepository userRepository;

    public Page<User> findAllUsers(int page) {

        return userRepository.findAll(PageRequest.of(page, USER_SIZE_ON_PAGE));
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(UserDoNotExistException::new);
    }

    public UserDto addUser(UserDto userDto) {
        User user = UserMappers.mapToEntity(userDto);
        User savedUser = userRepository.save(user);
        return UserMappers.mapToDto(savedUser);
    }

    public User updateUser(Long id, User user) {
        User toUpdate = userRepository.findById(id).orElseThrow(UserDoNotExistException::new);
        toUpdate.setUserName(user.getUserName());
        toUpdate.setPassword(user.getPassword());
        toUpdate.setEmail(user.getEmail());
        return toUpdate;
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

}
