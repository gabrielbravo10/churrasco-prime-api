package com.churrascoprime.api.dtos.city;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CityFormDto {

    private String id;

    @NotNull
    @Size(min = 3, max = 100)
    private String name;

    public CityFormDto() {
    }

    public CityFormDto(String id, String name) {
        this.id = id;
        this.name = name;
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
}
