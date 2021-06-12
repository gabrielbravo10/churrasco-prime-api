package com.churrascoprime.api.controllers;

import com.churrascoprime.api.dtos.city.CityDto;
import com.churrascoprime.api.dtos.city.CityFormDto;
import com.churrascoprime.api.dtos.order.OrderDto;
import com.churrascoprime.api.models.CityModel;
import com.churrascoprime.api.services.CityService;
import com.churrascoprime.api.services.OrderService;
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
@CrossOrigin("http://localhost:4200")
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderController(OrderService orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{idOrder}")
    public ResponseEntity<OrderDto> show(@PathVariable Long idOrder) {
        OrderDto order = modelMapper.map(orderService.findById(idOrder), OrderDto.class);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<Page<OrderDto>> index(Pageable pageable) {
        Page<OrderDto> orders = orderService.findAll(pageable)
                .map(order -> modelMapper.map(order, OrderDto.class));
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/customer/{email}")
    public ResponseEntity<Page<OrderDto>> ordersByEmail(@PathVariable String email, Pageable pageable) {
        Page<OrderDto> orders = orderService.findAllByCustomerEmail(email, pageable)
                .map(order -> modelMapper.map(order, OrderDto.class));
        return ResponseEntity.ok(orders);
    }

//    @Transactional
//    @PostMapping
//    public ResponseEntity<CityDto> store(@Valid @RequestBody CityFormDto cityFormDto,
//                                         UriComponentsBuilder uriComponentsBuilder) {
//        CityModel order = modelMapper.map(cityFormDto, CityModel.class);
//        CityDto newCity = modelMapper.map(orderService.save(order), CityDto.class);
//        URI uri = uriComponentsBuilder.path("/cities/{id}").buildAndExpand(newCity.getIdCity()).toUri();
//        return ResponseEntity.created(uri).body(newCity);
//    }

//    @Transactional
//    @PutMapping("/{idCity}")
//    public ResponseEntity<CityDto> update(@PathVariable Long idCity,
//                                          @Valid @RequestBody CityFormDto cityFormDto) {
//        CityModel city = modelMapper.map(cityFormDto, CityModel.class);
//        city.setIdCity(idCity);
//        CityDto updatedCity = modelMapper.map(orderService.update(city), CityDto.class);
//        return ResponseEntity.ok().body(updatedCity);
//    }

//    @Transactional
//    @DeleteMapping("/{idCity}")
//    public ResponseEntity<?> delete(@PathVariable Long idCity) {
//        orderService.delete(idCity);
//        return ResponseEntity.noContent().build();
//    }
}
