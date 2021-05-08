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
import java.time.LocalDateTime;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CargaDeCombustible extends JFrame {

    private JPanel contentPane;
    private JTextField litros;
    private JComboBox<String> nafta;
    private JLabel litrosCargados;
    private JLabel tipoDeNafta;
    private JButton totalAPagar;
    private JButton confirmar;
    private JButton cancelar;
    private Ventas ventas;

    public CargaDeCombustible(Ventas ventas) {
        this.ventas = ventas;
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

        nafta = new JComboBox<String>();
        nafta.setBounds(168, 89, 170, 30);
        contentPane.add(nafta);
        nafta.addItem("Comun");
        nafta.addItem("Super");

        totalAPagar = new JButton("Total a pagar");
        totalAPagar.addActionListener(e -> {
            double monto = 0;
            Combustible combustible = this.tipoDeCombustible(nafta.getSelectedItem().toString());
            try {
                monto = new Venta(LocalDateTime.now(), litros.getText(), combustible).monto();
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
            Combustible combustible = this.tipoDeCombustible(nafta.getSelectedItem().toString());
            try {
                ventas.guardarVentas(new Venta(LocalDateTime.now(), litros.getText(), combustible));
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
                Principal ventana = new Principal(ventas);
                ventana.setVisible(true);
                dispose();
            }
        });
        cancelar.setBounds(10, 164, 103, 23);
        contentPane.add(cancelar);
    }

    private Combustible tipoDeCombustible(String tipo) {
        Combustible combustible;
        if (tipo.equals("Comun"))
            combustible = new Comun();
        else
            combustible = new Super();
        return combustible;
    }

}
