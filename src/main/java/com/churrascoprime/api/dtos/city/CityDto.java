package com.churrascoprime.api.dtos.city;

public class CityDto {

    private Long idCity;
    private String name;

    public CityDto() {
    }

    public CityDto(Long idCity, String name) {
        this.idCity = idCity;
        this.name = name;
    }

    public Long getIdCity() {
        return idCity;
    }

    public void setIdCity(Long idCity) {
        this.idCity = idCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
