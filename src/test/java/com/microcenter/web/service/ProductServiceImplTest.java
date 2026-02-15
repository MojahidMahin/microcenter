package com.microcenter.web.service;

import com.microcenter.web.domain.Product;
import com.microcenter.web.repository.ProductRepository;
import com.microcenter.web.servlet.HomeServlet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceImplTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImplTest.class);
    private static final Product Apple_I_Pad_Pro_11 = new Product(
            1L,
            "Apple iPad Pro 11",
            "Apple iPad Pro 11-inch, Wi-Fi, 128GB - Space Gray (4th Generation)",
            BigDecimal.valueOf( 799.99)
    );

    public static final Product Headphones_Sony_WF_1000XM4 = new Product(
            2L,
            "Headphones Sony WF-1000XM4" ,
            "Sony WF-1000XM4 Industry Leading Noise Canceling Truly Wireless Earbuds",
            BigDecimal.valueOf(278.00)
    );

    @Mock
    private ProductRepository productRepository;
    private ProductService productService;

    @Before
    public void setUp() throws Exception {
        productRepository = mock(ProductRepository.class);
        LOGGER.info("Setting up ProductServiceImplTest");

        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    public void testFindAllProductsSortedByName() {
        when(productRepository.findAllProducts()).thenReturn(List.of(Apple_I_Pad_Pro_11, Headphones_Sony_WF_1000XM4));

        var sortedByName = productService.findAllProductsSortedByName();
        Assert.assertEquals(Apple_I_Pad_Pro_11.getName(), sortedByName.get(0).getName());
        Assert.assertEquals(Headphones_Sony_WF_1000XM4.getName(), sortedByName.get(1).getName());
    }
}
