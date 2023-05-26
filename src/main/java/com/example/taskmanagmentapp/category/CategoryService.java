package com.example.taskmanagmentapp.category;

import com.example.taskmanagmentapp.exceptions.CategoryDoNotExistException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@AllArgsConstructor
public class CategoryService {
    private static final int SIZE_ON_PAGE = 3;
    private final CategoryRepository categoryRepository;


    public Page<Category> findAllCategories(int page) {

        PageRequest pageable = PageRequest.of(page, SIZE_ON_PAGE);
        return categoryRepository.findAll(pageable);
    }

    public Category findById(@PathVariable Long id) {
        return categoryRepository.findById(id).orElseThrow(CategoryDoNotExistException::new);
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category category) {
        Category categoryToUpdate = categoryRepository.findById(id).orElseThrow(CategoryDoNotExistException::new);
        categoryToUpdate.setName(category.getName());
        categoryToUpdate.setDescription(category.getDescription());
        return categoryToUpdate;
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
