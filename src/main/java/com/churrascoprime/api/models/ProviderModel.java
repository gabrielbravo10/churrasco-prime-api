package com.churrascoprime.api.models;

import javax.persistence.*;

@Entity
@Table(name = "tb_provider")
public class ProviderModel extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_provider")
    private Long idProvider;

    private String name;

    private String cnpj;

    @Column(name = "image_url")
    private String imageUrl;

    private boolean active;

    private Double rating;

    public Long getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(Long idProvider) {
        this.idProvider = idProvider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void update(ProviderModel updatedProvider) {
        this.name = updatedProvider.getName();
        this.cnpj = updatedProvider.getCnpj();
        this.imageUrl = updatedProvider.getImageUrl();
        this.active = updatedProvider.isActive();
        this.rating = updatedProvider.getRating();
    }
}
