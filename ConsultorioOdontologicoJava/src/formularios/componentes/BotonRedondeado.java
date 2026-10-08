package formularios.componentes;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class BotonRedondeado extends JButton {

    private Color colorNormal;
    private Color colorHover;
    private int radio = 18;

    public BotonRedondeado(
            String texto,
            Color colorNormal,
            Color colorHover) {

        super(texto);

        this.colorNormal = colorNormal;
        this.colorHover = colorHover;

        configurar();
    }

    private void configurar() {

        setForeground(Color.WHITE);

        setFont(
                new java.awt.Font(
                        "SansSerif",
                        java.awt.Font.BOLD,
                        14
                )
        );

        setFocusPainted(false);

        setBorderPainted(false);

        setContentAreaFilled(false);

        setOpaque(false);

        setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            java.awt.event.MouseEvent e) {

                        repaint();
                    }

                    @Override
                    public void mouseExited(
                            java.awt.event.MouseEvent e) {

                        repaint();
                    }
                }
        );
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 =
                (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        if (getModel().isRollover()) {

            g2.setColor(colorHover);

        } else {

            g2.setColor(colorNormal);
        }

        g2.fillRoundRect(
                0,
                0,
                getWidth(),
                getHeight(),
                radio,
                radio
        );

        g2.dispose();

        super.paintComponent(g);
    }
}