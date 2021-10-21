package ar.edu.unnoba.ui.modelo;
import ar.edu.unnoba.model.Channel;
import ar.edu.unnoba.ui.vista.PanelBase;
import javax.swing.*;
import java.awt.*;

/**
 * Fernando Andana
 * Clase usada para renderizar los datos del modelo en celdas.
 */
public class Renderer2 implements ListCellRenderer<Channel>{
    /**
     * Configuración de como seran visulizados los datos del clima.
     * @param jList Objeto de tipo JList
     * @param channel Objeto de tipo Channel
     * @param i Objeto de tipo int
     * @param b Objeto de tipo boolean
     * @param b1 Objeto de tipo boolean
     * @return Component
     */
    @Override
    public Component getListCellRendererComponent(JList<? extends Channel> jList,
                                                  Channel channel,
                                                  int i,
                                                  boolean b,
                                                  boolean b1) {
        PanelBase vista = new PanelBase(20,new Color(21, 67, 96));
        vista.setLayout(new FlowLayout());
        Font fuenteLetras = new Font("Linux Libertine",Font.BOLD,12);
        vista.setSize(447,25);

        String temperatura = String.format("%.1f",channel.getTemperature());
        String fecha = channel.getRequestedOn();
        String datos = channel.getLocation() + " - " + temperatura + "°C" + " - " + channel.getDescription()
                + " - " + fecha;

        JLabel mensaje = new JLabel(datos,SwingConstants.CENTER);
        mensaje.setSize(295,25);
        mensaje.setForeground(Color.white);
        mensaje.setFont(fuenteLetras);
        mensaje.setOpaque(false);

        vista.add(BorderLayout.CENTER,mensaje);
        vista.setOpaque(false);
        return vista;
    }
}
