package com.churrascoprime.api.dtos.provider;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProviderFormDto {

    private String id;

    @NotNull
    @Size(min = 3, max = 100)
    private String name;

    @NotNull
    @Size(min = 3, max = 100)
    private String cnpj;

    @NotNull
    @Size(min = 3, max = 1000)
    private String imagemUrl;

    @NotNull
    @Size(min = 3, max = 100)
    private char active;

    @NotNull
    @Size(min = 3, max = 100)
    private double rating;

    public ProviderFormDto() {

    }

    public ProviderFormDto(String id, String name, String cnpj, String imagemUrl, char active, double rating) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
        this.imagemUrl = imagemUrl;
        this.active = active;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public char getActive() {
        return active;
    }

    public void setActive(char active) {
        this.active = active;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

}
