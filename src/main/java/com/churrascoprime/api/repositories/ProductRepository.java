package com.churrascoprime.api.repositories;

import com.churrascoprime.api.models.CategoryModel;
import com.churrascoprime.api.models.ProductModel;
import com.churrascoprime.api.models.ProviderModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    Page<ProductModel> findAllByDateDeletedIsNull(Pageable pageable);

    Page<ProductModel> findDistinctByProviderAndCategoriesIn(@Param("provider") ProviderModel provider,
                                                             @Param("categories") List<CategoryModel> categories,
                                                             Pageable pageable);

    Page<ProductModel> findDistinctByProvider(@Param("provider") ProviderModel provider, Pageable pageable);
}
