package ar.unrn.parcial1.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.unrn.parcial1.modelo.Ventas;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

    private JPanel contentPane;
    private JLabel menu;
    private JButton consultaDeVentas;
    private JButton cargaDeCombustible;

    public Principal(Ventas ventas) {
        setTitle("Menu principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 390, 250);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);
        setResizable(false);
        setLocationRelativeTo(null);

        cargaDeCombustible = new JButton("Carga de combustibles");
        cargaDeCombustible.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CargaDeCombustible ventana = new CargaDeCombustible(ventas);
                ventana.setVisible(true);
                dispose();
            }
        });
        cargaDeCombustible.setBounds(82, 67, 206, 30);
        contentPane.add(cargaDeCombustible);

        consultaDeVentas = new JButton("Consulta de ventas");
        consultaDeVentas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ConsultaDeVentas ventana = new ConsultaDeVentas(ventas);
                ventana.setVisible(true);
                dispose();
            }
        });
        consultaDeVentas.setBounds(82, 126, 206, 30);
        contentPane.add(consultaDeVentas);

        menu = new JLabel("Menu principal");
        menu.setBounds(142, 11, 128, 30);
        contentPane.add(menu);
        setVisible(true);
    }

}
