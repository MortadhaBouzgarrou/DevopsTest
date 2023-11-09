package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class OperatorMockitoTest {

    @InjectMocks
    private OperatorServiceImpl operatorService;

    @Mock
    private OperatorRepository operatorRepository;

    @Test
    public void testRetrieveAllOperators() {
        List<Operator> operatorList = new ArrayList<>();
        operatorList.add(new Operator());
        operatorList.add(new Operator());
        operatorList.add(new Operator());
        operatorList.add(new Operator());

        when(operatorRepository.findAll()).thenReturn(operatorList);

        List<Operator> result = operatorService.retrieveAllOperators();

        assertEquals(4, result.size());
    }

    @Test
    public void testAddOperator() {
        Operator operator = new Operator();
        operator.setFname("jawher1");
        operator.setLname("BL1");
        operator.setPassword("0");
        when(operatorRepository.save(operator)).thenReturn(operator);

        Operator result = operatorService.addOperator(operator);

        assertEquals(operator, result);
        assertEquals("jawher1", result.getFname());
        assertEquals("BL1", result.getLname());
        assertEquals("0", result.getPassword());
    }

    @Test
    public void testDeleteOperator() {
        Long idToDelete = 1L;

        operatorService.deleteOperator(idToDelete);

        verify(operatorRepository).deleteById(idToDelete);
    }

    @Test
    public void testUpdateOperator() {
        Operator operator = new Operator();
        operator.setIdOperateur(1L);
        operator.setFname("jawher2");
        operator.setLname("BL2");
        operator.setPassword("00");

        when(operatorRepository.save(operator)).thenReturn(operator);

        Operator result = operatorService.updateOperator(operator);

        assertNotNull(result);
        assertEquals("jawher2", result.getFname());
        assertEquals("BL2", result.getLname());
        assertEquals("00", result.getPassword());
    }

    @Test
    public void testRetrieveOperator() {
        Long id = 1L;

        Operator operator = new Operator();
        operator.setIdOperateur(1L);
        operator.setFname("jawher3");
        operator.setLname("BL3");
        operator.setPassword("000");

        when(operatorRepository.findById(id)).thenReturn(java.util.Optional.of(operator));

        Operator result = operatorService.retrieveOperator(id);

        assertEquals(operator, result);
        assertEquals("jawher3", result.getFname());
        assertEquals("BL3", result.getLname());
        assertEquals("000", result.getPassword());
    }
}
