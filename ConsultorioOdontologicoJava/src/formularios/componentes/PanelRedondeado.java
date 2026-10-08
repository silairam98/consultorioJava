package formularios.componentes;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class PanelRedondeado extends JPanel {

    private int radio = 20;
    private Color colorFondo = Color.WHITE;

    public PanelRedondeado() {

        setOpaque(false);
    }

    public PanelRedondeado(int radio) {

        this.radio = radio;
        setOpaque(false);
    }

    public void setColorFondo(Color color) {

        this.colorFondo = color;

        repaint();
    }

    public Color getColorFondo() {

        return colorFondo;
    }

    public void setRadio(int radio) {

        this.radio = radio;

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 =
                (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setColor(colorFondo);

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