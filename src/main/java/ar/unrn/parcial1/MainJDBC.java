package ar.unrn.parcial1;

import java.awt.EventQueue;

import ar.unrn.parcial1.modelo.EstacionDeServicio;
import ar.unrn.parcial1.persistencia.VentasJDBC;
import ar.unrn.parcial1.ui.Principal;

public class MainJDBC {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            EstacionDeServicio estacionDeServicio = new EstacionDeServicio(new VentasJDBC());

            public void run() {
                new Principal(estacionDeServicio);
            }
        });

    }

}
