package formularios;

import formularios.componentes.BotonRedondeado;
import formularios.componentes.PanelRedondeado;

import dao.PacienteDAO;
import modelo.Paciente;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

import com.toedter.calendar.JDateChooser;

public class GestionarPaciente extends JFrame {

    private JTextField txtBuscarCedula;

    private JTextField txtCedula;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtDireccion;

    private JDateChooser calendarioFechaNacimiento;

    private JButton btnBuscar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnLimpiar;

    private Paciente pacienteActual;


    public GestionarPaciente() {

        setTitle("Gestionar Paciente");

        setSize(760, 650);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        crearFormulario();
    }


    private void crearFormulario() {

        JPanel principal =
                new JPanel(new BorderLayout());

        principal.setBackground(
                Estilo.FONDO
        );


        // ==========================================
        // ENCABEZADO
        // ==========================================

        JPanel encabezado =
                new JPanel();

        encabezado.setBackground(
                Estilo.FONDO
        );

        encabezado.setLayout(
                new BoxLayout(
                        encabezado,
                        BoxLayout.Y_AXIS
                )
        );

        encabezado.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        35,
                        15,
                        35
                )
        );


        JLabel titulo =
                new JLabel(
                        "Gestionar paciente"
                );

        titulo.setFont(
                Estilo.TITULO
        );

        titulo.setForeground(
                Estilo.MORADO_OSCURO
        );


        JLabel descripcion =
                new JLabel(
                        "Busque un paciente para modificar o eliminar sus datos."
                );

        descripcion.setFont(
                Estilo.SUBTITULO
        );

        descripcion.setForeground(
                Estilo.GRIS_TEXTO
        );


        encabezado.add(titulo);

        encabezado.add(
                Box.createVerticalStrut(5)
        );

        encabezado.add(descripcion);


        principal.add(
                encabezado,
                BorderLayout.NORTH
        );


        // ==========================================
        // CONTENEDOR
        // ==========================================

        JPanel contenedor =
                new JPanel(
                        new BorderLayout()
                );

        contenedor.setBackground(
                Estilo.FONDO
        );

        contenedor.setBorder(
                BorderFactory.createEmptyBorder(
                        5,
                        35,
                        30,
                        35
                )
        );


        // ==========================================
        // TARJETA
        // ==========================================

        PanelRedondeado tarjeta =
                new PanelRedondeado(25);

        tarjeta.setColorFondo(
                Estilo.BLANCO
        );

        tarjeta.setLayout(
                new BorderLayout()
        );

        tarjeta.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        30,
                        25,
                        30
                )
        );


        // ==========================================
        // BÚSQUEDA
        // ==========================================

        JLabel lblBuscar =
                new JLabel(
                        "Buscar paciente"
                );

        lblBuscar.setFont(
                Estilo.ENCABEZADO
        );

        lblBuscar.setForeground(
                Estilo.MORADO_OSCURO
        );


        JPanel panelBusqueda =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                15
                        )
                );

        panelBusqueda.setBackground(
                Estilo.BLANCO
        );


        txtBuscarCedula =
                crearCampo();

        txtBuscarCedula.setPreferredSize(
                new Dimension(250, 38)
        );


        btnBuscar =
                new BotonRedondeado(
                        "Buscar",
                        Estilo.MORADO_MEDIO,
                        Estilo.MORADO_OSCURO
                );

        btnBuscar.setPreferredSize(
                new Dimension(110, 38)
        );


        panelBusqueda.add(
                new JLabel("Cédula:")
        );

        panelBusqueda.add(
                txtBuscarCedula
        );

        panelBusqueda.add(
                btnBuscar
        );


        JPanel parteSuperior =
                new JPanel(
                        new BorderLayout()
                );

        parteSuperior.setBackground(
                Estilo.BLANCO
        );

        parteSuperior.add(
                lblBuscar,
                BorderLayout.NORTH
        );

        parteSuperior.add(
                panelBusqueda,
                BorderLayout.CENTER
        );


        tarjeta.add(
                parteSuperior,
                BorderLayout.NORTH
        );


        // ==========================================
        // FORMULARIO
        // ==========================================

        JPanel formulario =
                new JPanel(
                        new GridLayout(
                                6,
                                2,
                                15,
                                15
                        )
                );

        formulario.setBackground(
                Estilo.BLANCO
        );

        formulario.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        0,
                        20,
                        0
                )
        );


        txtCedula =
                crearCampo();

        txtNombre =
                crearCampo();

        txtApellido =
                crearCampo();

        txtTelefono =
                crearCampo();

        txtDireccion =
                crearCampo();


        calendarioFechaNacimiento =
                new JDateChooser();

        calendarioFechaNacimiento.setLocale(
                new java.util.Locale(
                        "es",
                        "ES"
                )
        );

        calendarioFechaNacimiento.setDateFormatString(
                "yyyy-MM-dd"
        );


        formulario.add(
                crearEtiqueta("Cédula:")
        );

        formulario.add(
                txtCedula
        );


        formulario.add(
                crearEtiqueta("Nombre:")
        );

        formulario.add(
                txtNombre
        );


        formulario.add(
                crearEtiqueta("Apellido:")
        );

        formulario.add(
                txtApellido
        );


        formulario.add(
                crearEtiqueta("Teléfono:")
        );

        formulario.add(
                txtTelefono
        );


        formulario.add(
                crearEtiqueta("Fecha nacimiento:")
        );

        formulario.add(
                calendarioFechaNacimiento
        );


        formulario.add(
                crearEtiqueta("Dirección:")
        );

        formulario.add(
                txtDireccion
        );


        tarjeta.add(
                formulario,
                BorderLayout.CENTER
        );


        // ==========================================
        // BOTONES
        // ==========================================

        JPanel botones =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                0
                        )
                );

        botones.setBackground(
                Estilo.BLANCO
        );


        btnLimpiar =
                new BotonRedondeado(
                        "Limpiar",
                        Estilo.MORADO_MEDIO,
                        Estilo.MORADO_OSCURO
                );


        btnActualizar =
                new BotonRedondeado(
                        "Actualizar",
                        Estilo.MENTA,
                        Estilo.MENTA_OSCURO
                );


        btnEliminar =
                new BotonRedondeado(
                        "Eliminar",
                        Estilo.ROJO,
                        new Color(180, 65, 75)
                );


        btnLimpiar.setPreferredSize(
                new Dimension(110, 40)
        );

        btnActualizar.setPreferredSize(
                new Dimension(125, 40)
        );

        btnEliminar.setPreferredSize(
                new Dimension(110, 40)
        );


        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);


        botones.add(btnLimpiar);

        botones.add(btnActualizar);

        botones.add(btnEliminar);


        tarjeta.add(
                botones,
                BorderLayout.SOUTH
        );


        contenedor.add(
                tarjeta,
                BorderLayout.CENTER
        );


        principal.add(
                contenedor,
                BorderLayout.CENTER
        );


        add(principal);


        // ==========================================
        // EVENTOS
        // ==========================================

        btnBuscar.addActionListener(
                e -> buscarPaciente()
        );

        btnActualizar.addActionListener(
                e -> actualizarPaciente()
        );

        btnEliminar.addActionListener(
                e -> eliminarPaciente()
        );

        btnLimpiar.addActionListener(
                e -> limpiarFormulario()
        );
    }


    // ==========================================
    // BUSCAR
    // ==========================================

    private void buscarPaciente() {

        String cedula =
                txtBuscarCedula.getText()
                        .trim();

        if (cedula.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese la cédula del paciente.",
                    "Dato requerido",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        PacienteDAO dao =
                new PacienteDAO();

        pacienteActual =
                dao.buscarPorCedula(cedula);


        if (pacienteActual == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "No se encontró ningún paciente con esa cédula.",
                    "Paciente no encontrado",
                    JOptionPane.INFORMATION_MESSAGE
            );

            limpiarDatos();

            return;
        }


        txtCedula.setText(
                pacienteActual.getCedula()
        );

        txtNombre.setText(
                pacienteActual.getNombre()
        );

        txtApellido.setText(
                pacienteActual.getApellido()
        );

        txtTelefono.setText(
                pacienteActual.getTelefono()
        );

        txtDireccion.setText(
                pacienteActual.getDireccion()
        );


        if (pacienteActual.getFechaNacimiento() != null) {

            calendarioFechaNacimiento.setDate(
                    pacienteActual.getFechaNacimiento()
            );

        } else {

            calendarioFechaNacimiento.setDate(
                    null
            );
        }


        btnActualizar.setEnabled(true);
        btnEliminar.setEnabled(true);
    }


    // ==========================================
    // ACTUALIZAR
    // ==========================================

    private void actualizarPaciente() {

        if (pacienteActual == null) {
            return;
        }


        if (txtCedula.getText()
                .trim()
                .isEmpty()
                ||
            txtNombre.getText()
                .trim()
                .isEmpty()
                ||
            txtApellido.getText()
                .trim()
                .isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Cédula, nombre y apellido son obligatorios.",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        try {

            Date fecha = null;

            if (
                    calendarioFechaNacimiento
                            .getDate() != null
            ) {

                java.util.Date fechaSeleccionada =
                        calendarioFechaNacimiento
                                .getDate();

                fecha =
                        new Date(
                                fechaSeleccionada.getTime()
                        );
            }


            pacienteActual.setCedula(
                    txtCedula.getText()
                            .trim()
            );

            pacienteActual.setNombre(
                    txtNombre.getText()
                            .trim()
            );

            pacienteActual.setApellido(
                    txtApellido.getText()
                            .trim()
            );

            pacienteActual.setTelefono(
                    txtTelefono.getText()
                            .trim()
            );

            pacienteActual.setFechaNacimiento(
                    fecha
            );

            pacienteActual.setDireccion(
                    txtDireccion.getText()
                            .trim()
            );


            PacienteDAO dao =
                    new PacienteDAO();

            boolean actualizado =
                    dao.actualizarPaciente(
                            pacienteActual
                    );


            if (actualizado) {

                JOptionPane.showMessageDialog(
                        this,
                        "Paciente actualizado correctamente.",
                        "Actualización exitosa",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo actualizar el paciente.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }


        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ocurrió un error:\n"
                    + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // ==========================================
    // ELIMINAR
    // ==========================================

    private void eliminarPaciente() {

        if (pacienteActual == null) {
            return;
        }


        int respuesta =
                JOptionPane.showConfirmDialog(
                        this,
                        "¿Está seguro de eliminar este paciente?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );


        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }


        PacienteDAO dao =
                new PacienteDAO();

        boolean eliminado =
                dao.eliminarPaciente(
                        pacienteActual.getId()
                );


        if (eliminado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Paciente eliminado correctamente.",
                    "Eliminación exitosa",
                    JOptionPane.INFORMATION_MESSAGE
            );

            limpiarFormulario();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo eliminar el paciente.\n"
                    + "Verifique si tiene historial clínico registrado.",
                    "No se puede eliminar",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }


    // ==========================================
    // LIMPIAR
    // ==========================================

    private void limpiarFormulario() {

        txtBuscarCedula.setText("");

        limpiarDatos();

        txtBuscarCedula.requestFocus();
    }


    private void limpiarDatos() {

        pacienteActual = null;

        txtCedula.setText("");

        txtNombre.setText("");

        txtApellido.setText("");

        txtTelefono.setText("");

        txtDireccion.setText("");

        calendarioFechaNacimiento.setDate(
                null
        );

        btnActualizar.setEnabled(false);

        btnEliminar.setEnabled(false);
    }


    // ==========================================
    // ESTILO
    // ==========================================

    private JTextField crearCampo() {

        JTextField campo =
                new JTextField();

        campo.setFont(
                Estilo.NORMAL
        );

        campo.setForeground(
                Estilo.GRIS_TEXTO
        );

        campo.setBackground(
                Estilo.BLANCO
        );

        campo.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                Estilo.GRIS_BORDE
                        ),

                        BorderFactory.createEmptyBorder(
                                8,
                                10,
                                8,
                                10
                        )
                )
        );

        return campo;
    }


    private JLabel crearEtiqueta(
            String texto) {

        JLabel etiqueta =
                new JLabel(texto);

        etiqueta.setFont(
                Estilo.NORMAL_NEGRITA
        );

        etiqueta.setForeground(
                Estilo.GRIS_TEXTO
        );

        return etiqueta;
    }


    // ==========================================
    // MAIN
    // ==========================================

    public static void main(
            String[] args) {

        SwingUtilities.invokeLater(() -> {

            GestionarPaciente ventana =
                    new GestionarPaciente();

            ventana.setVisible(true);
        });
    }
}