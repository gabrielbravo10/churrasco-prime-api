package com.churrascoprime.api.services;

import com.churrascoprime.api.exceptions.RecordNotFoundException;
import com.churrascoprime.api.models.TelephoneModel;
import com.churrascoprime.api.repositories.TelephoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TelephoneService {
    
    private final TelephoneRepository telephoneRepository;
    private static final String NOT_FOUND = "telephone.notFound";

    @Autowired
    public TelephoneService(TelephoneRepository telephoneRepository) {
        this.telephoneRepository = telephoneRepository;
    }

    public TelephoneModel findById(Long idTelephone) {
        return telephoneRepository.findById(idTelephone).orElseThrow(() -> new RecordNotFoundException(NOT_FOUND));
    }

    public Page<TelephoneModel> findAll(Pageable pageable) {
        return telephoneRepository.findAllByDateDeletedIsNull(pageable);
    }

    public TelephoneModel save(TelephoneModel address) {
        return telephoneRepository.save(address);
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
