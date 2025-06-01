package com.microcenter.web.repository;

import com.microcenter.web.dto.ProductDTO;

import java.util.List;

public class DummyProductRepositoryImpl implements ProductRepository{

    @Override
    public List<ProductDTO> findAllProducts() {
        return List.of(
                new ProductDTO(String.valueOf(1), "Laptop", "High performance laptop", 999.99, "Electronics"),
                new ProductDTO(String.valueOf(2), "Smartphone", "Latest model smartphone", 799.99, "Electronics"),
                new ProductDTO(String.valueOf(3), "Tablet", "Portable tablet with stylus support", 499.99, "Electronics"),
                new ProductDTO(String.valueOf(4), "Smartwatch", "Feature-rich smartwatch", 199.99, "Electronics"),
                new ProductDTO(String.valueOf(5), "Headphones", "Noise-cancelling headphones", 149.99, "Electronics")
        );
    }
}
