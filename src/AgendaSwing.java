import javax.swing.*;          // Componentes graficos de Swing (JFrame, JButton, etc)
import java.awt.*;             // Layouts y contenedores
import java.util.Arrays;       // Para ordenar los contactos

// Clase que representa la interfaz grafica
// Extiende JFrame porque es una ventana
public class AgendaSwing extends JFrame {

    // Agenda principal (usa la logica existente, NO se modifica)
    private Agenda agenda = new Agenda(new Contacto[10]);

    // Campos de texto para ingresar datos del contacto
    private JTextField txtNombre = new JTextField(10);
    private JTextField txtApellido = new JTextField(10);
    private JTextField txtTelefono = new JTextField(10);

    // Area de texto donde se muestran los resultados
    private JTextArea area = new JTextArea(10, 30);

    // Constructor: aqui se construye la ventana
    public AgendaSwing() {

        // Titulo de la ventana
        setTitle("Agenda Telefonica");

        // Tamano de la ventana
        setSize(450, 400);

        // Cierra la aplicacion al cerrar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Centra la ventana en la pantalla
        setLocationRelativeTo(null);

        // Evita que el usuario escriba en el area de resultados
        area.setEditable(false);

        // Botones del menu (equivalente al menu por consola)
        JButton btnAgregar = new JButton("Agregar");
        JButton btnListar = new JButton("Listar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnEspacios = new JButton("Espacios libres");

        // Asignacion de acciones a cada boton
        btnAgregar.addActionListener(e -> agregarContacto());
        btnListar.addActionListener(e -> listarContactos());
        btnBuscar.addActionListener(e -> buscarContacto());
        btnEliminar.addActionListener(e -> eliminarContacto());
        btnEspacios.addActionListener(e -> espaciosLibres());

        // Panel para los campos de texto (nombre, apellido, telefono)
        JPanel panelCampos = new JPanel(new GridLayout(3, 2));
        panelCampos.add(new JLabel("Nombre:"));
        panelCampos.add(txtNombre);
        panelCampos.add(new JLabel("Apellido:"));
        panelCampos.add(txtApellido);
        panelCampos.add(new JLabel("Telefono:"));
        panelCampos.add(txtTelefono);

        // Panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnListar);
        panelBotones.add(btnEspacios);

        // Se agregan los paneles a la ventana principal
        add(panelCampos, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
        add(new JScrollPane(area), BorderLayout.SOUTH);

        // Hace visible la ventana
        setVisible(true);
    }

    // =========================
    // METODOS DE FUNCIONALIDAD
    // =========================

    // Agrega un nuevo contacto a la agenda
    private void agregarContacto() {

        // Obtiene los datos escritos por el usuario
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String telefono = txtTelefono.getText().trim();

        // Validacion: nombre y apellido no pueden estar vacios
        if (nombre.isEmpty() || apellido.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Nombre y apellido no pueden estar vacios");
            return;
        }

        // Validacion: telefono debe tener 10 digitos
        if (!telefono.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this,
                    "El telefono debe tener 10 digitos");
            return;
        }

        // Validacion: no permitir contactos duplicados
        for (Contacto c : agenda.getAgenda2()) {
            if (c != null &&
                    c.getNombre().equalsIgnoreCase(nombre) &&
                    c.getApellido().equalsIgnoreCase(apellido)) {

                JOptionPane.showMessageDialog(this,
                        "El contacto ya existe");
                return;
            }
        }

        // Si pasa todas las validaciones, se agrega el contacto
        agenda.añadirContacto(new Contacto(nombre, apellido, telefono));

        // Mensaje en el area de texto
        area.append("Contacto agregado: " + nombre + " " + apellido + "\n");

        // Limpia los campos de texto
        limpiar();
    }

    // Muestra todos los contactos ordenados alfabeticamente
    private void listarContactos() {

        // Limpia el area de resultados
        area.setText("");

        // Se crea una copia del arreglo para no modificar el original
        Contacto[] copia = agenda.getAgenda2().clone();

        // Ordena los contactos por nombre y apellido (ignorando mayusculas)
        Arrays.sort(copia, (a, b) -> {
            if (a == null) return 1;
            if (b == null) return -1;
            String n1 = a.getNombre() + a.getApellido();
            String n2 = b.getNombre() + b.getApellido();
            return n1.compareToIgnoreCase(n2);
        });

        // Muestra los contactos ordenados
        for (Contacto c : copia) {
            if (c != null) {
                area.append(
                        c.getNombre() + " " +
                                c.getApellido() + " - " +
                                c.getTelefono() + "\n"
                );
            }
        }
    }

    // Busca un contacto por nombre y apellido
    private void buscarContacto() {

        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();

        // Recorre la agenda buscando coincidencia
        for (Contacto c : agenda.getAgenda2()) {
            if (c != null &&
                    c.getNombre().equalsIgnoreCase(nombre) &&
                    c.getApellido().equalsIgnoreCase(apellido)) {

                // Si lo encuentra, muestra el telefono
                area.setText("Telefono: " + c.getTelefono());
                return;
            }
        }

        // Si no se encuentra el contacto
        JOptionPane.showMessageDialog(this,
                "Contacto no encontrado");
    }

    // Elimina un contacto existente
    private void eliminarContacto() {

        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();

        // Busca el contacto para eliminarlo
        for (Contacto c : agenda.getAgenda2()) {
            if (c != null &&
                    c.getNombre().equalsIgnoreCase(nombre) &&
                    c.getApellido().equalsIgnoreCase(apellido)) {

                agenda.eliminarContacto(c);
                area.setText("Contacto eliminado");
                return;
            }
        }

        // Si no existe el contacto
        JOptionPane.showMessageDialog(this,
                "No se puede eliminar, no existe");
    }

    // Muestra cuantos espacios libres quedan en la agenda
    private void espaciosLibres() {

        int libres = 0;

        // Cuenta las posiciones nulas del arreglo
        for (Contacto c : agenda.getAgenda2()) {
            if (c == null) libres++;
        }

        area.setText("Espacios disponibles: " + libres);
    }

    // Limpia los campos de texto despues de agregar un contacto
    private void limpiar() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
    }
}
