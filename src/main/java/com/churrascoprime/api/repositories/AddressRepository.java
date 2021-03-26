package com.churrascoprime.api.repositories;

import com.churrascoprime.api.models.AddressModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressModel, Long> {

    Page<AddressModel> findAllByDateDeletedIsNull(Pageable pageable);
}
