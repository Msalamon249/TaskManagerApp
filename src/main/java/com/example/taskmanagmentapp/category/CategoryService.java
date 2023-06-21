package com.example.taskmanagmentapp.category;

import com.example.taskmanagmentapp.exceptions.CategoryDoNotExistException;
import com.example.taskmanagmentapp.task.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;


@Service
@AllArgsConstructor
public class CategoryService {
    private static final int SIZE_ON_PAGE = 5;
    private final CategoryRepository categoryRepository;
    private final TaskRepository taskRepository;


    public Page<CategoryDto> findAllCategories(int page) {

        PageRequest pageable = PageRequest.of(page, SIZE_ON_PAGE);
        return categoryRepository.findAll(pageable).map(CategoryMappers::maptoDto);
    }

    public CategoryDto findById(@PathVariable Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(CategoryDoNotExistException::new);
        return CategoryMappers.maptoDto(category);

    }

    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category toCreate = CategoryMappers.mapToEntity(categoryDto);
        Category created = categoryRepository.save(toCreate);
        return CategoryMappers.maptoDto(created);
    }

    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        Category categoryToUpdate = categoryRepository.findById(id).orElseThrow(CategoryDoNotExistException::new);
        categoryToUpdate.setName(categoryDto.getName());
        categoryToUpdate.setDescription(categoryDto.getDescription());
        Category saved = categoryRepository.save(categoryToUpdate);
        return CategoryMappers.maptoDto(saved);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
