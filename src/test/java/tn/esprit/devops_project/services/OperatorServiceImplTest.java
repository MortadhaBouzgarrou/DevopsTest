package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OperatorServiceImplTest {

    @InjectMocks
    private OperatorServiceImpl operatorService;

    @Mock
    private OperatorRepository operatorRepository;

    @Test
    public void retrieveAllOperatorsTest() {
        when(operatorRepository.findAll()).thenReturn(Stream.of(new Operator(), new Operator(), new Operator()).collect(Collectors.toList()));
        assertEquals(3, operatorService.retrieveAllOperators().size());
    }

    @Test
    public void addOperatorTest() {
        Operator operator = new Operator();
        when(operatorRepository.save(operator)).thenReturn(operator);
        assertEquals(operator, operatorService.addOperator(operator));
    }

    @Test
    public void deleteOperatorTest() {
        Operator operator = new Operator();
        Operator operator1 = new Operator();
        operatorService.deleteOperator(operator.getIdOperateur()); // Use operator.getId() to get the ID
        operatorService.deleteOperator(operator1.getIdOperateur()); // Use operator1.getId() to get the ID
        verify(operatorRepository, times(2)).deleteById(operator.getIdOperateur());
    }

    @Test
    public void updateOperatorTest() {
        Operator operator = new Operator();
        when(operatorRepository.save(operator)).thenReturn(operator);
        assertEquals(operator, operatorService.updateOperator(operator));
    }

    @Test
    public void retrieveOperatorTest() {
        Operator operator = new Operator();
        when(operatorRepository.findById(operator.getIdOperateur())).thenReturn(Optional.of(operator));
        assertEquals(operator.getIdOperateur(), operatorService.retrieveOperator(operator.getIdOperateur()).getIdOperateur());
    }
}
