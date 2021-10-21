package ar.edu.unnoba.ui.vista;
import java.awt.*;

/**
 * Fernando Andana
 * Clase que configura los paneles necesarios para el programa.
 */
public class Panel extends PanelBase{
    //Constructores
    public Panel(LayoutManager layout, int radius) { super(layout, radius); }
    public Panel(LayoutManager layout, int radius, Color bgColor) { super(layout, radius, bgColor); }
    public Panel(int radius) { super(radius); }
    public Panel(int radius, Color bgColor) { super(radius, bgColor); }

    /**
     * Constructor principal.
     */
    public Panel(){ super(); }

    /**
     * Metodo que configura el panel de los botones.
     * @return PanelBase
     */
    public PanelBase configurarPanelBotoness(){
        Panel panel = new Panel();
        panel.setCornerRadius(45);
        panel.setBackgroundColor(new Color(133, 193, 233));
        panel.setBounds(5,130,175,365);
        panel.setLayout(null);
        panel.setOpaque(false);
        return panel;
    }
    /**
     * Metodo que configura el panel del historial de climas.
     * @return PanelBase
     */
    public PanelBase configurarPanelHistorial(){
        Panel panel = new Panel();
        panel.setCornerRadius(45);
        panel.setBackgroundColor(new Color(174, 214, 241));
        panel.setBounds(185,150,460,365);
        panel.setOpaque(false);
        return panel;
    }
    /**
     * Metodo que configura el panel que tiene los datos del clima actual.
     * @return PanelBase
     */
    public Panel configurarPanelClimaActual(){
        Panel panel = new Panel();
        panel.setCornerRadius(45);
        panel.setBackgroundColor(new Color(93, 173, 226));
        panel.setSize(640,140);
        panel.setLayout(null);
        panel.setOpaque(false);
        return panel;
    }
}
