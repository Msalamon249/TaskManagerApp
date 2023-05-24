package com.example.taskmanagmentapp.category;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;


@RestController
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;


    @GetMapping
    public Page<Category> findAllCategories(@RequestParam int page) {
        int requestedPage = page < 0 ? 0 : page;
        return categoryService.findAllCategories(requestedPage);

    }

    @GetMapping()


    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        Category category1 = categoryService.addCategory(category);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category1.getId()).toUri();
        return ResponseEntity.created(uri).body(category1);
    }
}
