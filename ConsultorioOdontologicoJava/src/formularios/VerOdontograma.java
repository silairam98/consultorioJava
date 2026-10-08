package formularios;

import dao.OdontogramaDAO;
import formularios.componentes.PanelOdontograma;
import modelo.Odontograma;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class VerOdontograma extends JFrame {

    private int historialId;

    public VerOdontograma(int historialId) {

        this.historialId = historialId;

        configurarVentana();

        cargarOdontograma();
    }

    // =========================================================
    // CONFIGURAR VENTANA
    // =========================================================

    private void configurarVentana() {

        setTitle(
                "Odontograma - Historia clínica"
        );

        setSize(
                950,
                650
        );

        setMinimumSize(
                new Dimension(
                        850,
                        600
                )
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        getContentPane().setBackground(
                Estilo.FONDO
        );
    }

    // =========================================================
    // CARGAR ODONTOGRAMA
    // =========================================================

    private void cargarOdontograma() {

        OdontogramaDAO dao =
                new OdontogramaDAO();

        List<Odontograma> lista =
                dao.buscarPorHistorial(
                        historialId
                );

        // -----------------------------------------------------
        // MAPAS
        // -----------------------------------------------------

        Map<String, String> estados =
                new LinkedHashMap<>();

        Map<String, String> observaciones =
                new LinkedHashMap<>();

        // -----------------------------------------------------
        // RECORRER LOS DIENTES
        // -----------------------------------------------------

        for (Odontograma diente : lista) {

            estados.put(
                    diente.getNumeroDiente(),
                    diente.getEstado()
            );

            observaciones.put(
                    diente.getNumeroDiente(),
                    diente.getObservacion()
            );
        }

        // -----------------------------------------------------
        // COMPLETAR LOS 32 DIENTES
        // -----------------------------------------------------

        String[] todosLosDientes = {

                "18", "17", "16", "15",
                "14", "13", "12", "11",

                "21", "22", "23", "24",
                "25", "26", "27", "28",

                "48", "47", "46", "45",
                "44", "43", "42", "41",

                "31", "32", "33", "34",
                "35", "36", "37", "38"
        };

        for (String numero :
                todosLosDientes) {

            if (!estados.containsKey(numero)) {

                estados.put(
                        numero,
                        "Sano"
                );

                observaciones.put(
                        numero,
                        ""
                );
            }
        }

        // -----------------------------------------------------
        // PANEL DEL ODONTOGRAMA
        // -----------------------------------------------------

        PanelOdontograma panel =
                new PanelOdontograma(
                        estados,
                        observaciones
                );

        // -----------------------------------------------------
        // ENCABEZADO
        // -----------------------------------------------------

        JPanel encabezado =
                new JPanel(
                        new BorderLayout()
                );

        encabezado.setOpaque(false);

        encabezado.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        20,
                        10,
                        20
                )
        );

        JLabel titulo =
                new JLabel(
                        "Odontograma de la consulta"
                );

        titulo.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        24
                )
        );

        titulo.setForeground(
                Estilo.MORADO
        );

        JLabel subtitulo =
                new JLabel(
                        "Historia clínica #" +
                        historialId
                );

        subtitulo.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        subtitulo.setForeground(
                Estilo.GRIS_TEXTO
        );

        JPanel textos =
                new JPanel();

        textos.setLayout(
                new BoxLayout(
                        textos,
                        BoxLayout.Y_AXIS
                )
        );

        textos.setOpaque(false);

        textos.add(titulo);

        textos.add(
                Box.createVerticalStrut(4)
        );

        textos.add(subtitulo);

        encabezado.add(
                textos,
                BorderLayout.WEST
        );

        // -----------------------------------------------------
        // SCROLL
        // -----------------------------------------------------

        JScrollPane scroll =
                new JScrollPane(
                        panel
                );

        scroll.setBorder(null);

        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );

        scroll.getVerticalScrollBar()
                .setUnitIncrement(16);

        scroll.getHorizontalScrollBar()
                .setUnitIncrement(16);

        // -----------------------------------------------------
        // PANEL PRINCIPAL
        // -----------------------------------------------------

        JPanel principal =
                new JPanel(
                        new BorderLayout()
                );

        principal.setBackground(
                Estilo.FONDO
        );

        principal.add(
                encabezado,
                BorderLayout.NORTH
        );

        principal.add(
                scroll,
                BorderLayout.CENTER
        );

        setContentPane(
                principal
        );
    }

    // =========================================================
    // PRUEBA
    // =========================================================

    public static void main(
            String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    VerOdontograma ventana =
                            new VerOdontograma(1);

                    ventana.setVisible(true);
                }
        );
    }
}