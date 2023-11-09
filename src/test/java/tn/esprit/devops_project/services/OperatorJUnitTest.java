package tn.esprit.devops_project.services;

//import org.junit.AfterClass;
//import org.junit.BeforeClass;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest

public class OperatorJUnitTest {

    @Autowired
    OperatorRepository operatorRepository;
    @Autowired
    OperatorServiceImpl operatorServiceImpl;

    @Test
    void testRetrieveAllOperators() {
        ArrayList<Operator> operatorList = new ArrayList<>();
        operatorRepository.saveAll(operatorList);


        List<Operator> actualRetrieveAllOperatorsResult = operatorServiceImpl.retrieveAllOperators();

        assertNotSame(operatorList, actualRetrieveAllOperatorsResult);
        assertFalse(actualRetrieveAllOperatorsResult.isEmpty());
    }

    @Test
    void testAddOperator() {
        Operator newOperator = new Operator();
        newOperator.setFname("mortadha");
        newOperator.setLname("bouz");
        newOperator.setPassword("1");

        Operator addedOperator = operatorServiceImpl.addOperator(newOperator);

        assertNotNull(addedOperator);

        Operator retrievedOperator = operatorRepository.findById(addedOperator.getIdOperateur()).orElse(null);

        assertNotNull(retrievedOperator);
        assertEquals("mortadha", retrievedOperator.getFname());
        assertEquals("bouz", retrievedOperator.getLname());
        assertEquals("1", retrievedOperator.getPassword());
    }

    @Test
    void testDeleteOperator() {
        Operator newOperator = new Operator();
        newOperator.setFname("mortadha2");
        newOperator.setLname("bouz2");
        newOperator.setPassword("2");

        Operator addedOperator = operatorRepository.save(newOperator);

        operatorServiceImpl.deleteOperator(addedOperator.getIdOperateur());

        Optional<Operator> deletedOperator = operatorRepository.findById(addedOperator.getIdOperateur());

        assertFalse(deletedOperator.isPresent());
    }

    @Test
    void testUpdateOperator() {
        Operator newOperator = new Operator();
        newOperator.setFname("mortadha3");
        newOperator.setLname("bouz3");
        newOperator.setPassword("3");

        Operator addedOperator = operatorRepository.save(newOperator);

        addedOperator.setFname("mortadha3");

        Operator updatedOperator = operatorServiceImpl.updateOperator(addedOperator);

        Operator retrievedOperator = operatorRepository.findById(addedOperator.getIdOperateur()).orElse(null);

        assertEquals("mortadha3", retrievedOperator.getFname());
        assertEquals("bouz3", retrievedOperator.getLname());
    }

    @Test
    void testRetrieveOperator() {
        Operator newOperator = new Operator();
        newOperator.setFname("mortadha4");
        newOperator.setLname("bouz4");
        newOperator.setPassword("4");

        Operator addedOperator = operatorServiceImpl.addOperator(newOperator);

        Operator retrievedOperator = operatorServiceImpl.retrieveOperator(addedOperator.getIdOperateur());

        assertNotNull(retrievedOperator);

        assertEquals("mortadha4", retrievedOperator.getFname());
        assertEquals("bouz4", retrievedOperator.getLname());
        assertEquals("4", retrievedOperator.getPassword());
    }
}