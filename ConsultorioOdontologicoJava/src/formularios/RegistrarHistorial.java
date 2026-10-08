package formularios;

import formularios.componentes.BotonRedondeado;
import formularios.componentes.PanelRedondeado;
import formularios.componentes.PanelOdontograma;

import dao.HistorialDAO;
import dao.OdontogramaDAO;

import modelo.Historial;
import modelo.Odontograma;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.toedter.calendar.JDateChooser;

public class RegistrarHistorial extends JFrame {

    private JTextField txtPacienteId;
    private JDateChooser calendarioFecha;
    private JTextField txtConsulta;
    private JTextField txtTratamiento;
    private JTextField txtObservaciones;

    private JButton btnGuardar;
    private JButton btnLimpiar;

    // Odontograma
    private PanelOdontograma panelOdontograma;

    private int pacienteId;

    private TarjetaPaciente tarjetaPaciente;


    // ==========================================
    // CONSTRUCTORES
    // ==========================================

    public RegistrarHistorial() {

        this(0, null);
    }


    public RegistrarHistorial(int pacienteId) {

        this(
                pacienteId,
                null
        );
    }


    public RegistrarHistorial(
            int pacienteId,
            TarjetaPaciente tarjetaPaciente) {

        this.pacienteId =
                pacienteId;

        this.tarjetaPaciente =
                tarjetaPaciente;


        setTitle(
                "Registrar Consulta"
        );

        setSize(
                900,
                750
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        crearFormulario();


        if (pacienteId > 0) {

            txtPacienteId.setText(
                    String.valueOf(
                            pacienteId
                    )
            );

            txtPacienteId.setEditable(
                    false
            );
        }
    }


    // ==========================================
    // CREAR FORMULARIO
    // ==========================================

    private void crearFormulario() {

        JPanel principal =
                new JPanel(
                        new BorderLayout()
                );

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
                        "Registrar consulta"
                );

        titulo.setFont(
                Estilo.TITULO
        );

        titulo.setForeground(
                Estilo.MORADO_OSCURO
        );


        JLabel descripcion =
                new JLabel(
                        "Registre la información de la consulta y el estado dental del paciente."
                );

        descripcion.setFont(
                Estilo.SUBTITULO
        );

        descripcion.setForeground(
                Estilo.GRIS_TEXTO
        );


        encabezado.add(
                titulo
        );

        encabezado.add(
                Box.createVerticalStrut(
                        5
                )
        );

        encabezado.add(
                descripcion
        );


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
                new PanelRedondeado(
                        25
                );

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
        // TÍTULO
        // ==========================================

        JLabel lblInformacion =
                new JLabel(
                        "Información de la consulta"
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
        // CONTENIDO DEL FORMULARIO
        // ==========================================

        JPanel contenidoFormulario =
                new JPanel();

        contenidoFormulario.setBackground(
                Estilo.BLANCO
        );

        contenidoFormulario.setLayout(
                new BoxLayout(
                        contenidoFormulario,
                        BoxLayout.Y_AXIS
                )
        );


        // ==========================================
        // FORMULARIO
        // ==========================================

        JPanel formulario =
                new JPanel(
                        new GridLayout(
                                5,
                                2,
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
        // CAMPOS
        // ==========================================

        txtPacienteId =
                crearCampo();

        txtConsulta =
                crearCampo();

        txtTratamiento =
                crearCampo();

        txtObservaciones =
                crearCampo();


        // ==========================================
        // FECHA
        // ==========================================

        calendarioFecha =
                new JDateChooser();

        calendarioFecha.setLocale(
                new Locale(
                        "es",
                        "ES"
                )
        );

        calendarioFecha.setDateFormatString(
                "yyyy-MM-dd"
        );

        calendarioFecha.setDate(
                new java.util.Date()
        );


        // ==========================================
        // AGREGAR CAMPOS
        // ==========================================

        formulario.add(
                crearEtiqueta(
                        "ID del paciente:"
                )
        );

        formulario.add(
                txtPacienteId
        );


        formulario.add(
                crearEtiqueta(
                        "Fecha de consulta:"
                )
        );

        formulario.add(
                calendarioFecha
        );


        formulario.add(
                crearEtiqueta(
                        "Consulta *:"
                )
        );

        formulario.add(
                txtConsulta
        );


        formulario.add(
                crearEtiqueta(
                        "Tratamiento:"
                )
        );

        formulario.add(
                txtTratamiento
        );


        formulario.add(
                crearEtiqueta(
                        "Observaciones:"
                )
        );

        formulario.add(
                txtObservaciones
        );


        contenidoFormulario.add(
                formulario
        );


        // ==========================================
        // ESPACIO
        // ==========================================

        contenidoFormulario.add(
                Box.createVerticalStrut(
                        10
                )
        );


        // ==========================================
        // TÍTULO ODONTOGRAMA
        // ==========================================

        JLabel lblOdontograma =
                new JLabel(
                        "Odontograma"
                );

        lblOdontograma.setFont(
                Estilo.ENCABEZADO
        );

        lblOdontograma.setForeground(
                Estilo.MORADO_OSCURO
        );

        contenidoFormulario.add(
                lblOdontograma
        );


        contenidoFormulario.add(
                Box.createVerticalStrut(
                        5
                )
        );


        JLabel lblAyuda =
                new JLabel(
                        "Seleccione un diente para indicar su estado y agregar una observación."
                );

        lblAyuda.setFont(
                Estilo.PEQUENA
        );

        lblAyuda.setForeground(
                Estilo.GRIS_SUAVE
        );

        contenidoFormulario.add(
                lblAyuda
        );


        contenidoFormulario.add(
                Box.createVerticalStrut(
                        10
                )
        );


        // ==========================================
        // ODONTOGRAMA
        // ==========================================

        panelOdontograma =
                new PanelOdontograma();

        panelOdontograma.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        15,
                        10
                )
        );


        contenidoFormulario.add(
                panelOdontograma
        );


        // ==========================================
        // SCROLL
        // ==========================================

        JScrollPane scroll =
                new JScrollPane(
                        contenidoFormulario
                );

        scroll.setBorder(
                BorderFactory.createEmptyBorder()
        );

        scroll.setBackground(
                Estilo.BLANCO
        );

        scroll.getVerticalScrollBar()
                .setUnitIncrement(
                        16
                );


        tarjeta.add(
                scroll,
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


        btnGuardar =
                new BotonRedondeado(
                        "Guardar consulta",
                        Estilo.MENTA,
                        Estilo.MENTA_OSCURO
                );


        btnLimpiar.setPreferredSize(
                new Dimension(
                        120,
                        40
                )
        );


        btnGuardar.setPreferredSize(
                new Dimension(
                        170,
                        40
                )
        );


        botones.add(
                btnLimpiar
        );

        botones.add(
                btnGuardar
        );


        tarjeta.add(
                botones,
                BorderLayout.SOUTH
        );


        // ==========================================
        // ARMAR VENTANA
        // ==========================================

        contenedor.add(
                tarjeta,
                BorderLayout.CENTER
        );


        principal.add(
                contenedor,
                BorderLayout.CENTER
        );


        add(
                principal
        );


        // ==========================================
        // EVENTOS
        // ==========================================

        btnGuardar.addActionListener(
                e -> guardarHistorial()
        );


        btnLimpiar.addActionListener(
                e -> limpiarFormulario()
        );
    }


    // ==========================================
    // GUARDAR CONSULTA Y ODONTOGRAMA
    // ==========================================

    private void guardarHistorial() {

        // ==========================================
        // VALIDAR DATOS
        // ==========================================

        if (
                txtPacienteId
                        .getText()
                        .trim()
                        .isEmpty()
                ||
                txtConsulta
                        .getText()
                        .trim()
                        .isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "El paciente y la consulta son obligatorios.",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        try {

            // ==========================================
            // ID DEL PACIENTE
            // ==========================================

            int idPaciente =
                    Integer.parseInt(
                            txtPacienteId
                                    .getText()
                                    .trim()
                    );


            // ==========================================
            // FECHA
            // ==========================================

            java.util.Date fechaSeleccionada =
                    calendarioFecha.getDate();


            if (
                    fechaSeleccionada
                            == null
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Seleccione una fecha para la consulta.",
                        "Fecha requerida",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }


            Date fecha =
                    new Date(
                            fechaSeleccionada
                                    .getTime()
                    );


            // ==========================================
            // CREAR HISTORIAL
            // ==========================================

            Historial historial =
                    new Historial(

                            idPaciente,

                            fecha,

                            txtConsulta
                                    .getText()
                                    .trim(),

                            txtTratamiento
                                    .getText()
                                    .trim(),

                            txtObservaciones
                                    .getText()
                                    .trim()
                    );


            // ==========================================
            // GUARDAR HISTORIAL
            // ==========================================

            HistorialDAO historialDAO =
                    new HistorialDAO();


            int historialId =
                    historialDAO
                            .registrarHistorial(
                                    historial
                            );


            // ==========================================
            // COMPROBAR HISTORIAL
            // ==========================================

            if (
                    historialId
                            <= 0
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo registrar la consulta.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }


            // ==========================================
            // OBTENER DATOS DEL ODONTOGRAMA
            // ==========================================

            List<Odontograma> dientes =
                    new ArrayList<>();


            Map<String, String> estados =
                    panelOdontograma
                            .getEstados();


            Map<String, String> observaciones =
                    panelOdontograma
                            .getObservaciones();


            // ==========================================
            // CREAR REGISTROS DE LOS DIENTES
            // ==========================================

            for (
                    String numeroDiente :
                    estados.keySet()
            ) {

                Odontograma diente =
                        new Odontograma(

                                historialId,

                                numeroDiente,

                                estados.get(
                                        numeroDiente
                                ),

                                observaciones.get(
                                        numeroDiente
                                )
                        );


                dientes.add(
                        diente
                );
            }


            // ==========================================
            // GUARDAR ODONTOGRAMA
            // ==========================================

            OdontogramaDAO odontogramaDAO =
                    new OdontogramaDAO();


            boolean odontogramaGuardado =
                    odontogramaDAO
                            .registrarOdontograma(
                                    dientes
                            );


            // ==========================================
            // RESULTADO
            // ==========================================

            if (
                    odontogramaGuardado
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Consulta y odontograma "
                        + "registrados correctamente.",
                        "Registro exitoso",
                        JOptionPane.INFORMATION_MESSAGE
                );


                // Actualizar automáticamente
                // la tarjeta del paciente

                if (
                        tarjetaPaciente
                                != null
                ) {

                    tarjetaPaciente
                            .actualizarHistorial();
                }


                dispose();


            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "La consulta fue registrada, "
                        + "pero ocurrió un problema "
                        + "al guardar el odontograma.",
                        "Advertencia",
                        JOptionPane.WARNING_MESSAGE
                );
            }


        } catch (
                NumberFormatException e
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "El ID del paciente no es válido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );


        } catch (
                Exception e
        ) {

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
    // LIMPIAR
    // ==========================================

    private void limpiarFormulario() {

        // Si la ventana se abrió
        // sin paciente seleccionado

        if (
                tarjetaPaciente
                        == null
        ) {

            txtPacienteId.setText(
                    ""
            );
        }


        txtConsulta.setText(
                ""
        );

        txtTratamiento.setText(
                ""
        );

        txtObservaciones.setText(
                ""
        );


        calendarioFecha.setDate(
                new java.util.Date()
        );


        // Limpiar odontograma

        if (
                panelOdontograma
                        != null
        ) {

            panelOdontograma.limpiar();
        }


        txtConsulta.requestFocus();
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
    // CREAR ETIQUETA
    // ==========================================

    private JLabel crearEtiqueta(
            String texto) {

        JLabel etiqueta =
                new JLabel(
                        texto
                );


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

        SwingUtilities.invokeLater(
                () -> {

                    RegistrarHistorial ventana =
                            new RegistrarHistorial();

                    ventana.setVisible(
                            true
                    );
                }
        );
    }
}