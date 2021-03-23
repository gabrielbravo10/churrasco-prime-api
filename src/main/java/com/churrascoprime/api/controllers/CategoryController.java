package com.churrascoprime.api.controllers;

import com.churrascoprime.api.dtos.category.CategoryDto;
import com.churrascoprime.api.models.Category;
import com.churrascoprime.api.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{idCategory}")
    public ResponseEntity<Category> show(@PathVariable Long idCategory) {
        return ResponseEntity.ok(categoryService.findById(idCategory));
    }

    @GetMapping
    public ResponseEntity<Page<Category>> index(Pageable pageable) {
        return ResponseEntity.ok(categoryService.findAll(pageable));
    }

    @PostMapping
    public  ResponseEntity<Category> store(@RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.save(categoryDto));
    }

    @PutMapping("/{idCategory}")
    public ResponseEntity<Category> update(@PathVariable Long idCategory, @RequestBody Category category) {
        return ResponseEntity.ok(categoryService.update(idCategory, category));
    }

    @DeleteMapping("/{idCategory}")
    public ResponseEntity<?> delete(@PathVariable Long idCategory){
        categoryService.delete(idCategory);
        return ResponseEntity.noContent().build();
    }
}
