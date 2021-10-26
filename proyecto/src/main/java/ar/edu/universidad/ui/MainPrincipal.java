package ar.edu.unnoba.ui;
import ar.edu.unnoba.model.Channel;
import ar.edu.unnoba.model.City;
import ar.edu.unnoba.service.WeatherService;
import ar.edu.unnoba.ui.controlador.Controlador;
import ar.edu.unnoba.ui.modelo.EstacionClima;
import ar.edu.unnoba.ui.modelo.Etiqueta;
import ar.edu.unnoba.ui.modelo.ListaHistorialClima;
import ar.edu.unnoba.ui.modelo.Modelo2;
import ar.edu.unnoba.ui.vista.Panel;
import ar.edu.unnoba.ui.vista.PanelBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;


/**
 * Fernando Andana
 * Clase principal que para ejecutar el programa
 * Esta clase ademas implementa el observer.
 */
public class MainPrincipal extends Panel implements Observer {
    private final String CANTIDADMODELO = "Cantidad modelo: ";
    private final String CANTIDADESTACION = "Cantidad estación: ";
    /**
     * Atributo necesario para el weather service
     */
    private static final String _DEGREE = "\u00b0";

    /**
     * Atributo necesario para el weather service
     */
    private static final long serialVersionUID = 1L;

    /**
     * Atributo de tipo WeatherService
     */
    private WeatherService service = new WeatherService(City.CABA, 30 );

    /**
     * Atributo de tipo EstacionClima2
     */
    private EstacionClima estacionClima= new EstacionClima();

    /**
     *Atributo de tipo Controlador
     */
    private Controlador controlador = new Controlador();

    /**
     *Atributo de tipo Etiqueta
     */
    private Etiqueta etiqueta = new Etiqueta();

    /**
     *Atributo de tipo Modelo2
     */
    private Modelo2 modelo2 = new Modelo2();

    /**
     * Atributo de tipo ListaHistorialClima
     */
    private ListaHistorialClima listaHistorialClima = new ListaHistorialClima();

    /**
     * Atributo de tipo JList
     */
    private JList historial = new JList();

    /**
     * Atributo de tipo ScrollPane
     */
    private JScrollPane scrollPane = new JScrollPane();

    /**
     * Atributo de tipo JFrame
     */
    private final JFrame ventanaEmergente = new JFrame();

    //Paneles
    /**
     * Atributo de tipo Panel
     */
    private Panel panel = new Panel();

    /**
     * Atributo de tipo PanelBase
     */
    private PanelBase panelBotones;

    /**
     * Atributo de tipo PanelBase
     */
    private PanelBase panelHistorial = new PanelBase();

    /**
     * Atributo de tipo PanelBase
     */
    private PanelBase panelClimaActual;

    //Botones
    /**
     * Atributo de tipo JButton.
     */
    private JButton botonTemperatura = new JButton();

    /**
     * Atributo de tipo JButton.
     */
    private JButton botonFecha = new JButton();

    /**
     * Atributo de tipo JButton.
     */
    private JButton botonLimpiar = new JButton();

    //Etiquetas
    private JLabel imagenClimaActual = new JLabel();
    private JLabel informacionClimaActual = new JLabel();
    private JLabel imagenFondo = new JLabel();

    /**
     * Constructor donde inicializo todos los objetos
     */
    public MainPrincipal(){
        setLayout(null);
        //Iniciar Servicio
        getService().addObserver(this);
        getService().start();

        //Iniciar Paneles
        setPanelBotones(getPanel().configurarPanelBotoness());
        getPanelBotones().setBounds(5,140, 175,365);
        setPanelClimaActual(getPanel().configurarPanelClimaActual());
        getPanelClimaActual().setBounds(5,0,640,140);
        setPanelHistorial(getPanel().configurarPanelHistorial());
        add(getPanelBotones());
        add(getPanelClimaActual());
        add(getPanelHistorial());

        setHistorial(getListaHistorialClima().
                     configurarHistorial(getControlador(),
                                        (PanelBase) getPanelHistorial(),
                                        getModelo2()
                     )
        );
        setScrollPane(getListaHistorialClima().configurarScrollPane((PanelBase)getPanelHistorial(), getHistorial()));


        //Asignar Etiquetas
        setPanelBotones(getEtiqueta().iniciarEtiquetasPanelBotones((PanelBase)  getPanelBotones()));
        setPanelClimaActual(getEtiqueta().iniciarEtiquetasPanelClimaActual((PanelBase) getPanelClimaActual(),
                getImagenClimaActual(),
                getInformacionClimaActual(),
                getImagenFondo()
                )
        );
        getPanelClimaActual().add(getImagenFondo());

        //Iniciar Botones
        getControlador().iniciarBotones((PanelBase) getPanelBotones(),
                                                getBotonTemperatura(),
                                                getBotonFecha(),
                                                getBotonLimpiar()
        );

        //Agregar acciones a los botones
        getBotonFecha().addActionListener(this::accionBotonOrdenerPorFecha);
        getBotonLimpiar().addActionListener(this::accionBotonLimpiarHistorial);
        getBotonTemperatura().addActionListener(this::accionBotonOrdenarPorTemperatura);

        getListaHistorialClima().asignarEventoHistorial(getHistorial(),
                                                        getControlador(),
                                                        getModelo2()
        );
        setBackground(new Color(21, 67, 96));
    }

    public WeatherService getService() { return service; }
    public void setService(WeatherService service) { this.service = service; }
    public EstacionClima2 getEstacionClima() { return estacionClima; }
    public void setEstacionClima(EstacionClima2 estacionClima) { this.estacionClima = estacionClima; }
    public Modelo2 getModelo2() { return modelo2; }
    public void setModelo2(Modelo2 modelo2) { this.modelo2 = modelo2; }
    public Etiqueta getEtiqueta() { return etiqueta; }
    public void setEtiqueta(Etiqueta etiqueta) { this.etiqueta = etiqueta; }
    public ListaHistorialClima getListaHistorialClima() { return listaHistorialClima; }
    public void setListaHistorialClima(ListaHistorialClima listaHistorialClima) { this.listaHistorialClima = listaHistorialClima; }
    public Controlador getControlador() { return controlador; }
    public void setControlador(Controlador controlador) { this.controlador = controlador; }
    public JList getHistorial() { return historial; }
    public void setHistorial(JList historial) { this.historial = historial; }
    public JScrollPane getScrollPane() { return scrollPane; }
    public void setScrollPane(JScrollPane scrollPane) { this.scrollPane = scrollPane; }
    public Panel getPanel() { return panel; }
    public void setPanel(Panel panel) { this.panel = panel; }
    public JPanel getPanelBotones() { return panelBotones; }
    public void setPanelBotones(PanelBase panelBotones) { this.panelBotones = panelBotones; }
    public JPanel getPanelHistorial() { return panelHistorial; }
    public void setPanelHistorial(PanelBase panelHistorial) { this.panelHistorial = panelHistorial; }
    public JPanel getPanelClimaActual() { return panelClimaActual; }
    public void setPanelClimaActual(PanelBase panelClimaActual) { this.panelClimaActual = panelClimaActual; }
    public JButton getBotonTemperatura() { return botonTemperatura; }
    public void setBotonTemperatura(JButton botonTemperatura) { this.botonTemperatura = botonTemperatura; }
    public JButton getBotonFecha() { return botonFecha; }
    public void setBotonFecha(JButton botonFecha) { this.botonFecha = botonFecha; }
    public JButton getBotonLimpiar() { return botonLimpiar; }
    public void setBotonLimpiar(JButton botonLimpiar) { this.botonLimpiar = botonLimpiar; }
    public JLabel getImagenClimaActual() { return imagenClimaActual; }
    public void setImagenClimaActual(JLabel imagenClimaActual) { this.imagenClimaActual = imagenClimaActual; }
    public JLabel getInformacionClimaActual() { return informacionClimaActual; }
    public void setInformacionClimaActual(JLabel informacionClimaActual) { this.informacionClimaActual = informacionClimaActual; }
    public JLabel getImagenFondo() { return imagenFondo; }
    public void setImagenFondo(JLabel imagenFondo) { this.imagenFondo = imagenFondo; }

    //Acciones de botones

    /**
     * Metodo para asignar evento a un botón.
     * @param action ActionEvent
     */
    public void accionBotonOrdenerPorFecha(ActionEvent action){ getControlador().accionOrdenarListaFecha(action,getModelo2()); }

    /**
     * Metodo para asignar evento a un botón.
     * @param actionEvent ActionEvent
     */
    public void accionBotonOrdenarPorTemperatura(ActionEvent actionEvent){
        getControlador().accionOrdenarListaTemperatura(actionEvent, getModelo2());
    }

    /**
     * Metodo para asignar evento a un botón.
     * @param actionEvent ActionEvent
     */
    public void accionBotonLimpiarHistorial(ActionEvent actionEvent){
        getControlador().accionLimpiarHistorial(actionEvent, getModelo2(), getEstacionClima(), getHistorial()
                                                ,getImagenClimaActual()
                                                ,getInformacionClimaActual()
        );
    }


    /**
     * Metodo para obtener los cambios hechos por el weather service
     * @param servicio Objeto de tipo WeatherService
     * @param c Objeto de tipo Channel
     */
    @Override
    public void update(Observable servicio, Object c) {
        Channel canal = (Channel) c;
        getControlador().agregarClima(canal, getEstacionClima(), getModelo2());
        getControlador().cambiarFotoClima(getImagenClimaActual(), (ImageIcon) getControlador().cargarFoto(canal));
        getControlador().cambiarInformacionClimaActual(getInformacionClimaActual(), getControlador().obtenerDatos(canal));
        getControlador().cambiarFondo(getImagenFondo(),getInformacionClimaActual(),canal.getDescription());

        System.out.println(canal);
        System.out.println(CANTIDADMODELO + getModelo2().getSize());
        System.out.println(CANTIDADESTACION + getEstacionClima().tamanio());
    }

    /**
     * Metodo que cierra el servicio del weather service
     */
    public void close(){
        getService().stop();
        getService().deleteObserver(this);
    }

    /**
     * Metodo principal que crea una ventana y agrega todos los paneles a si mismo, y funciona hasta que se cierra.
     * @param args Objeto de tipo String[]
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
                    try {
                        JFrame main = new JFrame("Weather App");
                        MainPrincipal ui = new MainPrincipal();

                        main.setIconImage(new ImageIcon("src/main/resources/icono-ventana.png").getImage());
                        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        main.setPreferredSize(new Dimension(657, 550)); // Le damos el tamaño a la ventana

                        main.getContentPane().add(ui); // Le agregamos el JPanel con nuestros componentes
                        main.pack();
                        main.setResizable(false);
                        main.setLocationRelativeTo(null); // Le decimos que no sea relativa a nada, eso hace que quede centrada.

                        main.addWindowListener(new WindowAdapter() {    //Termina el servicio si cierro la ventana
                            public void windowClosing(WindowEvent e) {
                                ui.close();
                            }
                        });
                        main.setVisible(true); // Le decimos que se muestre
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
    }
}
