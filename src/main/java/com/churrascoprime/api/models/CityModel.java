package com.churrascoprime.api.models;

import javax.persistence.*;

@Entity
@Table(name = "tb_city")
public class CityModel extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_city")
    private Long idCity;

    private String name;

    public CityModel() {
    }

    public CityModel(Long idCity, String name) {
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

    public void update(CityModel updatedCity) {
        this.name = updatedCity.getName();
    }
}
