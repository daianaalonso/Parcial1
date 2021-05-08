package ar.unrn.parcial1.modelo;

import java.util.List;

public interface Ventas {

    void guardarVentas(Venta venta);

    List<Venta> obtenerVentasPorFechas(String fechaInicio, String fechaFin);
}
