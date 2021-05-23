package ar.unrn.parcial1.modelo;

import java.time.LocalDateTime;

public class Comun extends Combustible {
    private static final double PRECIO = 70;

    public Comun() {
    }

    public double obtenerMonto(double litros, LocalDateTime fecha) {
        double montoTotal = PRECIO * litros;
        if (cargoALaManiana(fecha))
            montoTotal = montoTotal - (0.05 * montoTotal);
        return montoTotal;
    }

    private boolean cargoALaManiana(LocalDateTime fecha) {
        return (fecha.getHour() >= 8 && fecha.getHour() <= 10);
    }

}