package com.microcenter.web.service;

import com.microcenter.web.domain.Product;
import com.microcenter.web.dto.ProductDTO;
import com.microcenter.web.repository.ProductRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<ProductDTO> findAllProductsSortedByName() {
        return productRepository.findAllProducts()
                .stream()
                .map(this::convertToDTO)
                .sorted(Comparator.comparing(ProductDTO::getName))
                .collect(Collectors.toList());
//        return null;
    }

    private ProductDTO convertToDTO(Product product) {
        return new ProductDTO(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice()
        );
    }
}
