package com.churrascoprime.api.services;

import com.churrascoprime.api.exceptions.RecordNotFoundException;
import com.churrascoprime.api.models.CityModel;
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

    public CityModel findById(Long idCity) {
        return cityRepository.findById(idCity).orElseThrow(() -> new RecordNotFoundException(NOT_FOUND));
    }

    public Page<CityModel> findAll(Pageable pageable) {
        return cityRepository.findAllByDateDeletedIsNull(pageable);
    }

    public CityModel save(CityModel city) {
        return cityRepository.save(city);
    }

    public CityModel update(CityModel updatedCity) {
        CityModel city = findById(updatedCity.getIdCity());
        city.update(updatedCity);
        return city;
    }

    public void delete(Long idCity) {
        CityModel city = findById(idCity);
        city.setDateDeleted(new Date());
        cityRepository.save(city);
    }
}
