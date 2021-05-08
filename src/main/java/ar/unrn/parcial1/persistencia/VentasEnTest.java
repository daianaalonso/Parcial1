package ar.unrn.parcial1.persistencia;

import ar.unrn.parcial1.modelo.Venta;
import ar.unrn.parcial1.modelo.Ventas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class VentasEnTest implements Ventas {
    private final List<Venta> listaVentas = new ArrayList<>();

    @Override
    public void guardarVentas(Venta venta) {
        listaVentas.add(venta);
    }

    @Override
    public List<Venta> obtenerVentasPorFechas(String fechaI, String fechaF) {
        LocalDate fechaInicio = LocalDate.parse(fechaI, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate fechaFin = LocalDate.parse(fechaF, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        List<Venta> lista = new ArrayList<>();
        for (Venta v : listaVentas) {
            if (entreFechas(fechaInicio, fechaFin, v.fecha().toLocalDate()))
                lista.add(v);
        }
        return lista;
    }

    private boolean entreFechas(LocalDate fechaInicio, LocalDate fechaFin, LocalDate fecha) {
        return (fecha.isAfter(fechaInicio) || fecha.equals(fechaInicio)) && (fecha.isBefore(fechaFin) || fecha.equals(fechaFin));
    }

    public boolean estaRegistrado(LocalDateTime fecha, double litrosRegistrados, double monto) {
        return listaVentas.contains(new Venta(fecha, litrosRegistrados, monto));
    }
}
