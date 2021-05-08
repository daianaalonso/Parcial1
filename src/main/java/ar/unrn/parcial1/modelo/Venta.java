package ar.unrn.parcial1.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Venta {
    private LocalDateTime fecha;
    private double litrosCargados;
    private double monto;

    public Venta(LocalDateTime fecha, String litrosCargados, Combustible combustible) {
        if (litrosCargados == null || litrosCargados.isEmpty())
            throw new RuntimeException("Debe ingresar la cantidad de litros.");

        if (Double.parseDouble(litrosCargados) < 1)
            throw new RuntimeException("La cantidad de litros no puede ser 0.");

        this.fecha = fecha;
        this.litrosCargados = Double.parseDouble(litrosCargados);
        this.monto = combustible.obtenerMonto(Double.parseDouble(litrosCargados), fecha);
    }

    public Venta(LocalDateTime fecha, double litrosCargados, double monto) {
        this.fecha = fecha;
        this.litrosCargados = litrosCargados;
        this.monto = monto;
    }

    public Venta(String fecha, String litrosCargados, String monto) {
        if (fecha == null || fecha.isEmpty())
            throw new RuntimeException("Debe ingresar la fecha.");

        if (monto == null || monto.isEmpty())
            throw new RuntimeException("Debe ingresar el monto.");

        if (litrosCargados == null || litrosCargados.isEmpty())
            throw new RuntimeException("Debe ingresar la cantidad de litros.");

        if (Double.parseDouble(litrosCargados) < 1)
            throw new RuntimeException("La cantidad de litros no puede ser 0.");

        if (monto == null || monto.isEmpty())
            throw new RuntimeException("Debe ingresar el monto.");

        this.fecha = LocalDateTime.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        this.litrosCargados = Double.parseDouble(litrosCargados);
        this.monto = Double.parseDouble(monto);
    }

    public double monto() {
        return this.monto;
    }

    public LocalDateTime fecha() {
        return this.fecha;
    }

    public double litrosCargados() {
        return this.litrosCargados;
    }

}