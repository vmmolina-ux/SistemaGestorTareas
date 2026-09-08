package modelo;
//Clase Abstracta
public abstract class Tarea {

    private String titulo;
    private String descripcion;
    private String prioridad;
    private String estado;

    public Tarea(String titulo, String descripcion, String prioridad) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.estado = "Pendiente";
    }

    public void cambiarEstado(String nuevo) {
        this.estado = nuevo;
    }

    public void marcarCompletada() {
        this.estado = "Completada";
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return titulo + " [" + prioridad + "] - " + estado;
    }
}
