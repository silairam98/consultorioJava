package formularios.componentes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.util.LinkedHashMap;
import java.util.Map;

public class PanelOdontograma extends JPanel {

    // =========================================================
    // COLORES
    // =========================================================

    private final Color MORADO =
            new Color(108, 76, 150);

    private final Color MENTA =
            new Color(91, 195, 165);

    private final Color FONDO =
            new Color(250, 248, 252);

    private final Color BORDE =
            new Color(190, 185, 200);

    private final Color TEXTO =
            new Color(70, 67, 78);

    // =========================================================
    // ESTADOS
    // =========================================================

    private final String[] estadosDisponibles = {
            "Sano",
            "Caries",
            "Restauración",
            "Ausente",
            "Corona",
            "Endodoncia",
            "Extracción"
    };

    // =========================================================
    // DIENTES
    // =========================================================

    private final String[] superioresDerechos = {
            "18", "17", "16", "15",
            "14", "13", "12", "11"
    };

    private final String[] superioresIzquierdos = {
            "21", "22", "23", "24",
            "25", "26", "27", "28"
    };

    private final String[] inferioresDerechos = {
            "48", "47", "46", "45",
            "44", "43", "42", "41"
    };

    private final String[] inferioresIzquierdos = {
            "31", "32", "33", "34",
            "35", "36", "37", "38"
    };

    // =========================================================
    // INFORMACIÓN
    // =========================================================

    private final Map<String, String> estados =
            new LinkedHashMap<>();

    private final Map<String, String> observaciones =
            new LinkedHashMap<>();
    
    private boolean soloLectura = false;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public PanelOdontograma() {

        setLayout(new BorderLayout());

        setBackground(FONDO);

        setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );

        inicializarEstados();

        construirPanel();
    }
    
    public PanelOdontograma(
            Map<String, String> estadosGuardados,
            Map<String, String> observacionesGuardadas) {

        this();

        this.soloLectura = true;

        estados.clear();
        observaciones.clear();

        estados.putAll(
                estadosGuardados
        );

        observaciones.putAll(
                observacionesGuardadas
        );

        construirPanel();
    }

    // =========================================================
    // INICIALIZAR
    // =========================================================

    private void inicializarEstados() {

        String[] todos = {

                "18", "17", "16", "15",
                "14", "13", "12", "11",

                "21", "22", "23", "24",
                "25", "26", "27", "28",

                "48", "47", "46", "45",
                "44", "43", "42", "41",

                "31", "32", "33", "34",
                "35", "36", "37", "38"
        };

        for (String diente : todos) {

            estados.put(
                    diente,
                    "Sano"
            );

            observaciones.put(
                    diente,
                    ""
            );
        }
    }

    // =========================================================
    // CONSTRUIR PANEL
    // =========================================================

    private void construirPanel() {

        removeAll();

        JPanel principal =
                new JPanel(
                        new BorderLayout()
                );

        principal.setOpaque(false);

        // -----------------------------------------------------
        // ENCABEZADO
        // -----------------------------------------------------

        JPanel encabezado =
                new JPanel();

        encabezado.setLayout(
                new BoxLayout(
                        encabezado,
                        BoxLayout.Y_AXIS
                )
        );

        encabezado.setOpaque(false);

        JLabel titulo =
                new JLabel(
                        "Odontograma"
                );

        titulo.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        24
                )
        );

        titulo.setForeground(MORADO);

        JLabel descripcion =
                new JLabel(
                        "Seleccione una pieza dental para registrar su estado"
                );

        descripcion.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        descripcion.setForeground(
                new Color(
                        110,
                        105,
                        118
                )
        );

        encabezado.add(titulo);

        encabezado.add(
                Box.createVerticalStrut(4)
        );

        encabezado.add(descripcion);

        principal.add(
                encabezado,
                BorderLayout.NORTH
        );

        // -----------------------------------------------------
        // ODONTOGRAMA
        // -----------------------------------------------------

        JPanel contenido =
                new JPanel(
                        new GridLayout(
                                4,
                                1,
                                0,
                                8
                        )
                );

        contenido.setOpaque(false);

        // FILA SUPERIOR

        contenido.add(
                crearFila(
                        superioresDerechos,
                        superioresIzquierdos,
                        true
                )
        );

        // TITULO SUPERIOR

        contenido.add(
                crearSeparador(
                        "MAXILAR SUPERIOR"
                )
        );

        // FILA INFERIOR

        contenido.add(
                crearFila(
                        inferioresDerechos,
                        inferioresIzquierdos,
                        false
                )
        );

        // TITULO INFERIOR

        contenido.add(
                crearSeparador(
                        "MAXILAR INFERIOR"
                )
        );

        JScrollPane scroll =
                new JScrollPane(
                        contenido
                );

        scroll.setBorder(null);

        scroll.setOpaque(false);

        scroll.getViewport()
                .setOpaque(false);

        principal.add(
                scroll,
                BorderLayout.CENTER
        );

        // -----------------------------------------------------
        // LEYENDA
        // -----------------------------------------------------

        principal.add(
                crearLeyenda(),
                BorderLayout.SOUTH
        );

        add(
                principal,
                BorderLayout.CENTER
        );

        revalidate();
        repaint();
    }

    // =========================================================
    // SEPARADOR
    // =========================================================

    private JPanel crearSeparador(
            String texto) {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setOpaque(false);

        JSeparator izquierda =
                new JSeparator();

        JSeparator derecha =
                new JSeparator();

        JLabel etiqueta =
                new JLabel(
                        texto,
                        SwingConstants.CENTER
                );

        etiqueta.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        etiqueta.setForeground(
                new Color(
                        135,
                        128,
                        145
                )
        );

        panel.add(
                izquierda,
                BorderLayout.WEST
        );

        panel.add(
                etiqueta,
                BorderLayout.CENTER
        );

        panel.add(
                derecha,
                BorderLayout.EAST
        );

        return panel;
    }

    // =========================================================
    // FILA DE DIENTES
    // =========================================================

    private JPanel crearFila(
            String[] derecha,
            String[] izquierda,
            boolean superior) {

        JPanel fila =
                new JPanel(
                        new GridLayout(
                                1,
                                17,
                                2,
                                0
                        )
                );

        fila.setOpaque(false);

        // DERECHA

        for (String numero : derecha) {

            fila.add(
                    crearDiente(
                            numero,
                            superior
                    )
            );
        }

        // CENTRO

        JPanel centro =
                new JPanel();

        centro.setOpaque(false);

        fila.add(centro);

        // IZQUIERDA

        for (String numero : izquierda) {

            fila.add(
                    crearDiente(
                            numero,
                            superior
                    )
            );
        }

        return fila;
    }

    // =========================================================
    // CREAR DIENTE
    // =========================================================

    private JPanel crearDiente(
            String numero,
            boolean superior) {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setOpaque(false);

        JLabel etiquetaNumero =
                new JLabel(
                        numero,
                        SwingConstants.CENTER
                );

        etiquetaNumero.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        etiquetaNumero.setForeground(TEXTO);

        DienteDibujado diente =
                new DienteDibujado(
                        numero,
                        superior
                );

        JLabel etiquetaEstado =
                new JLabel(
                        estados.get(numero),
                        SwingConstants.CENTER
                );

        etiquetaEstado.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        9
                )
        );

        etiquetaEstado.setForeground(
                new Color(
                        115,
                        110,
                        122
                )
        );

        panel.add(
                etiquetaNumero,
                BorderLayout.NORTH
        );

        panel.add(
                diente,
                BorderLayout.CENTER
        );

        panel.add(
                etiquetaEstado,
                BorderLayout.SOUTH
        );

        diente.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            MouseEvent e) {

                        if (!soloLectura) {

                            abrirDialogo(
                                    numero,
                                    diente,
                                    etiquetaEstado
                            );
                        }
                    }

                    @Override
                    public void mouseEntered(
                            MouseEvent e) {

                        diente.setHover(
                                !soloLectura
                        );
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e) {

                        diente.setHover(false);
                    }
                }
        );

        return panel;
    }

    // =========================================================
    // DIALOGO
    // =========================================================

    private void abrirDialogo(
            String numero,
            DienteDibujado diente,
            JLabel etiquetaEstado) {

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        8,
                        8,
                        8,
                        8
                )
        );

        JLabel titulo =
                new JLabel(
                        "Pieza dental " + numero
                );

        titulo.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        16
                )
        );

        titulo.setForeground(MORADO);

        panel.add(titulo);

        panel.add(
                Box.createVerticalStrut(10)
        );

        JLabel textoEstado =
                new JLabel(
                        "Estado:"
                );

        textoEstado.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        panel.add(textoEstado);

        JComboBox<String> comboEstado =
                new JComboBox<>(
                        estadosDisponibles
                );

        comboEstado.setSelectedItem(
                estados.get(numero)
        );

        comboEstado.setMaximumSize(
                new Dimension(
                        300,
                        32
                )
        );

        panel.add(comboEstado);

        panel.add(
                Box.createVerticalStrut(10)
        );

        JLabel textoObservacion =
                new JLabel(
                        "Observación:"
                );

        textoObservacion.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        panel.add(textoObservacion);

        JTextArea observacion =
                new JTextArea(
                        this.observaciones
                                .get(numero)
                );

        observacion.setRows(4);

        observacion.setLineWrap(true);

        observacion.setWrapStyleWord(true);

        JScrollPane scroll =
                new JScrollPane(
                        observacion
                );

        scroll.setPreferredSize(
                new Dimension(
                        300,
                        90
                )
        );

        panel.add(scroll);

        int resultado =
                JOptionPane.showConfirmDialog(
                        this,
                        panel,
                        "Información dental",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

        if (resultado ==
                JOptionPane.OK_OPTION) {

            String nuevoEstado =
                    comboEstado
                            .getSelectedItem()
                            .toString();

            String nuevaObservacion =
                    observacion
                            .getText()
                            .trim();

            estados.put(
                    numero,
                    nuevoEstado
            );

            this.observaciones.put(
                    numero,
                    nuevaObservacion
            );

            etiquetaEstado.setText(
                    nuevoEstado
            );

            diente.setEstado(
                    nuevoEstado
            );

            diente.repaint();
        }
    }

    // =========================================================
    // DIBUJO DEL DIENTE
    // =========================================================

    private class DienteDibujado
            extends JPanel {

        private String numero;

        private boolean superior;

        private String estado;

        private boolean hover = false;

        public DienteDibujado(
                String numero,
                boolean superior) {

            this.numero = numero;

            this.superior = superior;

            this.estado =
                    estados.get(numero);

            setOpaque(false);

            setPreferredSize(
                    new Dimension(
                            48,
                            78
                    )
            );

            setCursor(
                    new Cursor(
                            Cursor.HAND_CURSOR
                    )
            );
        }

        public void setEstado(
                String estado) {

            this.estado = estado;

            repaint();
        }

        public void setHover(
                boolean hover) {

            this.hover = hover;

            repaint();
        }

        @Override
        protected void paintComponent(
                Graphics g) {

            super.paintComponent(g);

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            int ancho =
                    getWidth();

            int alto =
                    getHeight();

            String tipo =
                    obtenerTipoDiente(numero);

            // -------------------------------------------------
            // DIENTE AUSENTE
            // -------------------------------------------------

            if (estado.equals("Ausente")) {

                dibujarAusente(
                        g2,
                        ancho,
                        alto
                );

                g2.dispose();

                return;
            }

            // -------------------------------------------------
            // SOMBRA
            // -------------------------------------------------

            g2.setColor(
                    new Color(
                            0,
                            0,
                            0,
                            18
                    )
            );

            Shape sombra =
                    crearFormaCorona(
                            tipo,
                            ancho,
                            alto,
                            2,
                            4
                    );

            g2.fill(sombra);

            // -------------------------------------------------
            // CUERPO DEL DIENTE
            // -------------------------------------------------

            Shape corona =
                    crearFormaCorona(
                            tipo,
                            ancho,
                            alto,
                            0,
                            0
                    );

            g2.setColor(
                    new Color(
                            255,
                            255,
                            253
                    )
            );

            g2.fill(corona);

            // -------------------------------------------------
            // BORDE
            // -------------------------------------------------

            if (hover) {

                g2.setColor(MENTA);

                g2.setStroke(
                        new BasicStroke(
                                2.5f
                        )
                );

            } else {

                g2.setColor(BORDE);

                g2.setStroke(
                        new BasicStroke(
                                1.3f
                        )
                );
            }

            g2.draw(corona);

            // -------------------------------------------------
            // DETALLES SEGÚN TIPO
            // -------------------------------------------------

            if (tipo.equals("INCISIVO")) {

                dibujarIncisivo(
                        g2,
                        ancho,
                        alto
                );

            } else if (tipo.equals("CANINO")) {

                dibujarCanino(
                        g2,
                        ancho,
                        alto
                );

            } else if (tipo.equals("PREMOLAR")) {

                dibujarPremolar(
                        g2,
                        ancho,
                        alto
                );

            } else {

                dibujarMolar(
                        g2,
                        ancho,
                        alto
                );
            }

            // -------------------------------------------------
            // ESTADOS
            // -------------------------------------------------

            dibujarEstado(
                    g2,
                    ancho,
                    alto
            );

            g2.dispose();
        }

        // =====================================================
        // FORMA GENERAL DE LA CORONA
        // =====================================================

        private Shape crearFormaCorona(
                String tipo,
                int ancho,
                int alto,
                int desplazamientoX,
                int desplazamientoY) {

            int centroX =
                    ancho / 2 + desplazamientoX;

            GeneralPath path =
                    new GeneralPath();

            int inicioY =
                    8 + desplazamientoY;

            int finalY =
                    Math.min(
                            alto - 18,
                            70
                    ) + desplazamientoY;

            if (tipo.equals("INCISIVO")) {

                path.moveTo(
                        centroX - 18,
                        inicioY
                );

                path.curveTo(
                        centroX - 25,
                        12,
                        centroX - 25,
                        40,
                        centroX - 17,
                        finalY
                );

                path.curveTo(
                        centroX - 10,
                        finalY + 8,
                        centroX + 10,
                        finalY + 8,
                        centroX + 17,
                        finalY
                );

                path.curveTo(
                        centroX + 25,
                        40,
                        centroX + 25,
                        12,
                        centroX + 18,
                        inicioY
                );

                path.closePath();

            } else if (tipo.equals("CANINO")) {

                path.moveTo(
                        centroX,
                        inicioY - 4
                );

                path.curveTo(
                        centroX - 10,
                        10,
                        centroX - 25,
                        20,
                        centroX - 22,
                        48
                );

                path.curveTo(
                        centroX - 20,
                        finalY,
                        centroX - 10,
                        finalY + 6,
                        centroX,
                        finalY + 6
                );

                path.curveTo(
                        centroX + 10,
                        finalY + 6,
                        centroX + 20,
                        finalY,
                        centroX + 22,
                        48
                );

                path.curveTo(
                        centroX + 25,
                        20,
                        centroX + 10,
                        10,
                        centroX,
                        inicioY - 4
                );

                path.closePath();

            } else if (tipo.equals("PREMOLAR")) {

                path.moveTo(
                        centroX - 22,
                        inicioY + 5
                );

                path.curveTo(
                        centroX - 28,
                        25,
                        centroX - 25,
                        50,
                        centroX - 16,
                        finalY
                );

                path.curveTo(
                        centroX - 8,
                        finalY + 7,
                        centroX + 8,
                        finalY + 7,
                        centroX + 16,
                        finalY
                );

                path.curveTo(
                        centroX + 25,
                        50,
                        centroX + 28,
                        25,
                        centroX + 22,
                        inicioY + 5
                );

                path.curveTo(
                        centroX + 10,
                        0,
                        centroX - 10,
                        0,
                        centroX - 22,
                        inicioY + 5
                );

                path.closePath();

            } else {

                path.moveTo(
                        centroX - 25,
                        inicioY + 5
                );

                path.curveTo(
                        centroX - 32,
                        20,
                        centroX - 31,
                        50,
                        centroX - 21,
                        finalY
                );

                path.curveTo(
                        centroX - 10,
                        finalY + 8,
                        centroX + 10,
                        finalY + 8,
                        centroX + 21,
                        finalY
                );

                path.curveTo(
                        centroX + 31,
                        50,
                        centroX + 32,
                        20,
                        centroX + 25,
                        inicioY + 5
                );

                path.curveTo(
                        centroX + 10,
                        0,
                        centroX - 10,
                        0,
                        centroX - 25,
                        inicioY + 5
                );

                path.closePath();
            }

            return path;
        }

        // =====================================================
        // INCISIVO
        // =====================================================

        private void dibujarIncisivo(
                Graphics2D g2,
                int ancho,
                int alto) {

            int cx =
                    ancho / 2;

            g2.setColor(
                    new Color(
                            225,
                            222,
                            215
                    )
            );

            g2.setStroke(
                    new BasicStroke(1)
            );

            g2.drawLine(
                    cx,
                    20,
                    cx,
                    62
            );

            g2.drawLine(
                    cx - 10,
                    28,
                    cx - 7,
                    58
            );

            g2.drawLine(
                    cx + 10,
                    28,
                    cx + 7,
                    58
            );
        }

        // =====================================================
        // CANINO
        // =====================================================

        private void dibujarCanino(
                Graphics2D g2,
                int ancho,
                int alto) {

            int cx =
                    ancho / 2;

            g2.setColor(
                    new Color(
                            220,
                            217,
                            210
                    )
            );

            g2.setStroke(
                    new BasicStroke(1.2f)
            );

            g2.drawLine(
                    cx,
                    12,
                    cx - 5,
                    60
            );

            g2.drawLine(
                    cx,
                    12,
                    cx + 5,
                    60
            );
        }

        // =====================================================
        // PREMOLAR
        // =====================================================

        private void dibujarPremolar(
                Graphics2D g2,
                int ancho,
                int alto) {

            int cx =
                    ancho / 2;

            g2.setColor(
                    new Color(
                            215,
                            212,
                            205
                    )
            );

            g2.setStroke(
                    new BasicStroke(1.2f)
            );

            // Dos cúspides

            g2.drawOval(
                    cx - 15,
                    25,
                    11,
                    12
            );

            g2.drawOval(
                    cx + 4,
                    25,
                    11,
                    12
            );

            g2.drawLine(
                    cx,
                    32,
                    cx,
                    62
            );
        }

        // =====================================================
        // MOLAR
        // =====================================================

        private void dibujarMolar(
                Graphics2D g2,
                int ancho,
                int alto) {

            int cx =
                    ancho / 2;

            g2.setColor(
                    new Color(
                            215,
                            212,
                            205
                    )
            );

            g2.setStroke(
                    new BasicStroke(1.2f)
            );

            // Surcos de la corona

            g2.drawLine(
                    cx,
                    22,
                    cx,
                    60
            );

            g2.drawLine(
                    cx - 20,
                    40,
                    cx + 20,
                    40
            );

            // Cúspides

            g2.drawOval(
                    cx - 18,
                    22,
                    9,
                    9
            );

            g2.drawOval(
                    cx + 9,
                    22,
                    9,
                    9
            );

            g2.drawOval(
                    cx - 18,
                    49,
                    9,
                    9
            );

            g2.drawOval(
                    cx + 9,
                    49,
                    9,
                    9
            );
        }

        // =====================================================
        // RAÍCES Y ESTADOS
        // =====================================================

        private void dibujarEstado(
                Graphics2D g2,
                int ancho,
                int alto) {

            int cx =
                    ancho / 2;

            // RAÍCES

            g2.setColor(
                    new Color(
                            215,
                            212,
                            205
                    )
            );

            g2.setStroke(
                    new BasicStroke(
                            1.3f
                    )
            );

            String tipo =
                    obtenerTipoDiente(numero);

            if (tipo.equals("MOLAR")) {

                dibujarRaiz(
                        g2,
                        cx - 13,
                        68,
                        cx - 10,
                        94
                );

                dibujarRaiz(
                        g2,
                        cx,
                        68,
                        cx,
                        98
                );

                dibujarRaiz(
                        g2,
                        cx + 13,
                        68,
                        cx + 10,
                        94
                );

            } else if (tipo.equals("PREMOLAR")) {

                dibujarRaiz(
                        g2,
                        cx - 5,
                        68,
                        cx - 4,
                        96
                );

                dibujarRaiz(
                        g2,
                        cx + 5,
                        68,
                        cx + 4,
                        94
                );

            } else {

                dibujarRaiz(
                        g2,
                        cx,
                        68,
                        cx,
                        98
                );
            }

            // -------------------------------------------------
            // CARIES
            // -------------------------------------------------

            if (estado.equals("Caries")) {

                g2.setColor(
                        new Color(
                                210,
                                70,
                                80
                        )
                );

                g2.fillOval(
                        cx + 8,
                        38,
                        9,
                        9
                );
            }

            // -------------------------------------------------
            // RESTAURACIÓN
            // -------------------------------------------------

            if (estado.equals(
                    "Restauración")) {

                g2.setColor(MORADO);

                g2.fillRoundRect(
                        cx - 7,
                        33,
                        14,
                        12,
                        4,
                        4
                );
            }

            // -------------------------------------------------
            // CORONA
            // -------------------------------------------------

            if (estado.equals("Corona")) {

                g2.setColor(
                        new Color(
                                225,
                                175,
                                50
                        )
                );

                g2.setStroke(
                        new BasicStroke(
                                3
                        )
                );

                Shape corona =
                        crearFormaCorona(
                                obtenerTipoDiente(
                                        numero
                                ),
                                ancho,
                                alto,
                                0,
                                0
                        );

                g2.draw(corona);
            }

            // -------------------------------------------------
            // ENDODONCIA
            // -------------------------------------------------

            if (estado.equals(
                    "Endodoncia")) {

                g2.setColor(
                        new Color(
                                65,
                                130,
                                205
                        )
                );

                g2.setStroke(
                        new BasicStroke(
                                2
                        )
                );

                g2.drawLine(
                        cx,
                        38,
                        cx,
                        88
                );
            }

            // -------------------------------------------------
            // EXTRACCIÓN
            // -------------------------------------------------

            if (estado.equals(
                    "Extracción")) {

                g2.setColor(
                        new Color(
                                190,
                                65,
                                100
                        )
                );

                g2.setStroke(
                        new BasicStroke(
                                2.5f
                        )
                );

                g2.drawLine(
                        10,
                        15,
                        ancho - 10,
                        95
                );

                g2.drawLine(
                        ancho - 10,
                        15,
                        10,
                        95
                );
            }
        }

        // =====================================================
        // RAÍZ
        // =====================================================

        private void dibujarRaiz(
                Graphics2D g2,
                int x1,
                int y1,
                int x2,
                int y2) {

            g2.drawLine(
                    x1,
                    y1,
                    x2,
                    y2
            );
        }

        // =====================================================
        // AUSENTE
        // =====================================================

        private void dibujarAusente(
                Graphics2D g2,
                int ancho,
                int alto) {

            int cx =
                    ancho / 2;

            g2.setColor(
                    new Color(
                            160,
                            155,
                            165
                    )
            );

            g2.setStroke(
                    new BasicStroke(
                            2
                    )
            );

            g2.drawLine(
                    cx - 20,
                    25,
                    cx + 20,
                    75
            );

            g2.drawLine(
                    cx + 20,
                    25,
                    cx - 20,
                    75
            );
        }
    }

    // =========================================================
    // TIPO DE DIENTE
    // =========================================================

    private String obtenerTipoDiente(
            String numero) {

        int n =
                Integer.parseInt(
                        numero
                );

        int unidad =
                n % 10;

        if (unidad == 1 ||
                unidad == 2) {

            return "INCISIVO";
        }

        if (unidad == 3) {

            return "CANINO";
        }

        if (unidad == 4 ||
                unidad == 5) {

            return "PREMOLAR";
        }

        return "MOLAR";
    }

    // =========================================================
    // LEYENDA
    // =========================================================

    private JPanel crearLeyenda() {

        JPanel panel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                12,
                                8
                        )
                );

        panel.setOpaque(false);

        for (String estado :
                estadosDisponibles) {

            JLabel indicador =
                    new JLabel("●");

            indicador.setFont(
                    new Font(
                            "SansSerif",
                            Font.BOLD,
                            13
                    )
            );

            indicador.setForeground(
                    obtenerColorEstado(
                            estado
                    )
            );

            JLabel texto =
                    new JLabel(
                            estado
                    );

            texto.setFont(
                    new Font(
                            "SansSerif",
                            Font.PLAIN,
                            10
                    )
            );

            texto.setForeground(TEXTO);

            JPanel elemento =
                    new JPanel(
                            new FlowLayout(
                                    FlowLayout.LEFT,
                                    3,
                                    0
                            )
                    );

            elemento.setOpaque(false);

            elemento.add(indicador);
            elemento.add(texto);

            panel.add(elemento);
        }

        return panel;
    }

    // =========================================================
    // COLORES DE ESTADOS
    // =========================================================

    private Color obtenerColorEstado(
            String estado) {

        switch (estado) {

            case "Caries":
                return new Color(
                        220,
                        80,
                        90
                );

            case "Restauración":
                return MORADO;

            case "Ausente":
                return new Color(
                        140,
                        140,
                        140
                );

            case "Corona":
                return new Color(
                        225,
                        175,
                        50
                );

            case "Endodoncia":
                return new Color(
                        65,
                        130,
                        205
                );

            case "Extracción":
                return new Color(
                        190,
                        65,
                        100
                );

            default:
                return MENTA;
        }
    }

    // =========================================================
    // OBTENER ESTADOS
    // =========================================================

    public Map<String, String> getEstados() {

        return new LinkedHashMap<>(
                estados
        );
    }

    // =========================================================
    // OBTENER OBSERVACIONES
    // =========================================================

    public Map<String, String> getObservaciones() {

        return new LinkedHashMap<>(
                observaciones
        );
    }

    // =========================================================
    // LIMPIAR
    // =========================================================

    public void limpiar() {

        for (String diente :
                estados.keySet()) {

            estados.put(
                    diente,
                    "Sano"
            );

            observaciones.put(
                    diente,
                    ""
            );
        }

        construirPanel();
    }
}