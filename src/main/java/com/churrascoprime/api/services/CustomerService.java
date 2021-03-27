package com.churrascoprime.api.services;

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

    private final CustomerRepository customerRepository;
    private static final String NOT_FOUND = "customer.notFound";

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerModel findById(Long idCustomer) {
        return customerRepository.findById(idCustomer).orElseThrow(() -> new RecordNotFoundException(NOT_FOUND));
    }

    public Page<CustomerModel> findAll(Pageable pageable) {
        return customerRepository.findAllByDateDeletedIsNull(pageable);
    }

    public CustomerModel save(CustomerModel customer) {
        return customerRepository.save(customer);
    }

    public CustomerModel update(CustomerModel updatedCustomer) {
        CustomerModel customer = findById(updatedCustomer.getIdCustomer());
        customer.update(updatedCustomer);
        return customer;
    }

    public void delete(Long idCustomer) {
        CustomerModel customerModel = findById(idCustomer);
        customerModel.setDateDeleted(new Date());
        customerRepository.save(customerModel);
    }
}
