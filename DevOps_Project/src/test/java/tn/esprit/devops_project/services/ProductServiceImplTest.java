package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.services.Iservices.IProductService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceImplTest {
    @Autowired
    IProductService iProductService;
    @Test
    void retreiveAllProduct() {
        List<Product> products= iProductService.retreiveAllProduct();
        Assertions.assertEquals(0,products.size());
    }
}