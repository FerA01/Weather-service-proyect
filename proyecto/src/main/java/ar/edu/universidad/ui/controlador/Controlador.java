package ar.edu.unnoba.ui.controlador;
import ar.edu.unnoba.model.Channel;
import ar.edu.unnoba.ui.modelo.EstacionClima;
import ar.edu.unnoba.ui.modelo.Modelo;
import ar.edu.unnoba.ui.vista.PanelBase;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;

/**
 * Creado por Fernando Andana
 */

/**
 * Clase Controlador
 * Esta clase contiene todos los metodos necesarios para el funcionamiento del servicio del clima
 */
public class Controlador{

    /**
     * Constructor para iniciarlizar la clase Controlador
     */
    public Controlador(){}

    /**
     * Metodo que permite agregar el objeto Channel a la estación del clima y al modelo.
     * @param clima Objeto de tipo Channel.
     * @param estacionClima Objeto de tipo EstacionClima.
     * @param modelo Objeto de tipo Modelo2.
     */
    public void agregarClima(Channel clima, EstacionClima estacionClima, Modelo modelo){
        estacionClima.agregar(clima);
        modelo.agregar(clima);
    }

    /**
     * Metodo que vacia las listas que contiene la estacion del clima y el modelo.
     * @param modelo Objeto de tipo Modelo.
     * @param historial Objeto de JList que almacena Channels
     * @param estacionClima Objeto de tipo EstacionClima.
     */
    public void eliminarHistorial(Modelo2 modelo, JList historial, EstacionClima estacionClima){
        modelo.eliminarTodo();
        historial.clearSelection();
        estacionClima.eliminarTodo();
    }

    /**
     * Metodo que ordena las listas de la estación del clima y el modelo por fecha.
     * @param modelo Objeto de tipo Modelo.
     * @param estacionClima Objeto de tipo EstacionClima.
     */
    public void ordenarPorFecha(Modelo modelo, EstacionClima estacionClima){
        modelo.ordenarFecha();
        estacionClima.ordenarPorFecha();
    }

    /**
     * Metodo que ordena las listas de la estación del clima y el modelo por tempereatura.
     * @param modelo Objeto de tipo Modelo2.
     * @param estacionClima Objeto de tipo Estación del clima.
     */
    public void ordenarPorTemperatura(Modelo modelo, EstacionClima estacionClima){
        modelo.ordenarTemperatura();
        estacionClima.ordenarPorTemperatura();
    }

    /**
     * Metodo que configura los botones pasados por parametros y finalmente son agregados al panel.
     * @param unPanel Objeto de tipo PanelBase (JPanel).
     * @param botonTemperatura  Objeto de tipo JButton.
     * @param botonFecha Objeto de tipo JButton.
     * @param botonLimpiar Objeto de tipo JButton.
     */
    public void iniciarBotones(PanelBase unPanel, JButton botonTemperatura, JButton botonFecha, JButton botonLimpiar){
        //boton temperatura
        ImageIcon imagenTemperatura = new ImageIcon("src/main/resources/temperatura.png");
        botonTemperatura.setIcon(imagenTemperatura);
        botonTemperatura.setBorder(BorderFactory.createLineBorder(new Color(235, 245, 251),4));
        botonTemperatura.setBounds(55,50,64,64);
        unPanel.add(botonTemperatura);

        //boton fecha
        ImageIcon imagenFecha = new ImageIcon("src/main/resources/calendario.png");
        botonFecha.setIcon(imagenFecha);
        botonFecha.setBorder(BorderFactory.createLineBorder(new Color(235, 245, 251),4));
        botonFecha.setBounds(55,150,64,64);
        unPanel.add(botonFecha);


        //boton limpiar
        ImageIcon imagenTacho = new ImageIcon("src/main/resources/rechazado.png");
        botonLimpiar.setIcon(imagenTacho);
        botonLimpiar.setBorder(BorderFactory.createLineBorder(new Color(235, 245, 251),4));
        botonLimpiar.setBorder(new EmptyBorder(10,10,10,10));
        botonLimpiar.setBounds(55,270,64,64);
        unPanel.add(botonLimpiar);
    }

    /**
     * Metodo que es llamado cuando se presiona un botón.
     * La función principal es vaciar las listas de la estacion del clima y del modelo, además de eliminar la imagén
     * y el texto de 2 JLabel.
     * @param evento Objeto de tipo ActionEvent
     * @param modelo Objeto de tipo Modelo.
     * @param estacionClima Objeto de tipo EstacionClima.
     * @param historial Objeto de tipo JList que almacena Channels.
     * @param fotoClima Objeto de tipo JLabel, se elimina la imagen que contiene.
     * @param etiquetaInformacionClimaActual Objeto de tipo JLabel, se deja la etiqueta vacía.
     */
    public void accionLimpiarHistorial(ActionEvent evento,
                                       Modelo modelo,
                                       EstacionClima estacionClima,
                                       JList historial,
                                       JLabel fotoClima,
                                       JLabel etiquetaInformacionClimaActual){
        reiniciarEtiquetasClimaActual(fotoClima,etiquetaInformacionClimaActual);
        modelo.eliminarTodo();
        estacionClima.eliminarTodo();
        historial.clearSelection();
        historial.ensureIndexIsVisible(modelo.getSize());
        System.out.println("Limpiado");
    }

    /**
     * Metodo que ordena la lista del modelo por la temperatura.
     * @param event Objeto de tipo ActionEvent.
     * @param modelo Objeto de tipo Modelo.
     */
    public void accionOrdenarListaTemperatura(ActionEvent event, Modelo2 modelo){
        modelo.ordenarTemperatura();
        System.out.println("Ordenado por Temperatura");
    }
    /**
     * Metodo que ordena la lista del modelo por la fecha.
     * @param event Objeto de tipo ActionEvent.
     * @param modelo Objeto de tipo Modelo.
     */
    public void accionOrdenarListaFecha(ActionEvent event, Modelo modelo){
        modelo.ordenarFecha();
        System.out.println("Ordenado por fecha");
    }
    private void reiniciarEtiquetasClimaActual(JLabel fotoClima, JLabel etiquetaClimaActual){
        etiquetaClimaActual.setText("Limpiando lista");
        fotoClima.setIcon(null);
    }


    /**
     * Metodo que cambia el texto del JLabel por el string pasado por parametro.
     * @param etiquetaInformacionClimaActual Objeto de tipo JLabel.
     * @param informacionClima Objeto de tipo String.
     */
    public void cambiarInformacionClimaActual(JLabel etiquetaInformacionClimaActual,String informacionClima){ etiquetaInformacionClimaActual.setText(informacionClima); }

    /**
     * Metodo que obtiene datos a partir del Channel que es pasado por parametro, devolviendo un string de datos.
     * @param channel Objeto de tipo Channel.
     * @return String
     */
    public String obtenerDatos(Channel channel){
        String temperatura = String.format("%.1f",channel.getTemperature());
        String fecha = channel.getRequestedOn();
        String datos = channel.getLocation() + " - " + temperatura + "°C" + " - " + channel.getDescription() + " - " + fecha;
        return datos;
    }

    /**
     * Metodo que devuelve un objeto Icon a partir de lo pasado por parametro.
     * @param channel Objeto de tipo Channel, este contiene un atributo de tipo Icon.
     * @return Icon Devuelvo el objeto Icon.
     */
    public Icon cargarFoto(Channel channel){
        try{
            return new ImageIcon(new URL(channel.getWeatherIconUrl()));
        }catch (java.net.MalformedURLException error){
            System.out.println("Error al cargar la imagen");
        }
        return null;
    }

    /**
     * Metodo que cambia la imagen del JLabel pasado por parametro.
     * @param etiquetaImagenClimaActual Objeto de tipo JLabel.
     * @param imagen Objeto de tipo ImageIcon.
     */
    public void cambiarFotoClima(JLabel etiquetaImagenClimaActual,ImageIcon imagen){ etiquetaImagenClimaActual.setIcon(imagen); }

    /**
     * Metodo que cambia la imagen de un JLabel segun el string pasado por parametro.
     * @param etiquetaImagenFondo Objeto de tipo JLabel.
     * @param etiquetaImagenClimaActual Objeto de tipo JLabel.
     * @param estado Objeto de tipo String.
     */
    public void cambiarFondo(JLabel etiquetaImagenFondo, JLabel etiquetaImagenClimaActual,String estado){
        switch (estado){
            case "Cielo Claro":
                etiquetaImagenFondo.setIcon(new ImageIcon("src/main/resources/cieloSoleado.png"));
                Font fuenteLetrasCieloClaro = new Font("Linux Libertine",Font.BOLD,15);
                etiquetaImagenClimaActual.setFont(fuenteLetrasCieloClaro);
                break;
            case "Nubes":
                etiquetaImagenFondo.setIcon(new ImageIcon("src/main/resources/Nubes.png"));
                Font fuenteLetrasNubes = new Font("Linux Libertine",Font.BOLD,16);
                etiquetaImagenClimaActual.setFont(fuenteLetrasNubes);
                break;
            case "Lluvia Ligera":
                etiquetaImagenFondo.setIcon(new ImageIcon("src/main/resources/cieloLluvioso.png"));
                etiquetaImagenClimaActual.setForeground(Color.black);
                break;
            case "Muy Nuboso":
                etiquetaImagenFondo.setIcon(new ImageIcon("src/main/resources/cieloNublado.png"));
                Font fuenteLetrasMuyNuboso = new Font("Linux Libertine",Font.BOLD,16);
                etiquetaImagenClimaActual.setFont(fuenteLetrasMuyNuboso);
                break;
            case "Algo De Nubes":
                etiquetaImagenFondo.setIcon(new ImageIcon("src/main/resources/cieloAlgoNubes.png"));
                Font fuenteLetrasAlgoNubes = new Font("Linux Libertine",Font.BOLD,14);
                etiquetaImagenClimaActual.setForeground(Color.black);
                etiquetaImagenClimaActual.setFont(fuenteLetrasAlgoNubes);
                break;
            case "Nubes Dispersas":
                etiquetaImagenFondo.setIcon(new ImageIcon("src/main/resources/nubesDispersas.png"));
                Font fuenteLetras = new Font("Linux Libertine",Font.BOLD,15);
                etiquetaImagenClimaActual.setFont(fuenteLetras);
                break;
            default:
                break;
        }
    }

    /**
     * Metodo que lanza una ventana emergente cuando se da 2 clicks en un elemento del JList.
     * @param historial Objeto de tipo JList.
     * @param modelo Objeto de tipo Modelo.
     */
    public void lanzarVentanaEmergenteFrame2(  JList<Channel> historial,
                                               Modelo modelo){
        int seleccionado = historial.getSelectedIndex();
        Channel channel = modelo.getElementAt(seleccionado);

        JFrame ventanaEmergente = new JFrame();
        ventanaEmergente.setPreferredSize(new Dimension(250,225));
        ventanaEmergente.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        ventanaEmergente.setResizable(false);
        ventanaEmergente.setLocationRelativeTo(null);

        PanelBase panelVentanaEmergente = new PanelBase();
        panelVentanaEmergente.setBackground(new Color(93, 173, 226));
        panelVentanaEmergente.setSize(300,225);
        panelVentanaEmergente.setLayout(new BoxLayout(panelVentanaEmergente,BoxLayout.Y_AXIS));

        Font fuenteLetras = new Font("Linux Libertine",Font.BOLD,12);

        JLabel fotoClima = new JLabel("",SwingConstants.CENTER);
        fotoClima.setBounds(100,10,50,50);
        fotoClima.setIcon(cargarFoto(channel));


        String temperatura = String.format("%.1f",channel.getTemperature());
        String fecha = channel.getRequestedOn();
        String estado = channel.getState();
        String presion = String.format("%.1f",channel.getPressure());
        String humedad = String.format("%.1f",channel.getHumidity());
        String viento = String.format("%.2f",channel.getWind());
        String lluvia = String.format("%.2f",channel.getRain());
        String nieve = String.format("%.1f",channel.getSnow());
        String nubes = String.valueOf(channel.getClouds());

        JLabel etiquetaTemperatura = new JLabel("Temperatura: " + temperatura + "°C ",SwingConstants.CENTER);
        JLabel etiquetaFecha = new JLabel("Fecha: " + fecha,SwingConstants.CENTER);
        JLabel etiquetaEstado = new JLabel("Estado del clima: " + estado,SwingConstants.CENTER);
        JLabel etiquetaPresion = new JLabel("Presión: " + presion + " hPa ",SwingConstants.CENTER);
        JLabel etiquetaHumedad = new JLabel("Humedad: " + humedad + "% ",SwingConstants.CENTER);
        JLabel etiquetaViento = new JLabel("Viento: " + viento + " km/h ",SwingConstants.CENTER);
        JLabel etiquetaLluvia = new JLabel("Probabilidad de lluvia: " + lluvia + "% ",SwingConstants.CENTER);
        JLabel etiquetaNieve = new JLabel("Probabilidad de nevada: " + nieve + "% ",SwingConstants.CENTER);
        JLabel etiquetaNubes = new JLabel("Nubes: " + nubes,SwingConstants.CENTER);


        etiquetaTemperatura.setFont(fuenteLetras);
        etiquetaTemperatura.setForeground(Color.white);

        etiquetaFecha.setFont(fuenteLetras);
        etiquetaFecha.setForeground(Color.white);

        etiquetaEstado.setFont(fuenteLetras);
        etiquetaEstado.setForeground(Color.white);

        etiquetaPresion.setFont(fuenteLetras);
        etiquetaPresion.setForeground(Color.white);

        etiquetaHumedad.setFont(fuenteLetras);
        etiquetaHumedad.setForeground(Color.white);

        etiquetaViento.setFont(fuenteLetras);
        etiquetaViento.setForeground(Color.white);

        etiquetaLluvia.setFont(fuenteLetras);
        etiquetaLluvia.setForeground(Color.white);

        etiquetaNieve.setFont(fuenteLetras);
        etiquetaNieve.setForeground(Color.white);

        etiquetaNubes.setFont(fuenteLetras);
        etiquetaNubes.setForeground(Color.white);


        panelVentanaEmergente.add(fotoClima);
        panelVentanaEmergente.add(etiquetaTemperatura);
        panelVentanaEmergente.add(etiquetaFecha);
        panelVentanaEmergente.add(etiquetaEstado);
        panelVentanaEmergente.add(etiquetaPresion);
        panelVentanaEmergente.add(etiquetaHumedad);
        panelVentanaEmergente.add(etiquetaViento);
        panelVentanaEmergente.add(etiquetaLluvia);
        panelVentanaEmergente.add(etiquetaNieve);
        panelVentanaEmergente.add(etiquetaNubes);

        panelVentanaEmergente.add(Box.createHorizontalStrut(20));
        ventanaEmergente.setIconImage(new ImageIcon("src/main/resources/icono-ventana.png").getImage());
        ventanaEmergente.setTitle("Información del clima");
        ventanaEmergente.add(panelVentanaEmergente);
        ventanaEmergente.setVisible(true);
        ventanaEmergente.pack();
    }
}
