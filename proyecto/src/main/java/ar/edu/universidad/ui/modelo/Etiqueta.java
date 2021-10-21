package ar.edu.unnoba.ui.modelo;
import ar.edu.unnoba.ui.vista.PanelBase;
import javax.swing.*;
import java.awt.*;

/**
 * Fernando Andana
 * Clase que inicia las etiquetas de los paneles.
 */
public class Etiqueta {
    /**
     * Constructor de la clase
     */
    public Etiqueta(){}

    /**
     * Metodo que inicia las etiquetas del panel que contiene a los botones.
     * @param panel Objeto de tipo PanelBase.
     * @return PanelBase
     */
    public PanelBase iniciarEtiquetasPanelBotones(PanelBase panel){
        Font fuenteLetras = new Font("Linux Libertine",Font.BOLD,16);
        JLabel etiquetaOrdenarPor = new JLabel("ORDENAR POR");
        etiquetaOrdenarPor.setForeground(Color.white);
        etiquetaOrdenarPor.setFont(fuenteLetras);
        etiquetaOrdenarPor.setBounds(25,0,135,25);
        panel.add(etiquetaOrdenarPor);

        //Etiquetas botones
        Font fuenteLetrasBotones = new Font("Linux Libertine",Font.BOLD,12);

        JLabel etiquetaTemperatura = new JLabel("TEMPERATURA");
        etiquetaTemperatura.setFont(fuenteLetrasBotones);
        etiquetaTemperatura.setForeground(Color.white);
        etiquetaTemperatura.setBounds(43,25,100,30);
        panel.add(etiquetaTemperatura);

        JLabel etiquetaFecha = new JLabel("FECHA");
        etiquetaFecha.setFont(fuenteLetrasBotones);
        etiquetaFecha.setForeground(Color.white);
        etiquetaFecha.setBounds(68,125,100,30);
        panel.add(etiquetaFecha);

        JLabel etiquetaLimpiarHistorial = new JLabel("ELIMINAR HISTORIAL");
        etiquetaLimpiarHistorial.setForeground(Color.white);
        etiquetaLimpiarHistorial.setFont(fuenteLetras);
        etiquetaLimpiarHistorial.setBounds(5,235,168,30);
        panel.add(etiquetaLimpiarHistorial);

        return panel;
    }

    /**
     * Metodo que inicia las etiquetas del panel que contiene al clima actual.
     * @param panel Objeto de tipo PanelBase.
     * @param imagenClimaActual Objeto de tipo JLabel.
     * @param informacionClimaActual Objeto de tipo JLabel.
     * @param imagenFondo Objeto de tipo JLabel.
     * @return PanelBase
     */
    public PanelBase iniciarEtiquetasPanelClimaActual( PanelBase panel,
                                                       JLabel imagenClimaActual,
                                                       JLabel informacionClimaActual,
                                                       JLabel imagenFondo){
        //inicializo el jlabel que contiene los iconos de los clima
        imagenClimaActual.setBounds(50,50,50,50);
        panel.add(imagenClimaActual);

        Font fuenteLetras = new Font("Linux Libertine",Font.BOLD,16);
        Color colorLetra = new Color(33, 47, 60);

        //inicializo el jlabel que contiene la informacion del clima actual
        informacionClimaActual.setText("Información del clima");
        informacionClimaActual.setFont(fuenteLetras);
        informacionClimaActual.setForeground(colorLetra);
        informacionClimaActual.setBounds(100,55,520,30);
        panel.add(informacionClimaActual);

        //Inicializo la etiqueta con el fondo.
        imagenFondo.setBounds(0,0,640,140);
        imagenFondo.setIcon(new ImageIcon("src/main/resources/fondoClimaActual.png"));
        imagenFondo.setOpaque(false);

        return panel;
    }
}
