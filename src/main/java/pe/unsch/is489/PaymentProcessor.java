package pe.unsch.is489;

/**
 * Sistema de Procesamiento de Pagos con TDD
 * Laboratorio 6 - IS489
 */
public class PaymentProcessor {

    private static final double IMPUESTO_IGV = 0.18;        // 18% IGV Perú
    private static final double MONTO_MINIMO = 100.0;
    private static final double LIMITE_DIARIO = 5000.0;

    private double montoProcesadoHoy = 0.0;

    /**
     * Calcula el impuesto (IGV) sobre un monto
     */
    public double calcularImpuesto(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a 0");
        }
        return monto * IMPUESTO_IGV;
    }

    /**
     * Valida si el monto cumple con el mínimo requerido
     */
    public boolean validarMonto(double monto) {
        return monto >= MONTO_MINIMO;
    }

    /**
     * Valida si no se excede el límite diario
     */
    public boolean validarLimiteDiario(double monto) {
        return (montoProcesadoHoy + monto) <= LIMITE_DIARIO;
    }

    /**
     * Procesa un pago completo con validaciones
     */
    public boolean procesarPago(double monto) {
        if (!validarMonto(monto)) {
            return false;
        }
        if (!validarLimiteDiario(monto)) {
            return false;
        }
        // Simulación de procesamiento
        montoProcesadoHoy += monto;
        return true;
    }

    /**
     * Procesa el reembolso (devuelve monto sin impuesto)
     */
    public double procesarReembolso(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a reembolsar debe ser mayor a 0");
        }
        return monto * (1 - IMPUESTO_IGV);
    }

    /**
     * Reinicia el contador diario (útil para pruebas)
     */
    public void reiniciarProcesosDiarios() {
        montoProcesadoHoy = 0.0;
    }

    public double getMontoProcesadoHoy() {
        return montoProcesadoHoy;
    }
}