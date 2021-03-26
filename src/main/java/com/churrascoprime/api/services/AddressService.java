package com.churrascoprime.api.services;



import com.churrascoprime.api.exceptions.RecordNotFoundException;
import com.churrascoprime.api.models.AddressModel;
import com.churrascoprime.api.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AddressService {
    
    private final AddressRepository addressRepository;
    private static final String NOT_FOUND = "address.notFound";

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public AddressModel findById(Long idAddress){
        return addressRepository.findById(idAddress).orElseThrow(() -> new RecordNotFoundException(NOT_FOUND));
    }

    public Page<AddressModel> findAll(Pageable pageable) {
        return addressRepository.findAllByDateDeletedIsNull(pageable);
    }

    public AddressModel save(AddressModel addres) {
        return addressRepository.save(addres);
    }

    public AddressModel update(AddressModel updatedAddress) {
        AddressModel address = findById(updatedAddress.getIdAddress());
        address.update(updatedAddress);
        return address;
    }

    public void delete(Long idAddress) {
        AddressModel addressModel = findById(idAddress);
        addressModel.setDateDeleted(new Date());
        addressRepository.save(addressModel);
    }
}
