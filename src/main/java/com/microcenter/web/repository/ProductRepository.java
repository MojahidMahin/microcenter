package com.microcenter.web.repository;

import com.microcenter.web.domain.Product;
import com.microcenter.web.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAllProducts();

    Optional<Product> findById(Long id);
}
