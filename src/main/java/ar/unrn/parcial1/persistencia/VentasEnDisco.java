package ar.unrn.parcial1.persistencia;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import ar.unrn.parcial1.modelo.Venta;
import ar.unrn.parcial1.modelo.Ventas;

public class VentasEnDisco implements Ventas {
    private String path;

    public VentasEnDisco(String path) {
        this.path = path;
    }

    @Override
    public void guardarVentas(Venta venta) {
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
    public List<Venta> obtenerVentasPorFechas(String fechaI, String fechaF) {
        List<Venta> ventas = new ArrayList<Venta>();
        try {
            LocalDate fechaInicio = LocalDate.parse(fechaI, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate fechaFin = LocalDate.parse(fechaF, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            List<String> lines = Files.readAllLines(Paths.get(this.path));
            for (String l : lines) {
                String[] datos = l.split(", ");
                LocalDate fecha = LocalDate.parse(datos[0], DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
                if (fecha.isAfter(fechaInicio) && fecha.isBefore(fechaFin) || fecha.equals(fechaInicio)
                        || fecha.equals(fechaFin))
                    ventas.add(new Venta(datos[0], datos[1], datos[2]));
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo obtener la lista de ventas", e);
        } catch (DateTimeParseException e1) {
            throw new RuntimeException("Ingrese la fecha como indica el formato.", e1);
        }
        return ventas;
    }

}
