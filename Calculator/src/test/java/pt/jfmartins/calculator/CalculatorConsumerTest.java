package pt.jfmartins.calculator;

import model.CalcRequest;
import model.CalcResult;
import model.CalculatorOpsEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorConsumerTest {
    @InjectMocks
    private CalculatorConsumer calculatorConsumer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testSumOperation() {
        // Arrange
        CalcRequest request = new CalcRequest("1", CalculatorOpsEnum.SUM, 5.0, 3.0);

        // Act
        CalcResult result = calculatorConsumer.receive(request);

        // Assert
        assertNotNull(result);
        assertEquals(8.0, result.getResult());
    }

    @Test
    void testDivideOperation() {
        // Arrange
        CalcRequest request = new CalcRequest("2", CalculatorOpsEnum.DIVIDE,10.0, 2.0);

        // Act
        CalcResult result = calculatorConsumer.receive(request);

        // Assert
        assertNotNull(result);
        assertEquals(5.0, result.getResult());
    }


    @Test
    void testInvalidOperation() {
        // Arrange
        CalcRequest request = new CalcRequest("4", null, 7.0, 3.0);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> calculatorConsumer.receive(request));
    }

    @Test
    void testMDCContextManagement() {
        // Arrange
        CalcRequest request = new CalcRequest("5", CalculatorOpsEnum.SUM, 5.0, 5.0);

        // Act
        calculatorConsumer.receive(request);

        // Assert
        // Verify that MDC is cleared after processing
        assertNull(org.slf4j.MDC.get("requestId"));
    }
}
