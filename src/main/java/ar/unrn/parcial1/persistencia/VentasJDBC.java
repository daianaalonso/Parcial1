package ar.unrn.parcial1.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import ar.unrn.parcial1.modelo.Venta;
import ar.unrn.parcial1.modelo.Ventas;

public class VentasJDBC implements Ventas {

    @Override
    public void guardarVentas(Venta venta) {
        Connection conexion;
        try {
            conexion = obtenerConexion();
            PreparedStatement st = conexion
                    .prepareStatement("Insert into ventas (fecha, litros_cargados, monto) values(?, ?, ?)");
            st.setTimestamp(1, Timestamp.valueOf(venta.fecha()));
            st.setDouble(2, venta.litrosCargados());
            st.setDouble(3, venta.monto());
            st.executeUpdate();
            st.close();
            conexion.close();
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo guardar la venta", e);
        }
    }

    public List<Venta> obtenerVentasPorFechas(String fechaI, String fechaF) {
        List<Venta> ventas = new ArrayList<Venta>();
        Connection conexion;
        try {
            LocalDate fechaInicio = LocalDate.parse(fechaI, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate fechaFin = LocalDate.parse(fechaF, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            conexion = obtenerConexion();
            PreparedStatement st = conexion.prepareStatement("SELECT * FROM ventas ");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                if (rs.getTimestamp("fecha").toLocalDateTime().toLocalDate().isAfter(fechaInicio)
                        && rs.getTimestamp("fecha").toLocalDateTime().toLocalDate().isBefore(fechaFin)
                        || rs.getTimestamp("fecha").toLocalDateTime().toLocalDate().equals(fechaInicio)
                        || rs.getTimestamp("fecha").toLocalDateTime().toLocalDate().equals(fechaFin))
                    ventas.add(new Venta(rs.getTimestamp("fecha").toLocalDateTime(), rs.getDouble("litros_cargados"),
                            rs.getDouble("monto")));
            }
            rs.close();
            st.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("No se pudo obtener la lista de ventas", e);
        } catch (DateTimeParseException e1) {
            throw new RuntimeException("Ingrese la fecha como indica el formato.", e1);
        }
        return ventas;
    }

    private Connection obtenerConexion() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/bd_alonso?useSSL=false";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    }

}