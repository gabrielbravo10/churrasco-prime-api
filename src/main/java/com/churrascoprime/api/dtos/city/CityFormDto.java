package com.churrascoprime.api.dtos.city;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CityFormDto {

    private Long idCity;

    @NotNull
    @Size(min = 3, max = 100)
    private String name;

    public CityFormDto() {
    }

    public CityFormDto(Long idCity, @NotNull @Size(min = 3, max = 100) String name) {
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
