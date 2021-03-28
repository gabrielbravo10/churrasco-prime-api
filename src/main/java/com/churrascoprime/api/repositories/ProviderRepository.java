package com.churrascoprime.api.repositories;

import com.churrascoprime.api.models.ProviderModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderRepository extends JpaRepository<ProviderModel, Long> {
    Page<ProviderModel> findAllByDateDeletedIsNull(Pageable pageable);

    List<ProviderModel> findTop3ByActiveIsTrueAndDateDeletedIsNullOrderByRatingDesc();

    Page<ProviderModel> findAllByDateDeletedIsNullAndNameContainingIgnoreCase(String name, Pageable pageable);
}
