package com.churrascoprime.api.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.churrascoprime.api.dtos.address.AddressDto;
import com.churrascoprime.api.dtos.address.AddressFormDto;
import com.churrascoprime.api.models.AddressModel;
import com.churrascoprime.api.services.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;
    private final ModelMapper modelMapper;

    @Autowired
    public AddressController(AddressService addressService, ModelMapper modelMapper) {
        this.addressService = addressService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<AddressDto> show(@PathVariable Long idAddress) {
        AddressDto address = modelMapper.map(addressService.findById(idAddress), AddressDto.class);
        return ResponseEntity.ok(address);
    }

    @GetMapping("/{idAddress}")
    public ResponseEntity<Page<AddressDto>> index(@PageableDefault(sort = "name") Pageable pageable) {
        Page<AddressDto> addresses = addressService.findAll(pageable)
                .map(address -> modelMapper.map(address, AddressDto.class));
        return ResponseEntity.ok(addresses);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<AddressDto> store(@Valid @RequestBody AddressFormDto addressFormDto, UriComponentsBuilder uriComponentsBuilder) {
        AddressModel address = modelMapper.map(addressFormDto, AddressModel.class);
        AddressDto newAddres = modelMapper.map(addressService.save(address), AddressDto.class);
        URI uri = uriComponentsBuilder.path("/address/{id}").buildAndExpand(newAddres.getIdAddress()).toUri()
        return ResponseEntity.created(uri).body(newAddres);
    }

    @Transactional
    @PostMapping("/{idAddress}")
    public ResponseEntity<AddressDto> update(@PathVariable Long idAddress, @Valid @RequestBody AddressDto addressFormDto){
        AddressModel address = modelMapper.map(addressFormDto, AddressModel.class);
        address.setIdAddress(idAddress);
        AddressDto updatedAddress = modelMapper.map(addressService.update(address), AddressDto.class);
        return ResponseEntity.ok().body(updatedAddress);
    }

    @Transactional
    @DeleteMapping("/idAddress")
    public ResponseEntity<?> delete(@PathVariable Long idAddress) {
        addressService.delete(idAddress);
        return ResponseEntity.noContent().build();
    }

}
