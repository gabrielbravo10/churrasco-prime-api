package com.churrascoprime.api.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tb_category")
public class CategoryModel extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private Long idCategory;

    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<ProductModel> productModels;

    public CategoryModel() {
    }

    public CategoryModel(Long idCategory, String name) {
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

    public Set<ProductModel> getProducts() {
        return productModels;
    }

    public void setProducts(Set<ProductModel> productModels) {
        this.productModels = productModels;
    }

    public void update(CategoryModel updatedCategory) {
        this.name = updatedCategory.getName();
    }
}
