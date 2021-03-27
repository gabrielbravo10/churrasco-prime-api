package com.churrascoprime.api.controllers;

import com.churrascoprime.api.dtos.category.CategoryDto;
import com.churrascoprime.api.dtos.category.CategoryFormDto;
import com.churrascoprime.api.models.CategoryModel;
import com.churrascoprime.api.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{idCategory}")
    public ResponseEntity<CategoryDto> show(@PathVariable Long idCategory) {
        CategoryDto category = modelMapper.map(categoryService.findById(idCategory), CategoryDto.class);
        return ResponseEntity.ok(category);
    }

    @GetMapping
    public ResponseEntity<Page<CategoryDto>> index(@PageableDefault(sort = "name") Pageable pageable) {
        Page<CategoryDto> categories = categoryService.findAll(pageable)
                .map(category -> modelMapper.map(category, CategoryDto.class));
        return ResponseEntity.ok(categories);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<CategoryDto> store(@Valid @RequestBody CategoryFormDto categoryFormDto,
                                             UriComponentsBuilder uriComponentsBuilder) {
        CategoryModel category = modelMapper.map(categoryFormDto, CategoryModel.class);
        CategoryDto newCategory = modelMapper.map(categoryService.save(category), CategoryDto.class);
        URI uri = uriComponentsBuilder.path("/categories/{id}").buildAndExpand(newCategory.getIdCategory()).toUri();
        return ResponseEntity.created(uri).body(newCategory);
    }

    @Transactional
    @PutMapping("/{idCategory}")
    public ResponseEntity<CategoryDto> update(@PathVariable Long idCategory,
                                              @Valid @RequestBody CategoryFormDto categoryFormDto) {
        CategoryModel category = modelMapper.map(categoryFormDto, CategoryModel.class);
        category.setIdCategory(idCategory);
        CategoryDto updatedCategory = modelMapper.map(categoryService.update(category), CategoryDto.class);
        return ResponseEntity.ok().body(updatedCategory);
    }

    @Transactional
    @DeleteMapping("/{idCategory}")
    public ResponseEntity<?> delete(@PathVariable Long idCategory) {
        categoryService.delete(idCategory);
        return ResponseEntity.noContent().build();
    }
}
