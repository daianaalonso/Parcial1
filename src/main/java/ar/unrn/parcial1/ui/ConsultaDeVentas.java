package ar.unrn.parcial1.ui;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ar.unrn.parcial1.modelo.EstacionDeServicio;
import ar.unrn.parcial1.modelo.VentaPagada;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultaDeVentas extends JFrame {

    private final JPanel contentPane;
    private final JTable table;
    private final DefaultTableModel modelo;
    private List<VentaPagada> listaVentas;
    private final JTextField fechaInicio;
    private final JTextField fechaFin;
    private final JButton buscar;
    private final JButton cancelar;
    private final JLabel inicio;
    private final JLabel fin;
    private final JLabel formatoInicio;
    private final JLabel formatoFin;
    private final EstacionDeServicio estacionDeServicio;

    public ConsultaDeVentas(EstacionDeServicio estacionDeServicio) {
        this.estacionDeServicio = estacionDeServicio;
        this.listaVentas = new ArrayList<>();
        setTitle("Consulta de ventas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 520, 360);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);
        setResizable(false);
        setLocationRelativeTo(null);

        table = new JTable();
        table.setBounds(0, 0, 432, 237);
        String[] titulos = {"FECHA", "LITROS CARGADOS", "MONTO"};
        modelo = new DefaultTableModel(new Object[][]{}, titulos) {
        };
        table.setModel(modelo);
        JScrollPane panel = new JScrollPane(table);
        panel.setBounds(1, 5, 503, 255);
        contentPane.add(panel);

        fechaInicio = new JTextField();
        fechaInicio.setBounds(27, 286, 119, 20);
        contentPane.add(fechaInicio);
        fechaInicio.setColumns(10);

        fechaFin = new JTextField();
        fechaFin.setBounds(156, 286, 109, 20);
        contentPane.add(fechaFin);
        fechaFin.setColumns(10);

        buscar = new JButton("Buscar");
        buscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    modelo.setRowCount(0);
                    listaVentas = estacionDeServicio.obtenerVentasEntreFechas(fechaInicio.getText(), fechaFin.getText());
                    for (VentaPagada v : listaVentas) {
                        modelo.addRow(new Object[]{v.fecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")), v.litrosCargados(), v.monto()});
                    }
                } catch (RuntimeException r) {
                    JOptionPane.showMessageDialog(null, r.getMessage());
                }
            }
        });
        buscar.setBounds(400, 285, 89, 23);
        contentPane.add(buscar);

        cancelar = new JButton("Cancelar");
        cancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Principal ventana = new Principal(estacionDeServicio);
                ventana.setVisible(true);
                dispose();
            }
        });
        cancelar.setBounds(301, 285, 89, 23);
        contentPane.add(cancelar);

        inicio = new JLabel("Fecha inicio:");
        inicio.setBounds(27, 271, 101, 14);
        contentPane.add(inicio);

        fin = new JLabel("Fecha fin:");
        fin.setBounds(156, 271, 109, 14);
        contentPane.add(fin);

        formatoInicio = new JLabel("dd/mm/aaaa");
        formatoInicio.setFont(new Font("Tahoma", Font.ITALIC, 11));
        formatoInicio.setBounds(27, 306, 77, 14);
        contentPane.add(formatoInicio);

        formatoFin = new JLabel("dd/mm/aaaa");
        formatoFin.setFont(new Font("Tahoma", Font.ITALIC, 11));
        formatoFin.setBounds(156, 306, 77, 14);
        contentPane.add(formatoFin);
    }

}
