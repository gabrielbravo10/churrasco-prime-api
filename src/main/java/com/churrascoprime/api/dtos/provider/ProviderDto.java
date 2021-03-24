package com.churrascoprime.api.dtos.provider;

public class ProviderDto {

    private String id;

    private String name;
    private String cnpj;
    private String imagemUrl;
    private char active;
    private double rating;

    public ProviderDto() {

    }

    public ProviderDto(String id, String name, String cnpj, String imagemUrl, char active, double rating) {
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
