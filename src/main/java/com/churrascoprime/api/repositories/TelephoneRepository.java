package com.churrascoprime.api.repositories;

import com.churrascoprime.api.models.CustomerModel;
import com.churrascoprime.api.models.TelephoneModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TelephoneRepository extends JpaRepository<TelephoneModel, Long> {

    Page<TelephoneModel> findAllByDateDeletedIsNull(Pageable pageable);

    Set<TelephoneModel> findDistinctByDateDeletedIsNullAndCustomer(CustomerModel customer);
}
