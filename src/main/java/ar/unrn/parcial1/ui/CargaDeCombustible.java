package ar.unrn.parcial1.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.unrn.parcial1.modelo.*;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;

public class CargaDeCombustible extends JFrame {

    private final JPanel contentPane;
    private final JTextField litros;
    private final JComboBox<Combustible> nafta;
    private final JLabel litrosCargados;
    private final JLabel tipoDeNafta;
    private final JButton totalAPagar;
    private final JButton confirmar;
    private final JButton cancelar;
    private final EstacionDeServicio estacionDeServicio;

    public CargaDeCombustible(EstacionDeServicio estacionDeServicio) {
        this.estacionDeServicio = estacionDeServicio;
        setTitle("Carga de combustible");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 445, 240);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        litros = new JTextField();
        litros.setBounds(168, 35, 170, 30);
        contentPane.add(litros);
        litros.setColumns(10);

        litrosCargados = new JLabel("Litros cargados:");
        litrosCargados.setBounds(35, 43, 131, 14);
        contentPane.add(litrosCargados);

        tipoDeNafta = new JLabel("Tipo de nafta:");
        tipoDeNafta.setBounds(35, 97, 90, 14);
        contentPane.add(tipoDeNafta);

        nafta = new JComboBox<>();
        nafta.setBounds(168, 89, 170, 30);
        contentPane.add(nafta);
        nafta.addItem(new Comun());
        nafta.addItem(new Super());

        totalAPagar = new JButton("Total a pagar");
        totalAPagar.addActionListener(e -> {
            try {
                double monto = estacionDeServicio.obtenerMonto(LocalDateTime.now(), litros.getText(), (Combustible) nafta.getSelectedItem());
                JOptionPane.showMessageDialog(null, "Monto total a pagar: $" + monto, "AVISO",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (RuntimeException r) {
                JOptionPane.showMessageDialog(null, r.getMessage());
            }
        });
        totalAPagar.setBounds(123, 164, 137, 23);
        contentPane.add(totalAPagar);

        confirmar = new JButton("Confirmar pago");
        confirmar.addActionListener(e -> {
            try {
                estacionDeServicio.registrarVenta(LocalDateTime.now(), litros.getText(), (Combustible) nafta.getSelectedItem());
                JOptionPane.showMessageDialog(null, "Se registró el pago correctamente.", "AVISO",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (RuntimeException r) {
                JOptionPane.showMessageDialog(null, r.getMessage());
            }
        });
        confirmar.setBounds(270, 164, 137, 23);
        contentPane.add(confirmar);

        cancelar = new JButton("Cancelar");
        cancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Principal ventana = new Principal(estacionDeServicio);
                ventana.setVisible(true);
                dispose();
            }
        });
        cancelar.setBounds(10, 164, 103, 23);
        contentPane.add(cancelar);
    }
}
