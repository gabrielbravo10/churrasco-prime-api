package com.churrascoprime.api.repositories;

import com.churrascoprime.api.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    
    Page<Category> findAllByDateDeletedIsNull(Pageable pageable);
}
