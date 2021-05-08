package ar.unrn.parcial1.modelo;

import java.time.LocalDateTime;

public class Comun extends Combustible {
    private double precio = 70;

    public Comun() {
    }

    public double obtenerMonto(double litros, LocalDateTime fecha) {
        double montoTotal = this.precio * litros;
        if (cargoALaMañana(fecha))
            montoTotal = montoTotal - (0.05 * montoTotal);
        return montoTotal;
    }

    private boolean cargoALaMañana(LocalDateTime fecha) {
        return (fecha.getHour() >= 8 && fecha.getHour() <= 10);
    }

}