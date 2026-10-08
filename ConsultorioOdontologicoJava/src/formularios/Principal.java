package formularios;

import dao.PacienteDAO;
import formularios.componentes.BotonRedondeado;
import formularios.componentes.PanelRedondeado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Principal extends JFrame {

    private JPanel panelContenido;
    private JLabel lblCantidadPacientes;

    private JButton btnInicio;
    private JButton btnPacientes;
    private JButton btnHistoria;
    private JButton btnGestionar;
    private JButton btnSalir;

    public Principal() {

        setTitle("Consultorio Odontológico");

        setSize(1200, 720);

        setMinimumSize(
                new Dimension(1000, 650)
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        crearInterfaz();

        mostrarInicio();
    }

    // =========================================================
    // INTERFAZ PRINCIPAL
    // =========================================================

    private void crearInterfaz() {

        JPanel principal =
                new JPanel(new BorderLayout());

        principal.setBackground(
                Estilo.FONDO
        );

        // -----------------------------------------------------
        // MENÚ LATERAL
        // -----------------------------------------------------

        JPanel menu =
                crearMenuLateral();

        principal.add(
                menu,
                BorderLayout.WEST
        );

        // -----------------------------------------------------
        // CONTENIDO
        // -----------------------------------------------------

        panelContenido =
                new JPanel(
                        new BorderLayout()
                );

        panelContenido.setBackground(
                Estilo.FONDO
        );

        principal.add(
                panelContenido,
                BorderLayout.CENTER
        );

        add(principal);
    }

    // =========================================================
    // MENÚ LATERAL
    // =========================================================

    private JPanel crearMenuLateral() {

        JPanel menu =
                new JPanel(
                        new BorderLayout()
                );

        menu.setPreferredSize(
                new Dimension(245, 0)
        );

        menu.setBackground(
                Estilo.MORADO_OSCURO
        );

        // -----------------------------------------------------
        // LOGO
        // -----------------------------------------------------

        JPanel encabezado =
                new JPanel();

        encabezado.setOpaque(false);

        encabezado.setLayout(
                new BoxLayout(
                        encabezado,
                        BoxLayout.Y_AXIS
                )
        );

        encabezado.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        25,
                        25,
                        25
                )
        );

        JLabel icono =
                new JLabel("🦷");

        icono.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        38
                )
        );

        icono.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JLabel titulo =
                new JLabel(
                        "Consultorio"
                );

        titulo.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        23
                )
        );

        titulo.setForeground(
                Color.WHITE
        );

        titulo.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JLabel subtitulo =
                new JLabel(
                        "Odontológico"
                );

        subtitulo.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        subtitulo.setForeground(
                Estilo.MENTA_CLARO
        );

        subtitulo.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        encabezado.add(icono);

        encabezado.add(
                Box.createVerticalStrut(8)
        );

        encabezado.add(titulo);

        encabezado.add(subtitulo);

        menu.add(
                encabezado,
                BorderLayout.NORTH
        );

        // -----------------------------------------------------
        // OPCIONES
        // -----------------------------------------------------

        JPanel opciones =
                new JPanel();

        opciones.setOpaque(false);

        opciones.setLayout(
                new BoxLayout(
                        opciones,
                        BoxLayout.Y_AXIS
                )
        );

        opciones.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        15,
                        10,
                        15
                )
        );

        btnInicio =
                crearBotonMenu(
                        "⌂   Inicio"
                );

        btnPacientes =
                crearBotonMenu(
                        "●   Registrar paciente"
                );

        btnHistoria =
                crearBotonMenu(
                        "▣   Historia clínica"
                );

        btnGestionar =
                crearBotonMenu(
                        "✎   Gestionar paciente"
                );

        opciones.add(btnInicio);

        opciones.add(
                Box.createVerticalStrut(8)
        );

        opciones.add(btnPacientes);

        opciones.add(
                Box.createVerticalStrut(8)
        );

        opciones.add(btnHistoria);

        opciones.add(
                Box.createVerticalStrut(8)
        );

        opciones.add(btnGestionar);

        menu.add(
                opciones,
                BorderLayout.CENTER
        );

        // -----------------------------------------------------
        // PIE DEL MENÚ
        // -----------------------------------------------------

        JPanel pie =
                new JPanel();

        pie.setOpaque(false);

        pie.setLayout(
                new BoxLayout(
                        pie,
                        BoxLayout.Y_AXIS
                )
        );

        pie.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        25,
                        15
                )
        );

        btnSalir =
                crearBotonMenu(
                        "←   Cerrar aplicación"
                );

        pie.add(btnSalir);

        JLabel version =
                new JLabel(
                        "Sistema de gestión odontológica"
                );

        version.setFont(
                Estilo.PEQUENA
        );

        version.setForeground(
                new Color(
                        220,
                        215,
                        230
                )
        );

        version.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        pie.add(
                Box.createVerticalStrut(15)
        );

        pie.add(version);

        menu.add(
                pie,
                BorderLayout.SOUTH
        );

        // -----------------------------------------------------
        // EVENTOS
        // -----------------------------------------------------

        btnInicio.addActionListener(
                e -> mostrarInicio()
        );

        btnPacientes.addActionListener(
                e -> abrirRegistrarPaciente()
        );

        btnHistoria.addActionListener(
                e -> abrirHistoria()
        );

        btnGestionar.addActionListener(
                e -> abrirGestionarPaciente()
        );

        btnSalir.addActionListener(
                e -> {

                    int respuesta =
                            JOptionPane.showConfirmDialog(
                                    this,
                                    "¿Desea cerrar la aplicación?",
                                    "Confirmar salida",
                                    JOptionPane.YES_NO_OPTION
                            );

                    if (respuesta ==
                            JOptionPane.YES_OPTION) {

                        System.exit(0);
                    }
                }
        );

        return menu;
    }

    // =========================================================
    // BOTÓN DEL MENÚ
    // =========================================================

    private JButton crearBotonMenu(
            String texto) {

        JButton boton =
                new JButton(texto);

        boton.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        48
                )
        );

        boton.setPreferredSize(
                new Dimension(
                        210,
                        48
                )
        );

        boton.setMinimumSize(
                new Dimension(
                        210,
                        48
                )
        );

        boton.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        boton.setFont(
                Estilo.MENU
        );

        boton.setForeground(
                Color.WHITE
        );

        boton.setBackground(
                Estilo.MORADO_OSCURO
        );

        boton.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        18,
                        0,
                        10
                )
        );

        boton.setFocusPainted(false);

        boton.setBorderPainted(false);

        boton.setOpaque(true);

        boton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        boton.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e) {

                        boton.setBackground(
                                Estilo.MORADO_MEDIO
                        );
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e) {

                        boton.setBackground(
                                Estilo.MORADO_OSCURO
                        );
                    }
                }
        );

        return boton;
    }

    // =========================================================
    // INICIO / DASHBOARD
    // =========================================================

    private void mostrarInicio() {

        panelContenido.removeAll();

        JPanel contenido =
                new JPanel(
                        new BorderLayout()
                );

        contenido.setBackground(
                Estilo.FONDO
        );

        // -----------------------------------------------------
        // ENCABEZADO
        // -----------------------------------------------------

        JPanel encabezado =
                new JPanel();

        encabezado.setOpaque(false);

        encabezado.setLayout(
                new BoxLayout(
                        encabezado,
                        BoxLayout.Y_AXIS
                )
        );

        encabezado.setBorder(
                BorderFactory.createEmptyBorder(
                        35,
                        40,
                        20,
                        40
                )
        );

        JLabel titulo =
                new JLabel(
                        "¡Bienvenida al consultorio!"
                );

        titulo.setFont(
                Estilo.TITULO
        );

        titulo.setForeground(
                Estilo.MORADO_OSCURO
        );

        JLabel descripcion =
                new JLabel(
                        "Administre pacientes y consultas "
                        + "de forma sencilla desde un solo lugar."
                );

        descripcion.setFont(
                Estilo.SUBTITULO
        );

        descripcion.setForeground(
                Estilo.GRIS_TEXTO
        );

        encabezado.add(titulo);

        encabezado.add(
                Box.createVerticalStrut(7)
        );

        encabezado.add(descripcion);

        contenido.add(
                encabezado,
                BorderLayout.NORTH
        );

        // -----------------------------------------------------
        // PANEL CENTRAL
        // -----------------------------------------------------

        JPanel centro =
                new JPanel();

        centro.setOpaque(false);

        centro.setLayout(
                new BoxLayout(
                        centro,
                        BoxLayout.Y_AXIS
                )
        );

        centro.setBorder(
                BorderFactory.createEmptyBorder(
                        5,
                        40,
                        30,
                        40
                )
        );

        // -----------------------------------------------------
        // TARJETAS DE ESTADÍSTICAS
        // -----------------------------------------------------

        JPanel estadisticas =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                20,
                                0
                        )
                );

        estadisticas.setOpaque(false);

        // PACIENTES

        PanelRedondeado tarjetaPacientes =
                crearTarjetaEstadistica(
                        "Pacientes registrados",
                        "0",
                        "Total de pacientes",
                        Estilo.MORADO
                );

        lblCantidadPacientes =
                obtenerEtiquetaCantidad(
                        tarjetaPacientes
                );

        // CONSULTAS

        PanelRedondeado tarjetaConsultas =
                crearTarjetaEstadistica(
                        "Consultas",
                        "—",
                        "Próximamente",
                        Estilo.MENTA_OSCURO
                );

        // HISTORIAS

        PanelRedondeado tarjetaHistorias =
                crearTarjetaEstadistica(
                        "Historias clínicas",
                        "—",
                        "Próximamente",
                        Estilo.MORADO_MEDIO
                );

        estadisticas.add(
                tarjetaPacientes
        );

        estadisticas.add(
                tarjetaConsultas
        );

        estadisticas.add(
                tarjetaHistorias
        );

        centro.add(estadisticas);

        centro.add(
                Box.createVerticalStrut(30)
        );

        // -----------------------------------------------------
        // ACCESOS RÁPIDOS
        // -----------------------------------------------------

        JLabel tituloAcciones =
                new JLabel(
                        "Accesos rápidos"
                );

        tituloAcciones.setFont(
                Estilo.ENCABEZADO
        );

        tituloAcciones.setForeground(
                Estilo.MORADO_OSCURO
        );

        tituloAcciones.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        centro.add(tituloAcciones);

        centro.add(
                Box.createVerticalStrut(15)
        );

        JPanel acciones =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                20,
                                0
                        )
                );

        acciones.setOpaque(false);

        acciones.add(
                crearAccion(
                        "Registrar paciente",
                        "Agregar un nuevo paciente al sistema",
                        Estilo.MORADO,
                        e -> abrirRegistrarPaciente()
                )
        );

        acciones.add(
                crearAccion(
                        "Nueva consulta",
                        "Registrar una consulta clínica",
                        Estilo.MENTA_OSCURO,
                        e -> abrirHistoria()
                )
        );

        acciones.add(
                crearAccion(
                        "Gestionar paciente",
                        "Buscar, editar o eliminar pacientes",
                        Estilo.MORADO_MEDIO,
                        e -> abrirGestionarPaciente()
                )
        );

        centro.add(acciones);

        centro.add(
                Box.createVerticalStrut(30)
        );

        // -----------------------------------------------------
        // INFORMACIÓN
        // -----------------------------------------------------

        PanelRedondeado informacion =
                new PanelRedondeado(22);

        informacion.setColorFondo(
                Estilo.BLANCO
        );

        informacion.setLayout(
                new BorderLayout()
        );

        informacion.setBorder(
                BorderFactory.createEmptyBorder(
                        22,
                        25,
                        22,
                        25
                )
        );

        JLabel icono =
                new JLabel("🦷");

        icono.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        32
                )
        );

        informacion.add(
                icono,
                BorderLayout.WEST
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

        JLabel tituloInfo =
                new JLabel(
                        "Gestión odontológica"
                );

        tituloInfo.setFont(
                Estilo.NORMAL_NEGRITA
        );

        tituloInfo.setForeground(
                Estilo.MORADO_OSCURO
        );

        JLabel descripcionInfo =
                new JLabel(
                        "Mantenga organizada la información "
                        + "de sus pacientes y sus historias clínicas."
                );

        descripcionInfo.setFont(
                Estilo.PEQUENA
        );

        descripcionInfo.setForeground(
                Estilo.GRIS_TEXTO
        );

        texto.add(tituloInfo);

        texto.add(
                Box.createVerticalStrut(5)
        );

        texto.add(descripcionInfo);

        informacion.add(
                texto,
                BorderLayout.CENTER
        );

        centro.add(informacion);

        contenido.add(
                centro,
                BorderLayout.CENTER
        );

        panelContenido.add(
                contenido,
                BorderLayout.CENTER
        );

        actualizarCantidadPacientes();

        panelContenido.revalidate();

        panelContenido.repaint();
    }

    // =========================================================
    // TARJETA DE ESTADÍSTICA
    // =========================================================

    private PanelRedondeado crearTarjetaEstadistica(
            String titulo,
            String cantidad,
            String descripcion,
            Color color) {

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
                        22,
                        20,
                        22
                )
        );

        JPanel contenido =
                new JPanel();

        contenido.setOpaque(false);

        contenido.setLayout(
                new BoxLayout(
                        contenido,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel lblTitulo =
                new JLabel(titulo);

        lblTitulo.setFont(
                Estilo.PEQUENA
        );

        lblTitulo.setForeground(
                Estilo.GRIS_TEXTO
        );

        JLabel lblCantidad =
                new JLabel(cantidad);

        lblCantidad.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        32
                )
        );

        lblCantidad.setForeground(
                color
        );

        JLabel lblDescripcion =
                new JLabel(descripcion);

        lblDescripcion.setFont(
                Estilo.PEQUENA
        );

        lblDescripcion.setForeground(
                Estilo.GRIS_SUAVE
        );

        contenido.add(lblTitulo);

        contenido.add(
                Box.createVerticalStrut(8)
        );

        contenido.add(lblCantidad);

        contenido.add(
                Box.createVerticalStrut(3)
        );

        contenido.add(lblDescripcion);

        tarjeta.add(
                contenido,
                BorderLayout.CENTER
        );

        return tarjeta;
    }

    // =========================================================
    // OBTENER LABEL DE CANTIDAD
    // =========================================================

    private JLabel obtenerEtiquetaCantidad(
            JPanel tarjeta) {

        if (tarjeta instanceof PanelRedondeado) {

            Component[] componentes =
                    tarjeta.getComponents();

            if (componentes.length > 0) {

                JPanel contenido =
                        (JPanel) componentes[0];

                Component[] elementos =
                        contenido.getComponents();

                for (Component elemento :
                        elementos) {

                    if (elemento instanceof JLabel) {

                        JLabel etiqueta =
                                (JLabel) elemento;

                        if (etiqueta
                                .getFont()
                                .getSize() == 32) {

                            return etiqueta;
                        }
                    }
                }
            }
        }

        return new JLabel("0");
    }

    // =========================================================
    // ACCESO RÁPIDO
    // =========================================================

    private JPanel crearAccion(
            String titulo,
            String descripcion,
            Color color,
            java.awt.event.ActionListener evento) {

        PanelRedondeado tarjeta =
                new PanelRedondeado(20);

        tarjeta.setColorFondo(
                Estilo.BLANCO
        );

        tarjeta.setLayout(
                new BorderLayout()
        );

        tarjeta.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        JLabel circulo =
                new JLabel("●");

        circulo.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        25
                )
        );

        circulo.setForeground(color);

        tarjeta.add(
                circulo,
                BorderLayout.WEST
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

        JLabel lblTitulo =
                new JLabel(titulo);

        lblTitulo.setFont(
                Estilo.NORMAL_NEGRITA
        );

        lblTitulo.setForeground(
                Estilo.MORADO_OSCURO
        );

        JLabel lblDescripcion =
                new JLabel(
                        "<html>"
                        + "<div style='width:180px'>"
                        + descripcion
                        + "</div>"
                        + "</html>"
                );

        lblDescripcion.setFont(
                Estilo.PEQUENA
        );

        lblDescripcion.setForeground(
                Estilo.GRIS_TEXTO
        );

        texto.add(lblTitulo);

        texto.add(
                Box.createVerticalStrut(7)
        );

        texto.add(lblDescripcion);

        tarjeta.add(
                texto,
                BorderLayout.CENTER
        );

        JButton boton =
                new JButton("→");

        boton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        20
                )
        );

        boton.setForeground(color);

        boton.setBorderPainted(false);

        boton.setContentAreaFilled(false);

        boton.setFocusPainted(false);

        boton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        boton.addActionListener(evento);

        tarjeta.add(
                boton,
                BorderLayout.EAST
        );

        return tarjeta;
    }

    // =========================================================
    // ACTUALIZAR CANTIDAD DE PACIENTES
    // =========================================================

    private void actualizarCantidadPacientes() {

        try {

            PacienteDAO dao =
                    new PacienteDAO();

            int cantidad =
                    dao.contarPacientes();

            lblCantidadPacientes.setText(
                    String.valueOf(cantidad)
            );

        } catch (Exception e) {

            lblCantidadPacientes.setText("0");

            System.out.println(
                    "Error al actualizar cantidad de pacientes:"
            );

            System.out.println(
                    e.getMessage()
            );
        }
    }

    // =========================================================
    // ABRIR REGISTRAR PACIENTE
    // =========================================================

    private void abrirRegistrarPaciente() {

        RegistrarPaciente ventana =
                new RegistrarPaciente();

        ventana.setVisible(true);
    }

    // =========================================================
    // ABRIR HISTORIA CLÍNICA
    // =========================================================

    private void abrirHistoria() {

        TarjetaPaciente ventana =
                new TarjetaPaciente();

        ventana.setVisible(true);
    }

    // =========================================================
    // ABRIR GESTIONAR PACIENTE
    // =========================================================

    private void abrirGestionarPaciente() {

        GestionarPaciente ventana =
                new GestionarPaciente();

        ventana.setVisible(true);
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(
            String[] args) {

        SwingUtilities.invokeLater(() -> {

            Principal ventana =
                    new Principal();

            ventana.setVisible(true);
        });
    }
}
