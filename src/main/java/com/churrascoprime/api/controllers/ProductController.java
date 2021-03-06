package com.churrascoprime.api.controllers;

import com.churrascoprime.api.constants.FileConstant;
import com.churrascoprime.api.dtos.product.ProductDto;
import com.churrascoprime.api.dtos.product.ProductFormDto;
import com.churrascoprime.api.models.ProductModel;
import com.churrascoprime.api.services.CategoryService;
import com.churrascoprime.api.services.ProductService;
import com.churrascoprime.api.services.ProviderService;
import com.churrascoprime.api.utils.URL;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/products")
public class ProductController {

    private final ModelMapper modelMapper;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProviderService providerService;

    @Autowired
    public ProductController(ModelMapper modelMapper, ProductService productService,
                             CategoryService categoryService, ProviderService providerService) {
        this.modelMapper = modelMapper;
        this.productService = productService;
        this.categoryService = categoryService;
        this.providerService = providerService;
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<ProductDto> show(@PathVariable Long idProduct) {
        ProductDto product = modelMapper.map(productService.findById(idProduct), ProductDto.class);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDto>> index(@PageableDefault(sort = "name") Pageable pageable) {
        Page<ProductDto> products = productService.findAll(pageable)
                .map(product -> modelMapper.map(product, ProductDto.class));
        return ResponseEntity.ok(products);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<ProductDto> store(@Valid @RequestBody ProductFormDto productFormDto,
                                            UriComponentsBuilder uriComponentsBuilder) {
        ProductModel product = modelMapper.map(productFormDto, ProductModel.class);
        addProvider(product, productFormDto);
        addCategory(product, productFormDto);
        ProductDto newProduct = modelMapper.map(productService.save(product), ProductDto.class);
        URI uri = uriComponentsBuilder.path("/products/{id}").buildAndExpand(newProduct.getIdProduct()).toUri();
        return ResponseEntity.created(uri).body(newProduct);
    }

    @Transactional
    @PutMapping("/{idProduct}")
    public ResponseEntity<ProductDto> update(@PathVariable Long idProduct,
                                             @Valid @RequestBody ProductFormDto productFormDto) {
        ProductModel product = modelMapper.map(productFormDto, ProductModel.class);
        product.setIdProduct(idProduct);
        ProductDto updatedProduct = modelMapper.map(productService.update(product), ProductDto.class);
        return ResponseEntity.ok().body(updatedProduct);
    }

    @Transactional
    @DeleteMapping("/{idProduct}")
    public ResponseEntity<?> delete(@PathVariable Long idProduct) {
        productService.delete(idProduct);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/provider/{id}")
    public ResponseEntity<Page<ProductDto>> findProductsByProviderAndCategory(
            @PathVariable("id") Long providerId,
            @RequestParam(value = "categories", defaultValue = "") String categories,
            @RequestParam(value = "filter", required = false) String filter,
            Pageable pageable
    ) {
        List<Long> categoryIds = URL.decodeIntList(categories);
        Page<ProductModel> page = productService.findProductsByProviderAndCategory(
                providerId, categoryIds, filter, pageable);
        Page<ProductDto> productsDtos = page.map(product -> modelMapper.map(product, ProductDto.class));
        return ResponseEntity.ok(productsDtos);
    }

    @GetMapping(path = "/image/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImg(@PathVariable("name") String name) throws IOException {
        String fileName = name.concat(FileConstant.DOT + FileConstant.JPG_EXTENSION);
        return Files.readAllBytes(Paths.get(FileConstant.USER_FOLDER + FileConstant.FORWARD_SLASH + fileName));
    }

    private void addProvider(ProductModel productModel, ProductFormDto productFormDto) {
        productModel.setProvider(providerService.findById(productFormDto.getProvider()));
    }

    private void addCategory(ProductModel productModel, ProductFormDto productFormDto) {
        if (productFormDto.getCategories() != null && !productFormDto.getCategories().isEmpty()) {
            for (Long idCategory : productFormDto.getCategories()) {
                productModel.addCategory(categoryService.findById(idCategory));
            }
        }
    }
}
