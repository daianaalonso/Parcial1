package ar.unrn.parcial1.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class EstacionDeServicio {
    private final Ventas ventas;

    public EstacionDeServicio(Ventas ventas) {
        this.ventas = ventas;
    }

    public double obtenerMonto(LocalDateTime fecha, String litrosCargados, Combustible combustible) {
        return new Venta(fecha, litrosCargados, combustible).monto();
    }

    public void registrarVenta(LocalDateTime fecha, String litrosCargados, Combustible combustible) {
        this.ventas.guardarVentas(new Venta(fecha, litrosCargados, combustible).pagar());
    }

    public List<VentaPagada> obtenerVentasEntreFechas(String inicio, String fin) {
        if (inicio == null || inicio.isEmpty())
            throw new RuntimeException("Debe ingresar una fecha de inicio.");
        if (fin == null || fin.isEmpty())
            throw new RuntimeException("Debe ingresar una fecha de fin.");
        try {
            LocalDate fechaInicio = LocalDate.parse(inicio, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate fechaFin = LocalDate.parse(fin, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            if (fechaInicio.isAfter(fechaFin))
                throw new RuntimeException("La fecha de inicio debe ser menor a la fecha de fin.");
            return ventas.obtenerVentasPorFechas(fechaInicio, fechaFin);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Debe ingresar la fecha con el formato correspondiente.", e);
        }
    }

}
