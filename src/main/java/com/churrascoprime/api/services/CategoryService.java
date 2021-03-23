package com.churrascoprime.api.services;

import com.churrascoprime.api.exceptions.RecordNotFoundException;
import com.churrascoprime.api.models.Category;
import com.churrascoprime.api.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private static final String NOT_FOUND = "category.notFound";

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findById(Long idCategory) {
        return categoryRepository.findById(idCategory).orElseThrow(() -> new RecordNotFoundException(NOT_FOUND));
    }

    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAllByDateDeletedIsNull(pageable);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public Category update(Category updatedCategory) {
        Category category = findById(updatedCategory.getIdCategory());
        category.update(updatedCategory);
        return category;
    }

    public void delete(Long idCategory) {
        Category category = findById(idCategory);
        category.setDateDeleted(new Date());
        categoryRepository.save(category);
    }
}
