import ar.unrn.parcial1.modelo.*;
import ar.unrn.parcial1.persistencia.VentasEnTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class VentasTest {
    VentasEnTest ventas = new VentasEnTest();
    Combustible naftaSuper = new Super();
    Combustible naftaComun = new Comun();

    @Test
    public void montoNaftaSuperDiaSabadoMasVeinteLitros() {
        Venta venta = new Venta(LocalDateTime.of(2021, 05, 01, 10, 20, 30), "21", naftaSuper);
        ventas.guardarVentas(venta);
        double resultadoEsperado = 1663.2;
        assertEquals(resultadoEsperado, venta.monto());
    }

    @Test
    public void montoNaftaSuperDiaDomingo() {
        Venta venta = new Venta(LocalDateTime.of(2021, 05, 02, 15, 20, 30), "10", naftaSuper);
        ventas.guardarVentas(venta);
        double resultadoEsperado = 810;
        assertEquals(resultadoEsperado, venta.monto());
    }

    @Test
    public void montoNaftaComunALaManiana() {
        Venta venta = new Venta(LocalDateTime.of(2021, 04, 17, 9, 30, 10), "10", naftaComun);
        ventas.guardarVentas(venta);
        double resultadoEsperado = 665;
        assertEquals(resultadoEsperado, venta.monto());
    }

    @Test
    public void montoNaftaSuperDiaDeSemana() {
        Venta venta = new Venta(LocalDateTime.of(2021, 05, 06, 15, 20, 30), "10", naftaSuper);
        ventas.guardarVentas(venta);
        double resultadoEsperado = 900;
        assertEquals(resultadoEsperado, venta.monto());
    }

    @Test
    public void montoNaftaSuperSabadoMenosVeinteLitros() {
        Venta venta = new Venta(LocalDateTime.of(2021, 05, 01, 15, 20, 30), "10", naftaSuper);
        ventas.guardarVentas(venta);
        double resultadoEsperado = 900;
        assertEquals(resultadoEsperado, venta.monto());
    }

    @Test
    public void montoNaftaComunDespuesDeLaMañana(){
        Venta venta = new Venta(LocalDateTime.of(2021, 04, 17, 17, 30, 10), "10", naftaComun);
        ventas.guardarVentas(venta);
        double resultadoEsperado = 700;
        assertEquals(resultadoEsperado, venta.monto());
    }


}
