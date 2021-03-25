package com.churrascoprime.api.controllers;

import com.churrascoprime.api.dtos.customer.CustomerDto;
import com.churrascoprime.api.dtos.customer.CustomerFormDto;
import com.churrascoprime.api.dtos.provider.ProviderFormDto;
import com.churrascoprime.api.models.CustomerModel;
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
@RequestMapping("/cutomers")
public class CustomerController {

    private final CustomerService customerService;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerController(CustomerService customerService, ModelMapper modelMapper) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{idCustomer}/")
    public ResponseEntity<CustomerDto> show(@PathVariable Long idCustomer) {
        CustomerDto customer = modelMapper.map(customerService.findById(idCustomer), CustomerDto.class);
        return ResponseEntity.ok(customer);
    }

    @GetMapping
    public ResponseEntity<Page<CustomerDto>> index(@PageableDefault(sort = "name") Pageable pageable) {
        Page<CustomerDto> customers = customerService.findAll(pageable).map(customer -> modelMapper.map(customer, CustomerDto.class));
        return ResponseEntity.ok(customers);
    }
    
    @Transactional
    @PostMapping
    public ResponseEntity<CustomerDto> store(@Valid @RequestBody ProviderFormDto customerFormDto, UriComponentsBuilder uriComponentsBuilder) {
        CustomerModel customer = modelMapper.map(customerFormDto, CustomerModel.class);
        CustomerDto newCustomer = modelMapper.map(customerService.save(customer), CustomerDto.class);
        URI uri = uriComponentsBuilder.path("/customers/{id}").buildAndExpand(newCustomer.getIdCustomer()).toUri();
        return ResponseEntity.created(uri).body(newCustomer);
    }

    @Transactional
    @PutMapping("/{idCustomer}")
    public ResponseEntity<CustomerDto> update(@PathVariable Long idCustomer, @Valid @RequestBody CustomerDto customerFormDto) {
        CustomerModel customer = modelMapper.map(customerFormDto, CustomerModel.class);
        customer.setIdCustomer(idCustomer);
        CustomerDto updatedCustomer = modelMapper.map(customerService.update(customer), CustomerDto.class);
        return ResponseEntity.ok().body(updatedCustomer);
    }

    @Transactional
    @DeleteMapping("/{idCustomer}")
    public ResponseEntity<?> delete(@PathVariable Long idCustomer) {
        customerService.delete(idCustomer);
        return ResponseEntity.noContent().build();
    }
}
