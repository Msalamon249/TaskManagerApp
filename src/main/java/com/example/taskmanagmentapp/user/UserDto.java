package com.example.taskmanagmentapp.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long userId;
    private String userName;
    private String email;
    private String password;
}
