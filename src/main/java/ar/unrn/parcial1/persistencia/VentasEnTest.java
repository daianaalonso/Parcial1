package ar.unrn.parcial1.persistencia;

import ar.unrn.parcial1.modelo.VentaPagada;
import ar.unrn.parcial1.modelo.Ventas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VentasEnTest implements Ventas {
    private final List<VentaPagada> listaVentas = new ArrayList<>();

    @Override
    public void guardarVentas(VentaPagada venta) {
        listaVentas.add(venta);
    }

    @Override
    public List<VentaPagada> obtenerVentasPorFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        List<VentaPagada> lista = new ArrayList<>();
        for (VentaPagada v : listaVentas) {
            if (entreFechas(fechaInicio, fechaFin, v.fecha().toLocalDate()))
                lista.add(v);
        }
        return lista;
    }

    private boolean entreFechas(LocalDate fechaInicio, LocalDate fechaFin, LocalDate fecha) {
        return (fecha.isAfter(fechaInicio) || fecha.equals(fechaInicio)) && (fecha.isBefore(fechaFin) || fecha.equals(fechaFin));
    }

    public boolean estaRegistrado(LocalDateTime fecha, double litrosRegistrados, double monto) {
      return listaVentas.contains(new VentaPagada(fecha, litrosRegistrados, monto));
    }


}
