package modelo;

public class TareaSimple extends Tarea {

    public TareaSimple(String titulo, String descripcion, String prioridad) {
        super(titulo, descripcion, prioridad);
    }

    public void mostrarDetalle() {
        System.out.println("Tarea: " + getTitulo());
        System.out.println("Descripcion: " + getDescripcion());
        System.out.println("Prioridad: " + getPrioridad());
        System.out.println("Estado: " + getEstado());
    }
}
