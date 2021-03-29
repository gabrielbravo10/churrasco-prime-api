package com.churrascoprime.api.repositories;

import com.churrascoprime.api.models.TelephoneModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelephoneRepository extends JpaRepository<TelephoneModel, Long> {
    
    Page<TelephoneModel> findAllByDateDeletedIsNull(Pageable pageable);
}
