package com.churrascoprime.api.services;

import com.churrascoprime.api.exceptions.RecordNotFoundException;
import com.churrascoprime.api.models.City;
import com.churrascoprime.api.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CityService {

    private final CityRepository cityRepository;
    private static final String NOT_FOUND = "city.notFound";

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City findById(Long idCity) {
        return cityRepository.findById(idCity).orElseThrow(() -> new RecordNotFoundException(NOT_FOUND));
    }

    public Page<City> findAll(Pageable pageable) {
        return cityRepository.findAllByDateDeletedIsNull(pageable);
    }

    public City save(City city) {
        return cityRepository.save(city);
    }

    public City update(City updatedCity) {
        City city = findById(updatedCity.getIdCity());
        city.update(updatedCity);
        return city;
    }

    public void delete(Long idCity) {
        City city = findById(idCity);
        city.setDateDeleted(new Date());
        cityRepository.save(city);
    }
}
