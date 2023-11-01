package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)

public class ProductServiceImplMockitoTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);


    }
    Stock stock=new Stock(1l,"Stocktest",null);
    Product product= new Product(1l,"TestStock",300,30,ProductCategory.ELECTRONICS,stock);
    Product product2= new Product(2l,"TestStock",300,30,ProductCategory.ELECTRONICS,stock);
    List<Product> listProducts = new ArrayList<Product>(){
        {
            add(product);
            add(product2);
        }
    };

    @Test
    @Order(0)
    public void testAddProduct() {

        Mockito.when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));
        Mockito.when(productRepository.save(product)).thenReturn(product);
        Mockito.when(productRepository.save(product2)).thenReturn(product2);

        Product savedProduct = productService.addProduct(product, 1L);
        Product savedProduct2 = productService.addProduct(product2, 2L);

        Assertions.assertEquals(product, savedProduct);
        Assertions.assertEquals(product2, savedProduct2);


    }

    @Test
    @Order(1)
    public void testRetrieveProduct() {

        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(product));
        Product product1 = productService.retrieveProduct(1l);
        Assertions.assertNotNull(product1); }

    @Test
    @Order(2)
    public void testRetreiveAllProduct() {

        Mockito.when(productRepository.findAll()).thenReturn(listProducts);

        List<Product> retrievedProducts = productService.retreiveAllProduct();

        Assertions.assertEquals(listProducts, retrievedProducts);

    }

    @Test
    @Order(3)
    public void testRetrieveProductByCategory() {

        Mockito.when(productRepository.findByCategory(ProductCategory.ELECTRONICS)).thenReturn(listProducts);

        List<Product> retrievedProducts = productService.retrieveProductByCategory(ProductCategory.ELECTRONICS);

        Assertions.assertEquals(listProducts, retrievedProducts);
    }
    @Test
    @Order(4)
    public void testRetreiveProductStock() {

        Mockito.when(productRepository.findByStockIdStock(1L)).thenReturn(listProducts);

        List<Product> retrievedProducts = productService.retreiveProductStock(1L);

        Assertions.assertEquals(listProducts, retrievedProducts);
    }


    @Test
    @Order(5)
    public void testDeleteProduct() {
        productService.deleteProduct(1L);

        Mockito.verify(productRepository).deleteById(1L);
    }
}
