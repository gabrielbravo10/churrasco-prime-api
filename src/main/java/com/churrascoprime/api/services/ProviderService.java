package com.churrascoprime.api.services;

import com.churrascoprime.api.exceptions.RecordNotFoundException;
import com.churrascoprime.api.models.Provider;
import com.churrascoprime.api.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProviderService {
    
    private final ProviderRepository providerRepository;
    private static final String NOT_FOUND = "provider.notFound";

    @Autowired
    public ProviderService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    public Provider findById(Long idProvider) {
        return providerRepository.findById(idProvider).orElseThrow(() -> new RecordNotFoundException(NOT_FOUND));
    }

    public Page<Provider> findAll(Pageable pageable) {
        return providerRepository.findAllByDateDeletedIsNull(pageable);
    }

    public Provider save(Provider provider) {
        return providerRepository.save(provider);
    }

    public Provider update(Provider updatedProvider) {
        Provider provider = findById(updatedProvider.getIdProvider());
        provider.update(updatedProvider);
        return provider;
    }

    public void delete(Long idProvider) {
        Provider provider = findById(idProvider);
        provider.setDateDeleted(new Date());
        providerRepository.save(provider);
    }
}
