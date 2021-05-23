package ar.unrn.parcial1.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class VentaPagada {
    private final LocalDateTime fecha;
    private final double litrosCargados;
    private final double monto;

    public VentaPagada(LocalDateTime fecha, double litrosCargados, double monto) {
        this.fecha = fecha;
        this.litrosCargados = litrosCargados;
        this.monto = monto;
    }

    public VentaPagada(String fecha, String litrosCargados, String monto) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VentaPagada that = (VentaPagada) o;
        return Double.compare(that.litrosCargados, litrosCargados) == 0 && Double.compare(that.monto, monto) == 0 && fecha.equals(that.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fecha, litrosCargados, monto);
    }
}
