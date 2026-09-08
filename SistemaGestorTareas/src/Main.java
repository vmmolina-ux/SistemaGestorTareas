import modelo.Proyecto;
import vista.VentanaPrincipal;

import javax.swing.SwingUtilities;

/**
 * Punto de entrada del programa. No contiene logica propia:
 * solo crea el proyecto y llama a la vista.
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Proyecto proyecto = new Proyecto("Rediseño de sitio web");
            VentanaPrincipal ventana = new VentanaPrincipal(proyecto);
            ventana.mostrar();
        });
    }
}
