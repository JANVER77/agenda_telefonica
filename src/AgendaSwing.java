import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class AgendaSwing extends JFrame {

    // Agenda (se crea cuando el usuario define la capacidad)
    private Agenda agenda;

    // Campos de texto
    private JTextField CapacidadLista = new JTextField(10);
    private JTextField Nombre = new JTextField(10);
    private JTextField Apellido = new JTextField(10);
    private JTextField Telefono = new JTextField(10);

    private JTextArea area = new JTextArea(10, 30);

    public AgendaSwing() {

        setTitle("Agenda Telefonica");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        area.setEditable(false);

        JButton btnCrear = new JButton("Crear Agenda");
        JButton btnAgregar = new JButton("Agregar");
        JButton btnListar = new JButton("Listar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnEspacios = new JButton("Espacios libres");

        // Acciones
        btnCrear.addActionListener(e -> crearAgenda());
        btnAgregar.addActionListener(e -> agregarContacto());
        btnListar.addActionListener(e -> listarContactos());
        btnBuscar.addActionListener(e -> buscarContacto());
        btnEliminar.addActionListener(e -> eliminarContacto());
        btnEspacios.addActionListener(e -> espaciosLibres());

        JPanel panelCampos = new JPanel(new GridLayout(5, 2));
        panelCampos.add(new JLabel("Capacidad de la agenda:"));
        panelCampos.add(CapacidadLista);
        panelCampos.add(new JLabel("Nombre:"));
        panelCampos.add(Nombre);
        panelCampos.add(new JLabel("Apellido:"));
        panelCampos.add(Apellido);
        panelCampos.add(new JLabel("Telefono:"));
        panelCampos.add(Telefono);
        panelCampos.add(btnCrear);

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnListar);
        panelBotones.add(btnEspacios);

        add(panelCampos, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
        add(new JScrollPane(area), BorderLayout.SOUTH);

        setVisible(true);
    }

    // ================= LOGICA =================

    private void crearAgenda() {
        try {
            int capacidad = Integer.parseInt(CapacidadLista.getText());

            if (capacidad <= 0) {
                JOptionPane.showMessageDialog(this, "Capacidad invalida");
                return;
            }

            Contacto[] contactos = new Contacto[capacidad];

            // USAMOS EL CONSTRUCTOR QUE SI FUNCIONA
            agenda = new Agenda(capacidad, contactos);

            area.setText("Agenda creada con capacidad: " + capacidad);

            CapacidadLista.setEditable(false);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un numero valido");
        }
    }

    private void agregarContacto() {

        if (agenda == null) {
            JOptionPane.showMessageDialog(this, "Primero cree la agenda");
            return;
        }

        String nombre = Nombre.getText().trim();
        String apellido = Apellido.getText().trim();
        String telefono = Telefono.getText().trim();

        if (nombre.isEmpty() || apellido.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nombre y apellido obligatorios");
            return;
        }

        if (!telefono.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, "Telefono invalido");
            return;
        }

        // Verificar espacios
        int libres = 0;
        for (Contacto c : agenda.getAgenda2()) {
            if (c == null) libres++;
        }

        if (libres == 0) {
            JOptionPane.showMessageDialog(this, "Agenda llena");
            return;
        }

        agenda.añadirContacto(new Contacto(nombre, apellido, telefono));
        area.setText("Contacto agregado");

        limpiar();
    }

    private void listarContactos() {

        if (agenda == null) return;

        area.setText("");

        Contacto[] copia = agenda.getAgenda2().clone();

        Arrays.sort(copia, (a, b) -> {
            if (a == null) return 1;
            if (b == null) return -1;
            return (a.getNombre() + a.getApellido())
                    .compareToIgnoreCase(b.getNombre() + b.getApellido());
        });

        for (Contacto c : copia) {
            if (c != null) {
                area.append(c.getNombre() + " " +
                        c.getApellido() + " - " +
                        c.getTelefono() + "\n");
            }
        }
    }

    private void buscarContacto() {

        if (agenda == null) return;

        String nombre = Nombre.getText().trim();
        String apellido = Apellido.getText().trim();

        for (Contacto c : agenda.getAgenda2()) {
            if (c != null &&
                    c.getNombre().equalsIgnoreCase(nombre) &&
                    c.getApellido().equalsIgnoreCase(apellido)) {

                area.setText("Telefono: " + c.getTelefono());
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Contacto no encontrado");
    }

    private void eliminarContacto() {

        if (agenda == null) return;

        String nombre = Nombre.getText().trim();
        String apellido = Apellido.getText().trim();

        for (Contacto c : agenda.getAgenda2()) {
            if (c != null &&
                    c.getNombre().equalsIgnoreCase(nombre) &&
                    c.getApellido().equalsIgnoreCase(apellido)) {

                agenda.eliminarContacto(c);
                area.setText("Contacto eliminado");
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Contacto no existe");
    }

    private void espaciosLibres() {

        if (agenda == null) return;

        int libres = 0;
        for (Contacto c : agenda.getAgenda2()) {
            if (c == null) libres++;
        }

        area.setText("Espacios disponibles: " + libres);
    }

    private void limpiar() {
        Nombre.setText("");
        Apellido.setText("");
        Telefono.setText("");
    }
}

