import modelo.Proyecto;
import vista.VentanaPrincipal;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Proyecto proyecto = new Proyecto("Rediseño de sitio web");
            VentanaPrincipal ventana = new VentanaPrincipal(proyecto);
            ventana.mostrar();
        });
    }
}
