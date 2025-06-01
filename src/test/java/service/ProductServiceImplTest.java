package service;

import com.microcenter.web.dto.ProductDTO;
import com.microcenter.web.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceImplTest {
    private static final ProductDTO Apple_I_Pad_Pro_11 = new ProductDTO(
            "Apple_I_Pad_Pro_11",
            "Apple iPad Pro 11",
            "Apple iPad Pro 11-inch, Wi-Fi, 128GB - Space Gray (4th Generation)",
            799.99,
            "Tablets"
    );

    public static final ProductDTO Headphones_Sony_WF_1000XM4 = new ProductDTO(
            "Headphones_Sony_WF_1000XM4",
            "Sony WF-1000XM4",
            "Sony WF-1000XM4 Wireless Noise Cancelling Earbuds - Black",
            279.99,
            "Audio"
    );

    private ProductRepository productRepository;
    private ProductService productService;

    @Before
    public void setUp() throws Exception {
        productRepository = mock(ProductRepository.class);
        productService = new ProductServiceImpl(productRepository);

    }

    @Test
    public void testFindAllProductsSortedByName() {
        when(productRepository.findAllProducts())
                .thenReturn(List.of(Apple_I_Pad_Pro_11, Headphones_Sony_WF_1000XM4));

        var sortedByName = productService.findAllProductsSortedByName();
        Assert.assertEquals(sortedByName.get(0).getName(), Apple_I_Pad_Pro_11.getName());
        Assert.assertEquals(sortedByName.get(1).getName(), Headphones_Sony_WF_1000XM4.getName());
    }
}
