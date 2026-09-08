package modelo;

import java.util.ArrayList;
import java.util.List;

public class TareaCompleja extends Tarea {

    private List<Tarea> subtareas;

    public TareaCompleja(String titulo, String descripcion, String prioridad) {
        super(titulo, descripcion, prioridad);
        this.subtareas = new ArrayList<>();
    }

    public void agregarSubtarea(Tarea t) {
        subtareas.add(t);
    }

    public double calcularProgreso() {
        if (subtareas.isEmpty()) {
            return getEstado().equals("Completada") ? 100.0 : 0.0;
        }
        long completadas = subtareas.stream()
                .filter(t -> t.getEstado().equals("Completada"))
                .count();
        return (completadas * 100.0) / subtareas.size();
    }

    public List<Tarea> getSubtareas() {
        return subtareas;
    }
}
