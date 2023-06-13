package com.example.taskmanagmentapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Task with this ID do not exist")
public class TaskDoNotExistException extends RuntimeException{
}
