package com.churrascoprime.api.services;

import com.churrascoprime.api.dtos.CityDto;
import com.churrascoprime.api.models.City;
import com.churrascoprime.api.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CityService {

    private CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City findById(Long idCity) {
        return cityRepository.findById(idCity).orElse(null);
    }

    public Page<City> findAll(Pageable pageable) {
        return cityRepository.findAllByDateDeletedIsNull(pageable);
    }

    public City save(CityDto cityDto) {
        City city = new City();
        city.setName(cityDto.getName());
        return cityRepository.save(city);
    }

    public City update(Long idCity, City city) {
        City cityToUpdate = findById(idCity);
        cityToUpdate.setName(city.getName());
        return cityRepository.save(cityToUpdate);
    }

    public void delete(Long idCity) {
        City city = findById(idCity);
        city.setDateDeleted(new Date());
        cityRepository.save(city);
    }
}
