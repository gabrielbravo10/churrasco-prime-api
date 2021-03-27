package com.churrascoprime.api.models;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_product")
public class ProductModel extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long idProduct;

    private String sku;

    private String name;

    private String description;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "image_url")
    private String imageUrl;

    private boolean active;

    @Column(name = "units_in_stock")
    private Long unitsInStock;

    @ManyToOne
    @JoinColumn(name = "id_provider",
            foreignKey = @ForeignKey(name = "fk_product_provider"))
    private ProviderModel provider;

    @ManyToMany
    @JoinTable(name = "tb_product_category",
            joinColumns = @JoinColumn(name = "id_product",
                    foreignKey = @ForeignKey(name = "fk_product_category_product")),
            inverseJoinColumns = @JoinColumn(name = "id_category",
                    foreignKey = @ForeignKey(name = "fk_product_category_category")))
    private Set<CategoryModel> categories;

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(Long unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public ProviderModel getProvider() {
        return provider;
    }

    public void setProvider(ProviderModel provider) {
        this.provider = provider;
    }

    public Set<CategoryModel> getCategories() {
        if (categories == null) {
            Collections.emptySet();
        }
        return categories;
    }

    public void addCategory(CategoryModel category) {
        if (categories == null) {
            categories = new HashSet<>();
        }
        categories.add(category);
    }

    public void update(ProductModel updatedProductModel) {
        this.sku = updatedProductModel.getSku();
        this.name = updatedProductModel.getName();
        this.description = updatedProductModel.getDescription();
        this.unitPrice = updatedProductModel.getUnitPrice();
        this.imageUrl = updatedProductModel.getImageUrl();
        this.active = updatedProductModel.isActive();
        this.unitsInStock = updatedProductModel.getUnitsInStock();
        this.provider = updatedProductModel.getProvider();
        this.categories = updatedProductModel.getCategories();
    }
}
