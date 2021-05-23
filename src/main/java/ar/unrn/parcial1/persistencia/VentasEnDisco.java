package ar.unrn.parcial1.persistencia;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import ar.unrn.parcial1.modelo.VentaPagada;
import ar.unrn.parcial1.modelo.Ventas;

public class VentasEnDisco implements Ventas {
    private final String path;

    public VentasEnDisco(String path) {
        this.path = path;
    }

    @Override
    public void guardarVentas(VentaPagada venta) {
        try {
            File archivo = new File(path);
            Writer writer = new FileWriter(archivo, true);
            writer.write(venta.fecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + ", "
                    + venta.litrosCargados() + ", " + venta.monto() + "\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("No pudo guardarse la venta.", e);
        }
    }

    @Override
    public List<VentaPagada> obtenerVentasPorFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        List<VentaPagada> ventas = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(this.path));
            for (String l : lines) {
                String[] datos = l.split(", ");
                LocalDate fecha = LocalDate.parse(datos[0], DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
                if (fecha.isAfter(fechaInicio) || fecha.equals(fechaInicio)
                        && fecha.isBefore(fechaFin) || fecha.equals(fechaFin))
                    ventas.add(new VentaPagada(datos[0], datos[1], datos[2]));
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo obtener la lista de ventas", e);
        }
        return ventas;
    }

}
