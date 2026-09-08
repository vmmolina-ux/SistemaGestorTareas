package vista;

import modelo.Proyecto;
import modelo.Tarea;
import modelo.TareaCompleja;
import modelo.TareaSimple;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Interfaz grafica sencilla para crear tareas, asignarlas,
 * agregar subtareas y consultar el avance del proyecto.
 */
public class VentanaPrincipal extends JFrame {

    private final Proyecto proyecto;

    private final JTextField txtTitulo = new JTextField(15);
    private final JTextField txtDescripcion = new JTextField(15);
    private final JTextField txtMiembro = new JTextField(10);
    private final JComboBox<String> comboPrioridad = new JComboBox<>(new String[]{"Alta", "Media", "Baja"});
    private final JRadioButton radioSimple = new JRadioButton("Simple", true);
    private final JRadioButton radioCompleja = new JRadioButton("Compleja");

    private final DefaultListModel<Tarea> modeloLista = new DefaultListModel<>();
    private final JList<Tarea> listaTareas = new JList<>(modeloLista);

    private final JLabel lblAvance = new JLabel("Avance general: 0.0%");

    public VentanaPrincipal(Proyecto proyecto) {
        super("Gestor de Tareas y Proyectos - " + proyecto.getNombre());
        this.proyecto = proyecto;
        construirInterfaz();
    }

    private void construirInterfaz() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        add(construirPanelFormulario(), BorderLayout.NORTH);
        add(new JScrollPane(listaTareas), BorderLayout.CENTER);
        add(construirPanelAcciones(), BorderLayout.SOUTH);

        setSize(520, 420);
        setLocationRelativeTo(null);
    }

    private JPanel construirPanelFormulario() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Nueva tarea"));

        ButtonGroup grupoTipo = new ButtonGroup();
        grupoTipo.add(radioSimple);
        grupoTipo.add(radioCompleja);

        panel.add(new JLabel("Titulo:"));
        panel.add(txtTitulo);
        panel.add(new JLabel("Descripcion:"));
        panel.add(txtDescripcion);
        panel.add(new JLabel("Prioridad:"));
        panel.add(comboPrioridad);
        panel.add(new JLabel("Miembro asignado:"));
        panel.add(txtMiembro);

        JPanel panelTipo = new JPanel();
        panelTipo.add(radioSimple);
        panelTipo.add(radioCompleja);

        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.add(panel, BorderLayout.CENTER);
        contenedor.add(panelTipo, BorderLayout.SOUTH);
        return contenedor;
    }

    private JPanel construirPanelAcciones() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));

        JButton btnCrear = new JButton("Crear y asignar tarea");
        btnCrear.addActionListener(e -> crearTarea());

        JButton btnSubtarea = new JButton("Agregar subtarea a la seleccionada");
        btnSubtarea.addActionListener(e -> agregarSubtarea());

        JButton btnCompletar = new JButton("Marcar seleccionada como completada");
        btnCompletar.addActionListener(e -> marcarCompletada());

        JButton btnAvance = new JButton("Calcular avance general");
        btnAvance.addActionListener(e -> actualizarAvance());

        panel.add(btnCrear);
        panel.add(btnSubtarea);
        panel.add(btnCompletar);
        panel.add(btnAvance);

        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.add(panel, BorderLayout.CENTER);
        contenedor.add(lblAvance, BorderLayout.SOUTH);
        return contenedor;
    }

    private void crearTarea() {
        String titulo = txtTitulo.getText().trim();
        String descripcion = txtDescripcion.getText().trim();
        String prioridad = (String) comboPrioridad.getSelectedItem();
        String miembro = txtMiembro.getText().trim();

        if (titulo.isEmpty() || miembro.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El titulo y el miembro son obligatorios.");
            return;
        }

        Tarea tarea = radioCompleja.isSelected()
                ? new TareaCompleja(titulo, descripcion, prioridad)
                : new TareaSimple(titulo, descripcion, prioridad);

        proyecto.asignarTarea(tarea, miembro);
        modeloLista.addElement(tarea);

        txtTitulo.setText("");
        txtDescripcion.setText("");
        txtMiembro.setText("");
        actualizarAvance();
    }

    private void agregarSubtarea() {
        Tarea seleccionada = listaTareas.getSelectedValue();
        if (!(seleccionada instanceof TareaCompleja)) {
            JOptionPane.showMessageDialog(this, "Selecciona una tarea compleja de la lista.");
            return;
        }

        String tituloSub = JOptionPane.showInputDialog(this, "Titulo de la subtarea:");
        if (tituloSub == null || tituloSub.trim().isEmpty()) {
            return;
        }

        TareaSimple subtarea = new TareaSimple(tituloSub.trim(), "", "Media");
        ((TareaCompleja) seleccionada).agregarSubtarea(subtarea);
        JOptionPane.showMessageDialog(this, "Subtarea agregada a: " + seleccionada.getTitulo());
        actualizarAvance();
    }

    private void marcarCompletada() {
        Tarea seleccionada = listaTareas.getSelectedValue();
        if (seleccionada == null) {
            JOptionPane.showMessageDialog(this, "Selecciona una tarea de la lista.");
            return;
        }
        seleccionada.marcarCompletada();
        listaTareas.repaint();
        actualizarAvance();
    }

    private void actualizarAvance() {
        double avance = proyecto.calcularAvance();
        lblAvance.setText(String.format("Avance general: %.1f%%", avance));
    }

    public void mostrar() {
        setVisible(true);
    }
}
