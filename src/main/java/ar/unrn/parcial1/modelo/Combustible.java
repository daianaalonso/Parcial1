package ar.unrn.parcial1.modelo;

import java.time.LocalDateTime;

public abstract class Combustible {

    public Combustible() {
    }

    public abstract double obtenerMonto(double litros, LocalDateTime fecha);

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
