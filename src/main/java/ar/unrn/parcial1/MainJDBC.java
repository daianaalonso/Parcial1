package ar.unrn.parcial1;

import java.awt.EventQueue;

import ar.unrn.parcial1.persistencia.VentasJDBC;
import ar.unrn.parcial1.ui.Principal;

public class MainJDBC {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal(new VentasJDBC());
            }
        });

    }

}
