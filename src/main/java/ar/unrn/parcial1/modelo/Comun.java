package ar.unrn.parcial1.modelo;

import java.time.LocalDateTime;

public class Comun extends Combustible {
    private static final double PRECIO = 70;
    private static final double DESCUENTO_AM = 0.05;
    private static final double HORA_INICIO = 8;
    private static final double HORA_LIMITE = 10;

    public Comun() {
    }

    public double obtenerMonto(double litros, LocalDateTime fecha) {
        double montoTotal = PRECIO * litros;
        if (cargoALaManiana(fecha))
            montoTotal = montoTotal - (DESCUENTO_AM * montoTotal);
        return montoTotal;
    }

    private boolean cargoALaManiana(LocalDateTime fecha) {
        return (fecha.getHour() >= HORA_INICIO && fecha.getHour() <= HORA_LIMITE);
    }

}