package com.churrascoprime.api.services;

import com.churrascoprime.api.exceptions.RecordNotFoundException;
import com.churrascoprime.api.models.CategoryModel;
import com.churrascoprime.api.models.ProductModel;
import com.churrascoprime.api.models.ProviderModel;
import com.churrascoprime.api.repositories.CategoryRepository;
import com.churrascoprime.api.repositories.ProductRepository;
import com.churrascoprime.api.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProviderService providerService;
    private static final String NOT_FOUND = "product.notFound";

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository,
                          ProviderService providerService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.providerService = providerService;
    }

    public ProductModel findById(Long idProduct) {
        return productRepository.findById(idProduct).orElseThrow(() -> new RecordNotFoundException(NOT_FOUND));
    }

    public Page<ProductModel> findAll(Pageable pageable) {
        return productRepository.findAllByDateDeletedIsNull(pageable);
    }

    public ProductModel save(ProductModel productModel) {
        return productRepository.save(productModel);
    }

    public ProductModel update(ProductModel updatedProductModel) {
        ProductModel productModel = findById(updatedProductModel.getIdProduct());
        productModel.update(updatedProductModel);
        return productModel;
    }

    public void delete(Long idProduct) {
        ProductModel productModel = findById(idProduct);
        productModel.setDateDeleted(new Date());
        productRepository.save(productModel);
    }

    public Page<ProductModel> findProductsByProviderAndCategory(Long providerId, List<Long> categoryIds,
                                                               String filter, Pageable pageable) {
        ProviderModel provider = providerService.findById(providerId);
        if (categoryIds.isEmpty()) {
            if (StringUtils.isValid(filter)) {
                return productRepository.
                        findByDateDeletedIsNullAndProviderAndNameContainingIgnoreCase(provider, filter, pageable);
            }
            return productRepository.findByDateDeletedIsNullAndProvider(provider, pageable);
        }
        List<CategoryModel> categories = categoryRepository.findAllById(categoryIds);
        return productRepository.findByDateDeletedIsNullAndProviderAndCategoriesIn(provider, categories, pageable);
    }
}
