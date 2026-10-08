package formularios;

import dao.HistorialDAO;
import dao.PacienteDAO;
import formularios.componentes.BotonRedondeado;
import formularios.componentes.PanelRedondeado;
import modelo.Historial;
import modelo.Paciente;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class TarjetaPaciente extends JFrame {

    private JTextField txtCedula;
    private JButton btnBuscar;

    private JLabel lblNombre;
    private JLabel lblCedula;
    private JLabel lblTelefono;
    private JLabel lblFechaNacimiento;
    private JLabel lblDireccion;

    private JTable tablaHistorial;
    private DefaultTableModel modeloTabla;

    private JButton btnNuevaConsulta;
    private JButton btnOdontograma;

    private Paciente pacienteActual;

    public TarjetaPaciente() {

        setTitle("Historia clínica - Consultorio Odontológico");

        setSize(1100, 720);

        setMinimumSize(
                new Dimension(950, 650)
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        crearInterfaz();
    }

    // =========================================================
    // INTERFAZ
    // =========================================================

    private void crearInterfaz() {

        JPanel principal =
                new JPanel(
                        new BorderLayout()
                );

        principal.setBackground(
                Estilo.FONDO
        );

        // -----------------------------------------------------
        // ENCABEZADO
        // -----------------------------------------------------

        JPanel encabezado =
                crearEncabezado();

        principal.add(
                encabezado,
                BorderLayout.NORTH
        );

        // -----------------------------------------------------
        // CONTENIDO
        // -----------------------------------------------------

        JPanel contenido =
                new JPanel(
                        new BorderLayout()
                );

        contenido.setBackground(
                Estilo.FONDO
        );

        contenido.setBorder(
                BorderFactory.createEmptyBorder(
                        5,
                        35,
                        30,
                        35
                )
        );

        // -----------------------------------------------------
        // BUSCADOR
        // -----------------------------------------------------

        PanelRedondeado buscador =
                crearPanelBuscador();

        contenido.add(
                buscador,
                BorderLayout.NORTH
        );

        // -----------------------------------------------------
        // CENTRO
        // -----------------------------------------------------

        JPanel centro =
                new JPanel(
                        new BorderLayout(
                                0,
                                20
                        )
                );

        centro.setOpaque(false);

        // -----------------------------------------------------
        // INFORMACIÓN DEL PACIENTE
        // -----------------------------------------------------

        PanelRedondeado tarjetaPaciente =
                crearTarjetaPaciente();

        centro.add(
                tarjetaPaciente,
                BorderLayout.NORTH
        );

        // -----------------------------------------------------
        // HISTORIAL
        // -----------------------------------------------------

        PanelRedondeado tarjetaHistorial =
                crearTarjetaHistorial();

        centro.add(
                tarjetaHistorial,
                BorderLayout.CENTER
        );

        contenido.add(
                centro,
                BorderLayout.CENTER
        );

        principal.add(
                contenido,
                BorderLayout.CENTER
        );

        add(principal);

        // Estado inicial

        habilitarControles(false);
    }

    // =========================================================
    // ENCABEZADO
    // =========================================================

    private JPanel crearEncabezado() {

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
                        30,
                        35,
                        15,
                        35
                )
        );

        JLabel titulo =
                new JLabel(
                        "Historia clínica"
                );

        titulo.setFont(
                Estilo.TITULO
        );

        titulo.setForeground(
                Estilo.MORADO_OSCURO
        );

        JLabel descripcion =
                new JLabel(
                        "Consulte la información y el historial "
                        + "clínico de sus pacientes."
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

        return encabezado;
    }

    // =========================================================
    // BUSCADOR
    // =========================================================

    private PanelRedondeado crearPanelBuscador() {

        PanelRedondeado panel =
                new PanelRedondeado(20);

        panel.setColorFondo(
                Estilo.BLANCO
        );

        panel.setLayout(
                new BorderLayout(
                        15,
                        0
                )
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        18,
                        22,
                        18,
                        22
                )
        );

        JPanel texto =
                new JPanel();

        texto.setOpaque(false);

        texto.setLayout(
                new BoxLayout(
                        texto,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel titulo =
                new JLabel(
                        "Buscar paciente"
                );

        titulo.setFont(
                Estilo.NORMAL_NEGRITA
        );

        titulo.setForeground(
                Estilo.MORADO_OSCURO
        );

        JLabel descripcion =
                new JLabel(
                        "Ingrese la cédula del paciente"
                );

        descripcion.setFont(
                Estilo.PEQUENA
        );

        descripcion.setForeground(
                Estilo.GRIS_TEXTO
        );

        texto.add(titulo);

        texto.add(
                Box.createVerticalStrut(4)
        );

        texto.add(descripcion);

        panel.add(
                texto,
                BorderLayout.WEST
        );

        txtCedula =
                crearCampo();

        txtCedula.setPreferredSize(
                new Dimension(
                        230,
                        42
                )
        );

        txtCedula.addActionListener(
                e -> buscarPaciente()
        );

        panel.add(
                txtCedula,
                BorderLayout.CENTER
        );

        btnBuscar =
                new BotonRedondeado(
                        "Buscar paciente",
                        Estilo.MORADO,
                        Estilo.MORADO_OSCURO
                );

        btnBuscar.setPreferredSize(
                new Dimension(
                        170,
                        42
                )
        );

        btnBuscar.addActionListener(
                e -> buscarPaciente()
        );

        panel.add(
                btnBuscar,
                BorderLayout.EAST
        );

        return panel;
    }

    // =========================================================
    // TARJETA DEL PACIENTE
    // =========================================================

    private PanelRedondeado crearTarjetaPaciente() {

        PanelRedondeado tarjeta =
                new PanelRedondeado(22);

        tarjeta.setColorFondo(
                Estilo.BLANCO
        );

        tarjeta.setLayout(
                new BorderLayout()
        );

        tarjeta.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        25,
                        20,
                        25
                )
        );

        // -----------------------------------------------------
        // ENCABEZADO DEL PACIENTE
        // -----------------------------------------------------

        JPanel encabezado =
                new JPanel(
                        new BorderLayout()
                );

        encabezado.setOpaque(false);

        JLabel icono =
                new JLabel("●");

        icono.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        34
                )
        );

        icono.setForeground(
                Estilo.MENTA
        );

        encabezado.add(
                icono,
                BorderLayout.WEST
        );

        JPanel nombres =
                new JPanel();

        nombres.setOpaque(false);

        nombres.setLayout(
                new BoxLayout(
                        nombres,
                        BoxLayout.Y_AXIS
                )
        );

        lblNombre =
                new JLabel(
                        "Paciente no seleccionado"
                );

        lblNombre.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        22
                )
        );

        lblNombre.setForeground(
                Estilo.MORADO_OSCURO
        );

        lblCedula =
                new JLabel(
                        "Cédula: —"
                );

        lblCedula.setFont(
                Estilo.PEQUENA
        );

        lblCedula.setForeground(
                Estilo.GRIS_TEXTO
        );

        nombres.add(lblNombre);

        nombres.add(
                Box.createVerticalStrut(3)
        );

        nombres.add(lblCedula);

        encabezado.add(
                nombres,
                BorderLayout.CENTER
        );

        tarjeta.add(
                encabezado,
                BorderLayout.NORTH
        );

        // -----------------------------------------------------
        // DATOS
        // -----------------------------------------------------

        JPanel datos =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                25,
                                0
                        )
                );

        datos.setOpaque(false);

        lblTelefono =
                crearDato(
                        "Teléfono",
                        "—"
                );

        lblFechaNacimiento =
                crearDato(
                        "Fecha de nacimiento",
                        "—"
                );

        lblDireccion =
                crearDato(
                        "Dirección",
                        "—"
                );

        datos.add(
                crearBloqueDato(
                        "Teléfono",
                        lblTelefono
                )
        );

        datos.add(
                crearBloqueDato(
                        "Fecha de nacimiento",
                        lblFechaNacimiento
                )
        );

        datos.add(
                crearBloqueDato(
                        "Dirección",
                        lblDireccion
                )
        );

        tarjeta.add(
                datos,
                BorderLayout.CENTER
        );

        return tarjeta;
    }

    // =========================================================
    // BLOQUE DE DATO
    // =========================================================

    private JPanel crearBloqueDato(
            String titulo,
            JLabel valor) {

        JPanel panel =
                new JPanel();

        panel.setOpaque(false);

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel etiqueta =
                new JLabel(titulo);

        etiqueta.setFont(
                Estilo.PEQUENA
        );

        etiqueta.setForeground(
                Estilo.GRIS_SUAVE
        );

        panel.add(etiqueta);

        panel.add(
                Box.createVerticalStrut(4)
        );

        panel.add(valor);

        return panel;
    }

    // =========================================================
    // HISTORIAL CLÍNICO
    // =========================================================

    private PanelRedondeado crearTarjetaHistorial() {

        PanelRedondeado tarjeta =
                new PanelRedondeado(22);

        tarjeta.setColorFondo(
                Estilo.BLANCO
        );

        tarjeta.setLayout(
                new BorderLayout(
                        0,
                        15
                )
        );

        tarjeta.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        25,
                        20,
                        25
                )
        );

        // -----------------------------------------------------
        // ENCABEZADO
        // -----------------------------------------------------

        JPanel encabezado =
                new JPanel(
                        new BorderLayout()
                );

        encabezado.setOpaque(false);

        JLabel titulo =
                new JLabel(
                        "Historial de consultas"
                );

        titulo.setFont(
                Estilo.ENCABEZADO
        );

        titulo.setForeground(
                Estilo.MORADO_OSCURO
        );

        encabezado.add(
                titulo,
                BorderLayout.WEST
        );

        JPanel botones =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                8,
                                0
                        )
                );

        botones.setOpaque(false);

        btnOdontograma =
                new BotonRedondeado(
                        "Ver odontograma",
                        Estilo.MORADO_MEDIO,
                        Estilo.MORADO_OSCURO
                );

        btnOdontograma.setPreferredSize(
                new Dimension(
                        150,
                        38
                )
        );

        btnNuevaConsulta =
                new BotonRedondeado(
                        "Nueva consulta",
                        Estilo.MENTA,
                        Estilo.MENTA_OSCURO
                );

        btnNuevaConsulta.setPreferredSize(
                new Dimension(
                        145,
                        38
                )
        );

        botones.add(
                btnOdontograma
        );

        botones.add(
                btnNuevaConsulta
        );

        encabezado.add(
                botones,
                BorderLayout.EAST
        );

        tarjeta.add(
                encabezado,
                BorderLayout.NORTH
        );

        // -----------------------------------------------------
        // TABLA
        // -----------------------------------------------------

        String[] columnas = {
                "ID",
                "Fecha",
                "Consulta",
                "Tratamiento",
                "Observaciones"
        };

        modeloTabla =
                new DefaultTableModel(
                        columnas,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };

        tablaHistorial =
                new JTable(
                        modeloTabla
                );

        tablaHistorial.setFont(
                Estilo.NORMAL
        );

        tablaHistorial.setForeground(
                Estilo.GRIS_TEXTO
        );

        tablaHistorial.setBackground(
                Estilo.BLANCO
        );

        tablaHistorial.setRowHeight(
                38
        );

        tablaHistorial.setSelectionBackground(
                Estilo.MORADO_CLARO
        );

        tablaHistorial.setSelectionForeground(
                Estilo.MORADO_OSCURO
        );

        tablaHistorial.setShowGrid(false);

        tablaHistorial.setIntercellSpacing(
                new Dimension(
                        0,
                        1
                )
        );

        tablaHistorial.getTableHeader()
                .setFont(
                        Estilo.NORMAL_NEGRITA
                );

        tablaHistorial.getTableHeader()
                .setForeground(
                        Estilo.MORADO_OSCURO
                );

        tablaHistorial.getTableHeader()
                .setBackground(
                        Estilo.MENTA_CLARO
                );

        tablaHistorial.getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                40
                        )
                );

        // Ocultar visualmente el ID

        tablaHistorial
                .getColumnModel()
                .getColumn(0)
                .setMinWidth(0);

        tablaHistorial
                .getColumnModel()
                .getColumn(0)
                .setMaxWidth(0);

        tablaHistorial
                .getColumnModel()
                .getColumn(0)
                .setPreferredWidth(0);

        // Ancho de columnas

        tablaHistorial
                .getColumnModel()
                .getColumn(1)
                .setPreferredWidth(100);

        tablaHistorial
                .getColumnModel()
                .getColumn(2)
                .setPreferredWidth(220);

        tablaHistorial
                .getColumnModel()
                .getColumn(3)
                .setPreferredWidth(220);

        tablaHistorial
                .getColumnModel()
                .getColumn(4)
                .setPreferredWidth(250);

        // Centrar fecha

        DefaultTableCellRenderer centro =
                new DefaultTableCellRenderer();

        centro.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        tablaHistorial
                .getColumnModel()
                .getColumn(1)
                .setCellRenderer(centro);

        JScrollPane scroll =
                new JScrollPane(
                        tablaHistorial
                );

        scroll.setBorder(
                BorderFactory.createLineBorder(
                        Estilo.GRIS_BORDE
                )
        );

        scroll.getViewport()
                .setBackground(
                        Estilo.BLANCO
                );

        tarjeta.add(
                scroll,
                BorderLayout.CENTER
        );

        // -----------------------------------------------------
        // EVENTOS
        // -----------------------------------------------------

        btnNuevaConsulta.addActionListener(
                e -> abrirNuevaConsulta()
        );

        btnOdontograma.addActionListener(
                e -> verOdontograma()
        );

        tablaHistorial.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            MouseEvent e) {

                        if (e.getClickCount() == 2) {

                            verOdontograma();
                        }
                    }
                }
        );

        return tarjeta;
    }

    // =========================================================
    // BUSCAR PACIENTE
    // =========================================================

    private void buscarPaciente() {

        String cedula =
                txtCedula.getText()
                        .trim();

        if (cedula.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese la cédula del paciente.",
                    "Dato requerido",
                    JOptionPane.WARNING_MESSAGE
            );

            txtCedula.requestFocus();

            return;
        }

        try {

            PacienteDAO dao =
                    new PacienteDAO();

            Paciente paciente =
                    dao.buscarPorCedula(
                            cedula
                    );

            if (paciente == null) {

                pacienteActual = null;

                limpiarDatosPaciente();

                modeloTabla.setRowCount(0);

                habilitarControles(false);

                JOptionPane.showMessageDialog(
                        this,
                        "No se encontró ningún paciente "
                        + "con esa cédula.",
                        "Paciente no encontrado",
                        JOptionPane.INFORMATION_MESSAGE
                );

                return;
            }

            pacienteActual =
                    paciente;

            mostrarDatosPaciente();

            cargarHistorial();

            habilitarControles(true);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ocurrió un error al buscar el paciente:\n"
                    + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // MOSTRAR DATOS DEL PACIENTE
    // =========================================================

    private void mostrarDatosPaciente() {

        lblNombre.setText(
                pacienteActual.getNombre()
                + " "
                + pacienteActual.getApellido()
        );

        lblCedula.setText(
                "Cédula: "
                + pacienteActual.getCedula()
        );

        String telefono =
                pacienteActual.getTelefono();

        if (telefono == null ||
                telefono.trim().isEmpty()) {

            telefono = "No registrado";
        }

        lblTelefono.setText(
                telefono
        );

        if (pacienteActual
                .getFechaNacimiento() != null) {

            lblFechaNacimiento.setText(
                    pacienteActual
                            .getFechaNacimiento()
                            .toString()
            );

        } else {

            lblFechaNacimiento.setText(
                    "No registrada"
            );
        }

        String direccion =
                pacienteActual.getDireccion();

        if (direccion == null ||
                direccion.trim().isEmpty()) {

            direccion = "No registrada";
        }

        lblDireccion.setText(
                direccion
        );
    }

    // =========================================================
    // LIMPIAR DATOS
    // =========================================================

    private void limpiarDatosPaciente() {

        lblNombre.setText(
                "Paciente no seleccionado"
        );

        lblCedula.setText(
                "Cédula: —"
        );

        lblTelefono.setText("—");

        lblFechaNacimiento.setText("—");

        lblDireccion.setText("—");
    }

    // =========================================================
    // CARGAR HISTORIAL
    // =========================================================

    private void cargarHistorial() {

        modeloTabla.setRowCount(0);

        if (pacienteActual == null) {
            return;
        }

        HistorialDAO dao =
                new HistorialDAO();

        List<Historial> historial =
                dao.buscarPorPaciente(
                        pacienteActual.getId()
                );

        for (Historial registro :
                historial) {

            modeloTabla.addRow(
                    new Object[]{
                            registro.getId(),
                            registro.getFecha(),
                            registro.getConsulta(),
                            registro.getTratamiento(),
                            registro.getObservaciones()
                    }
            );
        }
    }

    // =========================================================
    // ACTUALIZAR HISTORIAL
    // =========================================================

    public void actualizarHistorial() {

        if (pacienteActual != null) {

            cargarHistorial();

            habilitarControles(true);
        }
    }

    // =========================================================
    // NUEVA CONSULTA
    // =========================================================

    private void abrirNuevaConsulta() {

        if (pacienteActual == null) {

            return;
        }

        RegistrarHistorial ventana =
                new RegistrarHistorial(
                        pacienteActual.getId(),
                        this
                );

        ventana.setVisible(true);
    }

    // =========================================================
    // VER ODONTOGRAMA
    // =========================================================

    private void verOdontograma() {

        int fila =
                tablaHistorial.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione una consulta del historial "
                    + "para ver su odontograma.",
                    "Consulta no seleccionada",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int historialId =
                Integer.parseInt(
                        tablaHistorial
                                .getValueAt(
                                        fila,
                                        0
                                )
                                .toString()
                );

        VerOdontograma ventana =
                new VerOdontograma(
                        historialId
                );

        ventana.setVisible(true);
    }

    // =========================================================
    // HABILITAR / DESHABILITAR
    // =========================================================

    private void habilitarControles(
            boolean habilitar) {

        btnNuevaConsulta.setEnabled(
                habilitar
        );

        btnOdontograma.setEnabled(
                habilitar
        );
    }

    // =========================================================
    // CREAR CAMPO
    // =========================================================

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
                                12,
                                8,
                                12
                        )
                )
        );

        return campo;
    }

    // =========================================================
    // CREAR DATO
    // =========================================================

    private JLabel crearDato(
            String titulo,
            String valor) {

        JLabel etiqueta =
                new JLabel(valor);

        etiqueta.setFont(
                Estilo.NORMAL
        );

        etiqueta.setForeground(
                Estilo.GRIS_TEXTO
        );

        return etiqueta;
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(
            String[] args) {

        SwingUtilities.invokeLater(() -> {

            TarjetaPaciente ventana =
                    new TarjetaPaciente();

            ventana.setVisible(true);
        });
    }
}