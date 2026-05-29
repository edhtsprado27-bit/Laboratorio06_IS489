package pe.unsch.is489;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentProcessorTest {

    private PaymentProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new PaymentProcessor();
        processor.reiniciarProcesosDiarios();
    }

    @Test
    void testCalcularImpuesto() {
        assertEquals(180.0, processor.calcularImpuesto(1000), 0.01);
        assertEquals(900.0, processor.calcularImpuesto(5000), 0.01);
    }

    @Test
    void testValidarMontoMinimo() {
        assertFalse(processor.validarMonto(50));
        assertTrue(processor.validarMonto(100));
        assertTrue(processor.validarMonto(150));
    }

    @Test
    void testProcesarPago() {
        assertTrue(processor.procesarPago(500));
        assertFalse(processor.procesarPago(50)); // monto mínimo
    }

    @Test
    void testLimiteDiario() {
        assertTrue(processor.procesarPago(4000));
        assertFalse(processor.procesarPago(2000)); // excede límite
    }

    @Test
    void testProcesarReembolso() {
        assertEquals(820.0, processor.procesarReembolso(1000), 0.01);
    }

    @Test
    void testExcepciones() {
        assertThrows(IllegalArgumentException.class, () -> processor.calcularImpuesto(-100));
        assertThrows(IllegalArgumentException.class, () -> processor.procesarReembolso(-50));
    }
}