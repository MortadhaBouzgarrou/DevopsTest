package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;

import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    ProductCategory category;

    @Autowired
    ProductServiceImpl productService;
    @Test
    void testRetrieveProduct() {
        Product product = productService.retrieveProduct(Long.valueOf(1));
       Assertions.assertEquals(product.getTitle(),"produit1");
        // Assertions.assertEquals(product.getTitle(),"prodddduit");
        Assertions.assertNotNull(product);
    }
    @Test
    void testRetreiveAllProduct(){
        List<Product> products= productService.retreiveAllProduct();
        Assertions.assertEquals(0,products.size());
    }
    @Test
    void testAddProduct() {
        Product product = new Product();
        product.setTitle("produit1");
        product.setPrice(4556);
        product.setCategory(category.valueOf(("ELECTRONICS")));
        product.setQuantity(5);
        productService.addProduct(product,Long.valueOf(1));
        Assertions.assertNotNull(product.getIdProduct());

    }


    @Test
    void testRetrieveProductByCategory() {
        ProductCategory category1= category.valueOf("ELECTRONICS");
        List<Product> products = productService.retrieveProductByCategory(category1);
        Assertions.assertEquals(0,products.size());

    }

    @Test
    void testDeleteProduct() {
        productService.deleteProduct(Long.valueOf(5));
        Product product = productService.retrieveProduct(Long.valueOf(5));
        Assertions.assertNotNull(product);
    }

    @Test
    void testRetreiveProductStock() {
     List <Product> products= productService.retreiveProductStock(Long.valueOf(1));
     Assertions.assertEquals(0,products.size());

    }


}