package com.example.taskmanagmentapp.category;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
      private static final int SIZE_ON_PAGE = 3;
      private final CategoryRepository categoryRepository;


      public Page<Category> findAllCategories(int page){

            PageRequest pageable = PageRequest.of(page, SIZE_ON_PAGE);
            return categoryRepository.findAll(pageable);
      }
      public Category addCategory(Category category){
            return categoryRepository.save(category);
      }
}
