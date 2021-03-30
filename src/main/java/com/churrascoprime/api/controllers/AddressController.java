package com.churrascoprime.api.controllers;

import com.churrascoprime.api.dtos.address.AddressDto;
import com.churrascoprime.api.dtos.address.AddressFormDto;
import com.churrascoprime.api.models.AddressModel;
import com.churrascoprime.api.services.AddressService;
import com.churrascoprime.api.services.CityService;
import com.churrascoprime.api.services.CustomerService;
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
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;
    private final CityService cityService;
    private final CustomerService customerService;
    private final ModelMapper modelMapper;

    @Autowired
    public AddressController(AddressService addressService, CityService cityService,
                             CustomerService customerService, ModelMapper modelMapper) {
        this.addressService = addressService;
        this.cityService = cityService;
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{idAddress}")
    public ResponseEntity<AddressDto> show(@PathVariable Long idAddress) {
        AddressDto address = modelMapper.map(addressService.findById(idAddress), AddressDto.class);
        return ResponseEntity.ok(address);
    }

    @GetMapping
    public ResponseEntity<Page<AddressDto>> index(@PageableDefault(sort = "zipCode") Pageable pageable) {
        Page<AddressDto> addresses = addressService.findAll(pageable)
                .map(address -> modelMapper.map(address, AddressDto.class));
        return ResponseEntity.ok(addresses);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<AddressDto> store(@Valid @RequestBody AddressFormDto addressFormDto, UriComponentsBuilder uriComponentsBuilder) {
        AddressModel address = modelMapper.map(addressFormDto, AddressModel.class);
        addCity(address, addressFormDto);
        addCustomer(address, addressFormDto);
        AddressDto newAddres = modelMapper.map(addressService.save(address), AddressDto.class);
        URI uri = uriComponentsBuilder.path("/addresses/{id}").buildAndExpand(newAddres.getIdAddress()).toUri();
        return ResponseEntity.created(uri).body(newAddres);
    }

    @Transactional
    @PutMapping("/{idAddress}")
    public ResponseEntity<AddressDto> update(@PathVariable Long idAddress, @Valid @RequestBody AddressFormDto addressFormDto) {
        AddressModel address = modelMapper.map(addressFormDto, AddressModel.class);
        address.setIdAddress(idAddress);
        AddressDto updatedAddress = modelMapper.map(addressService.update(address), AddressDto.class);
        return ResponseEntity.ok().body(updatedAddress);
    }

    @Transactional
    @DeleteMapping("/{idAddress}")
    public ResponseEntity<?> delete(@PathVariable Long idAddress) {
        addressService.delete(idAddress);
        return ResponseEntity.noContent().build();
    }

    private void addCity(AddressModel address, AddressFormDto addressFormDto) {
        address.setCity(cityService.findById(addressFormDto.getCity()));
    }

    private void addCustomer(AddressModel address, AddressFormDto addressFormDto) {
        address.setCustomer(customerService.findById(addressFormDto.getCustomer()));
    }
}
