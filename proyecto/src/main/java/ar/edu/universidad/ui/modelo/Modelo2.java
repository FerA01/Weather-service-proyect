package ar.edu.unnoba.ui.modelo;
import ar.edu.unnoba.model.Channel;
import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Fernando Andana
 * Clase usada para la logica del JList
 */
public class Modelo2 extends AbstractListModel implements ListModel{
    /**
     * Atributo List de tipo Channel
     */
    private List<Channel> elementos = new ArrayList<>();

    /**
     * Atributo List de tipo Channel ordenados por fecha
     */
    private List<Channel> elementosOrdenadosFecha;

    /**
     * Atributo List de tipo Channel ordenados por temperatura
     */
    private List<Channel> elementosOrdenadosTemperatura;

    /**
     * Constructor
     */
    public Modelo2(){}
    public Modelo2(List<Channel> elementos){getElementos().addAll(elementos);}

    /**
     * Getter de la lista elementos.
     * @return List
     */
    public List<Channel> getElementos() { return elementos; }

    /**
     * Setter de la lista elementos.
     * @param elementos List
     */
    public void setElementos(List<Channel> elementos) { this.elementos = elementos; }

    /**
     * Getter de la lista elementosOrdenadosFecha.
     * @return List
     */
    public List<Channel> getElementosOrdenadosFecha() { return elementosOrdenadosFecha; }

    /**
     * Setter de la lista elementos.
     * @param elementosOrdenadosFecha List
     */
    public void setElementosOrdenadosFecha(List<Channel> elementosOrdenadosFecha) { this.elementosOrdenadosFecha = elementosOrdenadosFecha; }

    /**
     * Getter de la lista elementosOrdenadosTemperatura.
     * @return List
     */
    public List<Channel> getElementosOrdenadosTemperatura() { return elementosOrdenadosTemperatura; }

    /**
     * Setter de la lista elementos.
     * @param elementosOrdenadosTemperatura List
     */
    public void setElementosOrdenadosTemperatura(List<Channel> elementosOrdenadosTemperatura) { this.elementosOrdenadosTemperatura = elementosOrdenadosTemperatura; }

    /**
     * Metodo que devuelve la cantidad de elementos que tiene la lista elementos.
     * @return int
     */
    @Override
    public int getSize() { return getElementos().size(); }

    /**
     * Metodo que devuelve un objeto Channel según el indice.
     * @param index Objeto de tipo int
     * @return Channel
     */
    @Override
    public Channel getElementAt(int index) { return getElementos().get(index); }

    /**
     * Metodo que agrega el objeto pasado por parametro a la lista elementos.
     * @param chanel Objeto de tipo Channel.
     */
    public void agregar(Channel chanel){
        getElementos().add(chanel);
        this.fireIntervalAdded(this,getSize(),getSize()+1);
    }

    /**
     * Metodo que elimina el objeto pasado por parametro a la lista elementos.
     * @param index Objeto de tipo int.
     */
    public void eliminar(int index){
        getElementos().remove(index);
        this.fireIntervalRemoved(this,getSize(),getSize()-1);
    }

    /**
     * Metodo que vaica la lista elementos.
     */
    public void eliminarTodo(){
        getElementos().removeAll(getElementos());
        this.fireContentsChanged(this,0,getSize());
    }

    /**
     * Metodo que ordena la lista elementos por temperatura y se lo asigna a la lista elementosOrdenadosTemperatura
     */
    public void ordenarTemperatura(){
        ArrayList<Channel> ordenado = (ArrayList<Channel>) getElementos().stream()
                                        .sorted(Comparator.comparing(Channel::getTemperature).reversed())
                                        .collect(Collectors.toList()
                                        );
        setElementosOrdenadosTemperatura(ordenado);
        setElementos(ordenado);
        this.fireContentsChanged(this,0,getSize());
    }

    /**
     * Metodo que ordena la lista elementos por temperatura y se lo asigna a la lista elementosOrdenadosFecha
     */
    public void ordenarFecha(){
        ArrayList<Channel> ordenado = (ArrayList<Channel>) getElementos().stream()
                                    .sorted(Comparator.comparing(Channel::getRequestedOn).reversed())
                                    .collect(Collectors.toList()
                                    );
        setElementosOrdenadosFecha(ordenado);
        setElementos(ordenado);
        this.fireContentsChanged(this,0,getSize());
    }
}
