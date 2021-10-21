package ar.edu.unnoba.ui.modelo;
import ar.edu.unnoba.model.Channel;
import ar.edu.unnoba.model.City;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

/**
 * Fernando Andana
 * Clase estación del clima que sera usado para ser "observado".
 */
public class EstacionClima2 extends Observable {
    /**
     * Atributo de tipo List que contendra los climas.
     */
    private List<Channel> registroClimas = new ArrayList<Channel>();
    /**
     * Atributo de tipo Channel contendra el clima actual.
     */
    private Channel climaActual;
    /**
     * Atributo de tipo City que contendra la ciudad donde se toma el clima.
     */
    private City city;

    /**
     * Constructor vacio
     */
    public EstacionClima2(){ setRegistroClimas(new ArrayList<>()); }
    public EstacionClima2(List<Channel> registroClimas){
        setRegistroClimas(registroClimas);
        setRegistroClimas(new ArrayList<>());
    }
    public EstacionClima2(City city){ setRegistroClimas(new ArrayList<>()); setCity(city); }

    /**
     * Getter del atributo registroClimas.
     * @return List
     */
    public List<Channel> getRegistroClimas() { return registroClimas; }

    /**
     * Setter del atributo registro Climas.
     * @param registroClimas Objeto de tipo List
     */
    public void setRegistroClimas(List<Channel> registroClimas) { this.registroClimas = registroClimas; }

    /**
     * Getter del atributo climaActual.
     * @return Channel
     */
    public Channel getClimaActual() { return climaActual; }

    /**
     * Setter del atributo registro Climas.
     * @param climaActual Objeto de tipo Channel.
     */
    public void setClimaActual(Channel climaActual) { this.climaActual = climaActual; }

    /**
     * Getter del atributo city.
     * @return City
     */
    public City getCity() { return city; }

    /**
     * Setter del atributo city.
     * @param city Objeto de tipo City
     */
    private void setCity(City city) { this.city = city; }

    //Metodos.

    /**
     * Metodo que agregar el clima pasado por parametro al atributo climaActual y a la lista que contiene el historial
     * de clima. Ademas notifica que hizo un cambio a sus observadores.
     * @param channel Objeto de tipo Channel.
     */
    public void agregar(Channel channel){
        setClimaActual(channel);
        getRegistroClimas().add(channel);
        notificarCambios();
    }
    /**
     * Metodo que elimina el clima pasado por parametro de la lista.
     * Ademas notifica que hizo un cambio a sus observadores.
     * @param aChannel Objeto de tipo Channel.
     */
    public void eliminar(Channel aChannel){
        if (aChannel == getClimaActual()){ setClimaActual(null); }
        getRegistroClimas().removeIf(channel -> channel.equals(aChannel));
        notificarCambios();
    }

    /**
     * Metodo que vacia la lista y notifica su cambio a sus observadores.
     */
    public void eliminarTodo(){ getRegistroClimas().clear(); notificarCambios();}

    /**
     * Metodo que ordena la lista por fecha y notifica su cambio a sus observadores.
     */
    public void ordenarPorFecha(){
        setRegistroClimas(getRegistroClimas().stream()
                .sorted(Comparator.comparing(Channel::getRequestedOn).reversed())
                .collect(Collectors.toList()));
        notificarCambios();
    }
    /**
     * Metodo que ordena la lista por temperatura y notifica su cambio a sus observadores.
     */
    public void ordenarPorTemperatura(){
        setRegistroClimas(getRegistroClimas().stream()
                .sorted(Comparator.comparing(Channel::getTemperature).reversed())
                .collect(Collectors.toList()));
        notificarCambios();
    }

    /**
     * Metodo que notifica a observadores de cambios.
     */
    private void notificarCambios(){
        setChanged();
        notifyObservers();
    }
}
