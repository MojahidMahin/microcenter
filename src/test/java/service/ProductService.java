package service;

import com.microcenter.web.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> findAllProductsSortedByName();
}
