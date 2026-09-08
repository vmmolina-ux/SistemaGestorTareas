package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Gestiona la asignacion de tareas a los miembros del equipo
 * y calcula el porcentaje de avance general.
 */
public class Proyecto {

    private String nombre;
    private List<Tarea> tareas;
    private Map<String, List<Tarea>> equipo;

    public Proyecto(String nombre) {
        this.nombre = nombre;
        this.tareas = new ArrayList<>();
        this.equipo = new HashMap<>();
    }

    public void asignarTarea(Tarea t, String miembro) {
        tareas.add(t);
        equipo.computeIfAbsent(miembro, k -> new ArrayList<>()).add(t);
    }

    public double calcularAvance() {
        if (tareas.isEmpty()) {
            return 0.0;
        }
        double sumaProgreso = 0.0;
        for (Tarea t : tareas) {
            if (t instanceof TareaCompleja) {
                sumaProgreso += ((TareaCompleja) t).calcularProgreso();
            } else {
                sumaProgreso += t.getEstado().equals("Completada") ? 100.0 : 0.0;
            }
        }
        return sumaProgreso / tareas.size();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public Map<String, List<Tarea>> getEquipo() {
        return equipo;
    }
}
