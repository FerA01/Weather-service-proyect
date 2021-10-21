package ar.edu.unnoba.ui.vista;

import javax.swing.*;
import java.awt.*;

/**
 * Fernando Andana
 * Clase que es hija de la clase JPanel y permite crear JPanel's con bordes redondeados.
 */
public class PanelBase extends JPanel {
    /**
     * Atributo de tipo Color.
     */
    private Color backgroundColor;
    /**
     * Atributo de tipo int.
     */
    private int cornerRadius = 15;

    /**
     * Constructor vacio.
     */
    public PanelBase(){}
    public PanelBase(LayoutManager layout, int radius) { super(layout); cornerRadius = radius; }
    public PanelBase(LayoutManager layout, int radius, Color bgColor) { super(layout); cornerRadius = radius; backgroundColor = bgColor; }
    public PanelBase(int radius) { super(); cornerRadius = radius; }
    public PanelBase(int radius, Color bgColor) { super(); cornerRadius = radius; backgroundColor = bgColor; }

    /**
     * Getter del atributo backgroundColor.
     * @return Color
     */
    public Color getBackgroundColor() { return backgroundColor; }

    /**
     * Setter del atributo backgroundColor.
     * @param backgroundColor Objeto de tipo Color
     */
    public void setBackgroundColor(Color backgroundColor) { this.backgroundColor = backgroundColor; }

    /**
     * Getter del atributo cornerRadius.
     * @return int
     */
    public int getCornerRadius() { return cornerRadius; }

    /**
     * Setter del atributo cornerRadius.
     * @param cornerRadius Objeto de tipo int
     */
    public void setCornerRadius(int cornerRadius) { this.cornerRadius = cornerRadius; }

    /**
     * Metodo que es usado para poder redondear los JPanel.
     * @param g Objeto de tipo Graphics.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(getCornerRadius(), getCornerRadius());
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Draws the rounded panel with borders.
        if (getBackgroundColor() != null) {
            graphics.setColor(getBackgroundColor());
        } else {
            graphics.setColor(getBackground());
        }
        graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint background
        graphics.setColor(getForeground());
        graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint border
    }
}
