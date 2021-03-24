package com.churrascoprime.api.dtos.category;

public class CategoryDto {

    private Long idCategory;
    private String name;

    public CategoryDto() {
    }

    public CategoryDto(Long idCategory, String name) {
        this.idCategory = idCategory;
        this.name = name;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

