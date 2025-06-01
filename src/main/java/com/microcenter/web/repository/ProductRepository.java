package com.microcenter.web.repository;

import com.microcenter.web.dto.ProductDTO;

import java.util.List;

public interface ProductRepository {
    List<ProductDTO> findAllProducts();
}
