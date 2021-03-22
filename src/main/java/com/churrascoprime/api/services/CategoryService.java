package com.churrascoprime.api.services;

import com.churrascoprime.api.dtos.CategoryDto;
import com.churrascoprime.api.models.Category;
import com.churrascoprime.api.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CategoryService {
    
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public Category findById(Long idCategory) {
        return categoryRepository.findById(idCategory).orElse(null);
    }

    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAllByDateDeletedIsNull(pageable);
    }

    public Category save(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        return categoryRepository.save(category);
    }

    public Category update(Long idCategory, Category category) {
        Category categoryToUpdate = findById(idCategory);
        categoryToUpdate.setName(category.getName());
        return categoryRepository.save(categoryToUpdate);
    }

    public void delete(Long idCategory) {
        Category category = findById(idCategory);
        category.setDateDeleted(new Date());
        categoryRepository.save(category);
    }






}
