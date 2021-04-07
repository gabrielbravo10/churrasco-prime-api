package com.churrascoprime.api.services;

import com.churrascoprime.api.exceptions.RecordNotFoundException;
import com.churrascoprime.api.models.TelephoneModel;
import com.churrascoprime.api.repositories.TelephoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

@Service
public class TelephoneService {
    
    private final TelephoneRepository telephoneRepository;
    private static final String NOT_FOUND = "telephone.notFound";
    private CustomerService customerService;

    @Autowired
    public TelephoneService(TelephoneRepository telephoneRepository, CustomerService customerService) {
        this.telephoneRepository = telephoneRepository;
        this.customerService = customerService;
    }

    public Set<TelephoneModel> findByCustomer(Long id) {
        Set<TelephoneModel> telephones = telephoneRepository.
                findDistinctByDateDeletedIsNullAndCustomer(customerService.findById(id));
        if (telephones == null || telephones.isEmpty()) {
            throw new RecordNotFoundException(NOT_FOUND);
        }
        return telephones;
    }


    public TelephoneModel findById(Long idTelephone) {
        return telephoneRepository.findById(idTelephone).orElseThrow(() -> new RecordNotFoundException(NOT_FOUND));
    }

    public Page<TelephoneModel> findAll(Pageable pageable) {
        return telephoneRepository.findAllByDateDeletedIsNull(pageable);
    }

    public TelephoneModel save(TelephoneModel telephone) {
        return telephoneRepository.save(telephone);
    }

    public TelephoneModel update(TelephoneModel updatedTelephone) {
        TelephoneModel telephone = findById(updatedTelephone.getIdTelephone());
        telephone.update(updatedTelephone);
        return telephone;
    }

    public void delete(Long idTelephone) {
        TelephoneModel telephoneModel = findById(idTelephone);
        telephoneModel.setDateDeleted(new Date());
        telephoneRepository.save(telephoneModel);
    }
}
