package ar.unrn.parcial1.modelo;

import java.time.LocalDate;
import java.util.List;

public interface Ventas {

    void guardarVentas(VentaPagada venta);

    List<VentaPagada> obtenerVentasPorFechas(LocalDate fechaInicio, LocalDate fechaFin);
}
