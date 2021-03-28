package com.churrascoprime.api.services;

import com.churrascoprime.api.exceptions.RecordNotFoundException;
import com.churrascoprime.api.models.ProviderModel;
import com.churrascoprime.api.repositories.ProviderRepository;
import com.churrascoprime.api.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProviderService {

    private final ProviderRepository providerRepository;
    private static final String NOT_FOUND = "provider.notFound";

    @Autowired
    public ProviderService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    public ProviderModel findById(Long idProvider) {
        return providerRepository.findById(idProvider).orElseThrow(() -> new RecordNotFoundException(NOT_FOUND));
    }

    public Page<ProviderModel> findAll(String filter, Pageable pageable) {
        if (StringUtils.isValid(filter)) {
            return providerRepository.findAllByDateDeletedIsNullAndNameContainingIgnoreCase(filter, pageable);
        }
        return providerRepository.findAllByDateDeletedIsNull(pageable);
    }

    public ProviderModel save(ProviderModel provider) {
        return providerRepository.save(provider);
    }

    public ProviderModel update(ProviderModel updatedProvider) {
        ProviderModel provider = findById(updatedProvider.getIdProvider());
        provider.update(updatedProvider);
        return provider;
    }

    public void delete(Long idProvider) {
        ProviderModel provider = findById(idProvider);
        provider.setDateDeleted(new Date());
        providerRepository.save(provider);
    }

    public List<ProviderModel> findTop3ActiveProviders() {
        return providerRepository.findTop3ByActiveIsTrueAndDateDeletedIsNullOrderByRatingDesc();
    }
}
