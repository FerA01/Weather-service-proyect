package ar.edu.unnoba.ui.modelo;
import ar.edu.unnoba.model.Channel;
import ar.edu.unnoba.ui.controlador.Controlador;
import ar.edu.unnoba.ui.vista.PanelBase;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Fernando Andana
 * Clase que inicia y configura un JList y un JScrollPane
 */
public class ListaHistorialClima{

    /**
     * Constructor
     */
    public ListaHistorialClima(){}

    /**
     * Metodo que devuelve un JList configurado
     * @param controlador Objeto de tipo Controlador
     * @param panel Objeto de tipo PanelBase
     * @param modelo Objeto de tipo Modelo2
     * @return Jlist
     */
    public JList configurarHistorial(Controlador controlador, PanelBase panel, Modelo2 modelo){
        JList<Channel> historial = new JList<>();
        historial.setFixedCellWidth(430);
        historial.setFixedCellHeight(25);
        historial.setOpaque(false);
        panel.add(historial);

        historial.setModel(modelo);
        historial.setCellRenderer(new Renderer2());

        return historial;
    }

    /**
     * Metodo que le agrega un evento al JList pasado por parametro.
     * @param historial Objeto de tipo JList
     * @param controlador Objeto de tipo Controlador
     * @param modelo Objeto de tipo Modelo2
     */
    public void asignarEventoHistorial(JList<Channel> historial,
                                       Controlador controlador,
                                       Modelo2 modelo){
        //Al hacer doble click en un elemento del historial muestro otra ventana con sus datos.
        historial.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2) {
                    controlador.lanzarVentanaEmergenteFrame2(historial, modelo);
                    System.out.println("Doble Click");
                }
            }
        });
    }

    /**
     * Metodo que devuelve un JScrollPanel configurado.
     * @param panel Objeto de tipo PanelBase
     * @param historial Objeto de tipo JList
     * @return JScrollPane
     */
    public JScrollPane configurarScrollPane(PanelBase panel, JList<Channel> historial){
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(440,355));
        scrollPane.setViewportView(historial);
        scrollPane.getViewport().setBackground(new Color(174, 214, 241));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(BorderLayout.CENTER,scrollPane);
        return scrollPane;
    }
}
