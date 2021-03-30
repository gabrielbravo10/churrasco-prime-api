package com.churrascoprime.api.controllers;

import com.churrascoprime.api.dtos.telephone.TelephoneDto;
import com.churrascoprime.api.dtos.telephone.TelephoneFormDto;
import com.churrascoprime.api.models.TelephoneModel;
import com.churrascoprime.api.services.TelephoneService;
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
@RequestMapping("/telephones")
public class TelephoneController {

    private final TelephoneService telephoneService;
    private final CustomerService customerService;
    private final ModelMapper modelMapper;

    @Autowired
    public TelephoneController(TelephoneService telephoneService, CustomerService customerService,
            ModelMapper modelMapper) {
        this.telephoneService = telephoneService;
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{idTelephone}")
    public ResponseEntity<TelephoneDto> show(@PathVariable Long idTelephone) {
        TelephoneDto telephone = modelMapper.map(telephoneService.findById(idTelephone), TelephoneDto.class);
        return ResponseEntity.ok(telephone);
    }

    @GetMapping
    public ResponseEntity<Page<TelephoneDto>> index(@PageableDefault(sort = "telephoneNumber") Pageable pageable) {
        Page<TelephoneDto> telephones = telephoneService.findAll(pageable)
                .map(telephone -> modelMapper.map(telephone, TelephoneDto.class));
        return ResponseEntity.ok(telephones);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<TelephoneDto> store(@Valid @RequestBody TelephoneFormDto telephoneFormDto,
            UriComponentsBuilder uriComponentsBuilder) {
        TelephoneModel telephone = modelMapper.map(telephoneFormDto, TelephoneModel.class);
        addCustomer(telephone, telephoneFormDto);
        TelephoneDto newTelephone = modelMapper.map(telephoneService.save(telephone), TelephoneDto.class);
        URI uri = uriComponentsBuilder.path("/telephones/{id}").buildAndExpand(newTelephone.getIdTelephone()).toUri();
        return ResponseEntity.created(uri).body(newTelephone);
    }

    @Transactional
    @PutMapping("/{idTelephone}")
    public ResponseEntity<TelephoneDto> update(@PathVariable Long idTelephone,
            @Valid @RequestBody TelephoneFormDto telephoneFormDto) {
        TelephoneModel telephone = modelMapper.map(telephoneFormDto, TelephoneModel.class);
        telephone.setIdTelephone(idTelephone);
        TelephoneDto updatedAddress = modelMapper.map(telephoneService.update(telephone), TelephoneDto.class);
        return ResponseEntity.ok().body(updatedAddress);
    }

    @Transactional
    @DeleteMapping("/{idTelephone}")
    public ResponseEntity<?> delete(@PathVariable Long idTelephone) {
        telephoneService.delete(idTelephone);
        return ResponseEntity.noContent().build();
    }

    private void addCustomer(TelephoneModel telephone, TelephoneFormDto telephoneFormDto) {
        telephone.setCustomer(customerService.findById(telephoneFormDto.getCustomer()));
    }

}
