package com.churrascoprime.api.services;

import org.springframework.stereotype.Service;

import com.churrascoprime.api.exceptions.RecordNotFoundException;
import com.churrascoprime.api.models.CustomerModel;
import com.churrascoprime.api.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomerService {

    private final CustomerRepository customerRespository;
    private static final String NOT_FOUND = "customer.notFound";

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRespository = customerRepository;
    }

    public CustomerModel findById(Long idCustomer) {
        return customerRespository.findById(idCustomer).orElseThrow(() -> new RecordNotFoundException(NOT_FOUND));
    }

    public Page<CustomerModel> findAll(Pageable pageable) {
        return customerRespository.findAllByDateDeletedIsNull(pageable);
    }

    public CustomerModel save(CustomerModel customer) {
        return customerRespository.save(customer);
    }

    public CustomerModel update(CustomerModel updatedCustomer) {
        CustomerModel customer = findById(updatedCustomer.getIdCustomer());
        customer.update(updatedCustomer);
        return customer;
    }

    public void delete(Long idCustomer) {
        CustomerModel customerModel = findById(idCustomer);
        customerModel.setDateDeleted(new Date());
        customerRespository.save(customerModel);
    }
}
