package com.churrascoprime.api.repositories;

import com.churrascoprime.api.models.OrderModel;
import com.churrascoprime.api.models.ProviderModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long> {

    Page<OrderModel> findAllByDateDeletedIsNull(Pageable pageable);

    Page<OrderModel> findAllByCustomerEmailAndDateDeletedIsNullOrderByDateCreated(@Param("email") String email, Pageable pageable);

}
