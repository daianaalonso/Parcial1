import ar.unrn.parcial1.modelo.*;
import ar.unrn.parcial1.persistencia.VentasEnTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class VentasTest {
    EstacionDeServicio estacionDeServicio;
    VentasEnTest ventas;
    Combustible naftaSuper;
    Combustible naftaComun;

    @BeforeEach
    void setUp() {
        naftaComun = new Comun();
        naftaSuper = new Super();
        ventas = new VentasEnTest();
        estacionDeServicio = new EstacionDeServicio(ventas);
    }

    @Test
    public void montoNaftaSuperDiaSabadoMasVeinteLitros() {
        double resultadoEsperado = 1663.2;
        assertEquals(resultadoEsperado, estacionDeServicio.obtenerMonto(LocalDateTime.of(2021, Month.MAY, 1, 10, 20, 30), "21", naftaSuper));
    }

    @Test
    public void montoNaftaSuperDiaDomingo() {
        double resultadoEsperado = 810;
        assertEquals(resultadoEsperado, estacionDeServicio.obtenerMonto(LocalDateTime.of(2021, Month.MAY, 2, 15, 20, 30), "10", naftaSuper));
    }

    @Test
    public void montoNaftaComunALaManiana() {
        double resultadoEsperado = 665;
        assertEquals(resultadoEsperado, estacionDeServicio.obtenerMonto(LocalDateTime.of(2021, Month.MAY, 17, 9, 30, 10), "10", naftaComun));
    }

    @Test
    public void montoNaftaSuperDiaDeSemana() {
        double resultadoEsperado = 900;
        assertEquals(resultadoEsperado, estacionDeServicio.obtenerMonto(LocalDateTime.of(2021, Month.MAY, 6, 15, 20, 30), "10", naftaSuper));
    }

    @Test
    public void montoNaftaSuperSabadoMenosVeinteLitros() {
        double resultadoEsperado = 900;
        assertEquals(resultadoEsperado, estacionDeServicio.obtenerMonto(LocalDateTime.of(2021, Month.MAY, 1, 15, 10, 20), "10", naftaSuper));
    }

    @Test
    public void montoNaftaComunDespuesDeLaManiana() {
        double resultadoEsperado = 700;
        assertEquals(resultadoEsperado, estacionDeServicio.obtenerMonto(LocalDateTime.of(2021, Month.APRIL, 17, 17, 30, 10), "10", naftaComun));
    }

    @Test
    public void estaRegistrado() {
        LocalDateTime fecha = LocalDateTime.now();
        double monto = naftaComun.obtenerMonto(10, fecha);
        estacionDeServicio.registrarVenta(fecha, "10", naftaComun);
        assertTrue(ventas.estaRegistrado(fecha, 10, monto));
    }

    @Test
    public void existenVentasEntreFechas() {
        LocalDateTime fecha = LocalDateTime.now();
        LocalDate inicio = LocalDate.now().minusDays(1);
        LocalDate fin = LocalDate.now().plusDays(1);
        estacionDeServicio.registrarVenta(fecha, "10", naftaComun);
        assertFalse(estacionDeServicio.obtenerVentasEntreFechas(inicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                fin.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).isEmpty());
    }

}
