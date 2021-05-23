package ar.unrn.parcial1.modelo;

import java.time.LocalDateTime;

public class Venta {
    private final LocalDateTime fecha;
    private final double litrosCargados;
    private final Combustible combustible;

    public Venta(LocalDateTime fecha, String litrosCargados, Combustible combustible) {
        if (litrosCargados == null || litrosCargados.isEmpty())
            throw new RuntimeException("Debe ingresar la cantidad de litros.");
        try {
            this.litrosCargados = Double.parseDouble(litrosCargados);
            if (this.litrosCargados < 1)
                throw new RuntimeException("La cantidad de litros no puede ser 0.");
        } catch (NumberFormatException e) {
            throw new RuntimeException("Debe ingresar solo números.", e);
        }
        this.fecha = fecha;
        this.combustible = combustible;
    }

    public double monto() {
        return combustible.obtenerMonto(this.litrosCargados, this.fecha);
    }

    public VentaPagada pagar() {
        return new VentaPagada(this.fecha, this.litrosCargados, monto());
    }

}