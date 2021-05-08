package ar.unrn.parcial1;

import java.awt.EventQueue;

import ar.unrn.parcial1.persistencia.VentasEnDisco;
import ar.unrn.parcial1.ui.Principal;

public class MainDisco {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal(new VentasEnDisco("C:\\Users\\Daiana\\Documentos\\Objetos2\\ventas.txt"));
            }
        });

    }
}
