package com.churrascoprime.api.controllers;

import com.churrascoprime.api.dtos.CityDto;
import com.churrascoprime.api.models.City;
import com.churrascoprime.api.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/{idCity}")
    public ResponseEntity<City> show(@PathVariable Long idCity) {
        return ResponseEntity.ok(cityService.findById(idCity));
    }

    @GetMapping
    public ResponseEntity<Page<City>> index(Pageable pageable) {
        return ResponseEntity.ok(cityService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<City> store(@RequestBody CityDto cityDto) {
        return ResponseEntity.ok(cityService.save(cityDto));
    }

    @PutMapping("/{idCity}")
    public ResponseEntity<City> update(@PathVariable Long idCity, @RequestBody City city) {
        return ResponseEntity.ok(cityService.update(idCity, city));
    }

    @DeleteMapping("/{idCity}")
    public ResponseEntity<?> delete(@PathVariable Long idCity) {
        cityService.delete(idCity);
        return ResponseEntity.noContent().build();
    }
}
