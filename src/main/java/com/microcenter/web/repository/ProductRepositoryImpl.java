package com.microcenter.web.repository;

import com.microcenter.web.domain.Product;
import com.microcenter.web.dto.ProductDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository{

    public static final List<Product> ALL_PRODUCTS = List.of(
            new Product(1L, "Laptop", "High performance laptop", BigDecimal.valueOf(999.99)),
            new Product(2L, "Smartphone", "Latest model smartphone", BigDecimal.valueOf(799.99)),
            new Product(3L, "Tablet", "Portable tablet with stylus support", BigDecimal.valueOf(499.99)),
            new Product(4L, "Smartwatch", "Feature-rich smartwatch", BigDecimal.valueOf(199.99)),
            new Product(5L, "Headphones", "Noise-cancelling headphones", BigDecimal.valueOf(149.99))
    );

    @Override
    public List<Product> findAllProducts() {
        return ALL_PRODUCTS;
    }

    @Override
    public Optional<Product> findById(Long productId) {
        return findAllProducts().stream().filter(product -> product.getId().equals(productId)).findFirst();
    }
}
