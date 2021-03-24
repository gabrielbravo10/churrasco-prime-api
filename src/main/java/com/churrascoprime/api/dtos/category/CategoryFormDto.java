package com.churrascoprime.api.dtos.category;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoryFormDto {

    private Long idCategory;

    @NotNull
    @Size(min = 3, max = 100)
    private String name;

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
