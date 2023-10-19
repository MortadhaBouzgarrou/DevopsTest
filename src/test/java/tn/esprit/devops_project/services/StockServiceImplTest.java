package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Stock;


@SpringBootTest
class StockServiceImplTest {
    @Autowired
    StockServiceImpl stockService;
    @Test
    void addStock() {
        Stock stock = new Stock();
        stock.setTitle("Stock1");
        stockService.addStock(stock);
        Assertions.assertNotNull(stock.getIdStock());
    }
}