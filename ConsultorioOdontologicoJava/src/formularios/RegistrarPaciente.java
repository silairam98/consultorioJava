package formularios;

import formularios.componentes.BotonRedondeado;
import formularios.componentes.PanelRedondeado;

import dao.PacienteDAO;
import modelo.Paciente;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

import com.toedter.calendar.JDateChooser;

public class RegistrarPaciente extends JFrame {

    private JTextField txtCedula;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JDateChooser calendarioFechaNacimiento;
    private JTextField txtDireccion;

    private JButton btnGuardar;
    private JButton btnLimpiar;

    public RegistrarPaciente() {

        setTitle("Registrar Paciente");

        setSize(760, 620);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        crearFormulario();
    }

    private void crearFormulario() {

        // ==========================================
        // PANEL PRINCIPAL
        // ==========================================

        JPanel panelPrincipal =
                new JPanel(new BorderLayout());

        panelPrincipal.setBackground(
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


        JLabel lblTitulo =
                new JLabel("Registrar paciente");

        lblTitulo.setFont(
                Estilo.TITULO
        );

        lblTitulo.setForeground(
                Estilo.MORADO_OSCURO
        );


        JLabel lblDescripcion =
                new JLabel(
                        "Complete la información del nuevo paciente."
                );

        lblDescripcion.setFont(
                Estilo.SUBTITULO
        );

        lblDescripcion.setForeground(
                Estilo.GRIS_TEXTO
        );


        encabezado.add(lblTitulo);

        encabezado.add(
                Box.createVerticalStrut(5)
        );

        encabezado.add(lblDescripcion);


        panelPrincipal.add(
                encabezado,
                BorderLayout.NORTH
        );


        // ==========================================
        // TARJETA PRINCIPAL
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
        // TITULO DE LA TARJETA
        // ==========================================

        JLabel lblInformacion =
                new JLabel(
                        "Información personal"
                );

        lblInformacion.setFont(
                Estilo.ENCABEZADO
        );

        lblInformacion.setForeground(
                Estilo.MORADO_OSCURO
        );


        tarjeta.add(
                lblInformacion,
                BorderLayout.NORTH
        );


        // ==========================================
        // FORMULARIO
        // ==========================================

        JPanel formulario =
                new JPanel(
                        new GridLayout(
                                3,
                                4,
                                15,
                                18
                        )
                );

        formulario.setBackground(
                Estilo.BLANCO
        );

        formulario.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        0,
                        20,
                        0
                )
        );


        // ==========================================
        // ETIQUETAS
        // ==========================================

        JLabel lblCedula =
                crearEtiqueta("Cédula *");

        JLabel lblNombre =
                crearEtiqueta("Nombre *");

        JLabel lblApellido =
                crearEtiqueta("Apellido *");

        JLabel lblTelefono =
                crearEtiqueta("Teléfono");

        JLabel lblFecha =
                crearEtiqueta(
                        "Fecha de nacimiento"
                );

        JLabel lblDireccion =
                crearEtiqueta("Dirección");


        // ==========================================
        // CAMPOS
        // ==========================================

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


        // ==========================================
        // FECHA
        // ==========================================

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

        calendarioFechaNacimiento.setDate(
                null
        );

        calendarioFechaNacimiento.setPreferredSize(
                new Dimension(150, 35)
        );


        // ==========================================
        // AGREGAR CAMPOS
        // ==========================================

        formulario.add(lblCedula);
        formulario.add(txtCedula);

        formulario.add(lblNombre);
        formulario.add(txtNombre);

        formulario.add(lblApellido);
        formulario.add(txtApellido);

        formulario.add(lblTelefono);
        formulario.add(txtTelefono);

        formulario.add(lblFecha);
        formulario.add(
                calendarioFechaNacimiento
        );

        formulario.add(lblDireccion);
        formulario.add(txtDireccion);


        tarjeta.add(
                formulario,
                BorderLayout.CENTER
        );


        // ==========================================
        // BOTONES
        // ==========================================

        JPanel panelBotones =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                0
                        )
                );

        panelBotones.setBackground(
                Estilo.BLANCO
        );


        btnLimpiar =
                new BotonRedondeado(
                        "Limpiar",
                        Estilo.MORADO_MEDIO,
                        Estilo.MORADO_OSCURO
                );


        btnGuardar =
                new BotonRedondeado(
                        "Guardar paciente",
                        Estilo.MENTA,
                        Estilo.MENTA_OSCURO
                );


        btnLimpiar.setPreferredSize(
                new Dimension(120, 40)
        );

        btnGuardar.setPreferredSize(
                new Dimension(170, 40)
        );


        panelBotones.add(
                btnLimpiar
        );

        panelBotones.add(
                btnGuardar
        );


        tarjeta.add(
                panelBotones,
                BorderLayout.SOUTH
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


        contenedor.add(
                tarjeta,
                BorderLayout.CENTER
        );


        panelPrincipal.add(
                contenedor,
                BorderLayout.CENTER
        );


        add(panelPrincipal);


        // ==========================================
        // EVENTOS
        // ==========================================

        btnGuardar.addActionListener(
                e -> guardarPaciente()
        );

        btnLimpiar.addActionListener(
                e -> limpiarFormulario()
        );
    }


    // ==========================================
    // CREAR ETIQUETA
    // ==========================================

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
    // CREAR CAMPO
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


    // ==========================================
    // GUARDAR PACIENTE
    // ==========================================

    private void guardarPaciente() {

        // --------------------------------------
        // VALIDAR CAMPOS OBLIGATORIOS
        // --------------------------------------

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
            
            String telefono =
                    txtTelefono.getText().trim();

            if (!telefono.isEmpty()) {

                if (!telefono.matches("\\d{11}")) {

                    JOptionPane.showMessageDialog(
                            this,
                            "El número de teléfono debe contener exactamente 11 dígitos.",
                            "Teléfono inválido",
                            JOptionPane.WARNING_MESSAGE
                    );

                    txtTelefono.requestFocus();

                    return;
                }
            }


        try {

            // --------------------------------------
            // FECHA DE NACIMIENTO
            // --------------------------------------

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
                                fechaSeleccionada
                                        .getTime()
                        );
            }


            // --------------------------------------
            // CREAR PACIENTE
            // --------------------------------------

            Paciente paciente =
                    new Paciente(

                            txtCedula
                                    .getText()
                                    .trim(),

                            txtNombre
                                    .getText()
                                    .trim(),

                            txtApellido
                                    .getText()
                                    .trim(),

                            txtTelefono
                                    .getText()
                                    .trim(),

                            fecha,

                            txtDireccion
                                    .getText()
                                    .trim()
                    );


            // --------------------------------------
            // GUARDAR EN BASE DE DATOS
            // --------------------------------------

            PacienteDAO dao =
                    new PacienteDAO();

            boolean registrado =
                    dao.registrarPaciente(
                            paciente
                    );


            // --------------------------------------
            // RESULTADO
            // --------------------------------------

            if (registrado) {

                JOptionPane.showMessageDialog(
                        this,
                        "Paciente registrado correctamente.",
                        "Registro exitoso",
                        JOptionPane.INFORMATION_MESSAGE
                );

                limpiarFormulario();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo registrar el paciente.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }


        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ocurrió un error al registrar "
                    + "el paciente:\n"
                    + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // ==========================================
    // LIMPIAR FORMULARIO
    // ==========================================

    private void limpiarFormulario() {

        txtCedula.setText("");

        txtNombre.setText("");

        txtApellido.setText("");

        txtTelefono.setText("");

        calendarioFechaNacimiento
                .setDate(null);

        txtDireccion.setText("");

        txtCedula.requestFocus();
    }


    // ==========================================
    // MAIN
    // ==========================================

    public static void main(
            String[] args) {

        SwingUtilities.invokeLater(() -> {

            RegistrarPaciente ventana =
                    new RegistrarPaciente();

            ventana.setVisible(true);
        });
    }
}
