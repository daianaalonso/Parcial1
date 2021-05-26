package ar.unrn.parcial1.modelo;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class Super extends Combustible {
    private static final double PRECIO = 90;
    private static final double DESCUENTO_DOMINGO = 0.1;
    private static final double SUPERAR_LITROS = 20;
    private static final double DESCUENTO_SABADO = 0.12;


    public Super() {
    }

    public double obtenerMonto(double litros, LocalDateTime fecha) {
        double montoTotal = PRECIO * litros;
        if (esDomingo(fecha))
            montoTotal = montoTotal - (DESCUENTO_DOMINGO * montoTotal);
        if (esSabado(fecha) && litros > SUPERAR_LITROS)
            montoTotal = montoTotal - (DESCUENTO_SABADO * montoTotal);
        return montoTotal;
    }

    private boolean esDomingo(LocalDateTime fecha) {
        return (fecha.getDayOfWeek().equals(DayOfWeek.SUNDAY));
    }

    private boolean esSabado(LocalDateTime fecha) {
        return (fecha.getDayOfWeek().equals(DayOfWeek.SATURDAY));
    }

}
