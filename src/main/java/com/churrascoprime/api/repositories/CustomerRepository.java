package com.churrascoprime.api.repositories;

import com.churrascoprime.api.models.CustomerModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {

    Page<CustomerModel> findAllByDateDeletedIsNull(Pageable pageable);
}
