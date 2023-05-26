package com.example.taskmanagmentapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Category not found")
public class CategoryDoNotExistException extends RuntimeException{
}
