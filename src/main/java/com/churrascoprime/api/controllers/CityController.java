package com.churrascoprime.api.controllers;

import com.churrascoprime.api.dtos.city.CityDto;
import com.churrascoprime.api.dtos.city.CityFormDto;
import com.churrascoprime.api.models.CityModel;
import com.churrascoprime.api.services.CityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;
    private final ModelMapper modelMapper;

    @Autowired
    public CityController(CityService cityService, ModelMapper modelMapper) {
        this.cityService = cityService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{idCity}")
    public ResponseEntity<CityDto> show(@PathVariable Long idCity) {
        CityDto city = modelMapper.map(cityService.findById(idCity), CityDto.class);
        return ResponseEntity.ok(city);
    }

    @GetMapping
    public ResponseEntity<Page<CityDto>> index(@PageableDefault(sort = "name") Pageable pageable) {
        Page<CityDto> cities = cityService.findAll(pageable)
                .map(city -> modelMapper.map(city, CityDto.class));
        return ResponseEntity.ok(cities);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<CityDto> store(@Valid @RequestBody CityFormDto cityFormDto,
                                         UriComponentsBuilder uriComponentsBuilder) {
        CityModel city = modelMapper.map(cityFormDto, CityModel.class);
        CityDto newCity = modelMapper.map(cityService.save(city), CityDto.class);
        URI uri = uriComponentsBuilder.path("/cities/{id}").buildAndExpand(newCity.getId()).toUri();
        return ResponseEntity.created(uri).body(newCity);
    }

    @Transactional
    @PutMapping("/{idCity}")
    public ResponseEntity<CityDto> update(@PathVariable Long idCity,
                                          @Valid @RequestBody CityFormDto cityFormDto) {
        CityModel city = modelMapper.map(cityFormDto, CityModel.class);
        city.setIdCity(idCity);
        CityDto updatedCity = modelMapper.map(cityService.update(city), CityDto.class);
        return ResponseEntity.ok().body(updatedCity);
    }

    @Transactional
    @DeleteMapping("/{idCity}")
    public ResponseEntity<?> delete(@PathVariable Long idCity) {
        cityService.delete(idCity);
        return ResponseEntity.noContent().build();
    }
}
