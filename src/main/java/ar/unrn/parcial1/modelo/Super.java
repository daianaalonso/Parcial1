package ar.unrn.parcial1.modelo;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class Super extends Combustible {
    private double precio = 90;

    public Super() {
    }

    public double obtenerMonto(double litros, LocalDateTime fecha) {
        double montoTotal = this.precio * litros;
        if (esDomingo(fecha))
            montoTotal = montoTotal - (0.1 * montoTotal);
        if (esSabado(fecha) && litros > 20)
            montoTotal = montoTotal - (0.12 * montoTotal);
        return montoTotal;
    }

    private boolean esDomingo(LocalDateTime fecha) {
        return (fecha.getDayOfWeek().equals(DayOfWeek.SUNDAY));
    }

    private boolean esSabado(LocalDateTime fecha) {
        return (fecha.getDayOfWeek().equals(DayOfWeek.SATURDAY));
    }

}
