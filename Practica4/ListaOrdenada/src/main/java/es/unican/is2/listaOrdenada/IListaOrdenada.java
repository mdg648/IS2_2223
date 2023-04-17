package es.unican.is2.listaOrdenada;

/**
 * TDA ListaOrdenada 
 * Estructura de datos que almacena los datos ordenados de acuerdo
 * a su orden natural
 * 
 * @param <E> tipo de los elementos almacenados en la lista
 */
public interface IListaOrdenada<E> {

    /**
	 * Retorna el elemento que ocupa la posicion indicada
	 * @param indice Indice del elemento al que se accede
	 * @return Elemento en la posicion indice
	 * @throws IndexOutOfBoundsException si el indice es incorrecto
	 */
    public E get(int indice) throws IndexOutOfBoundsException;
    
    
    /**
     * Añade un elemento a la posición
     * que le corresponda por orden natural
     * @param elemento Elemento a añadir
     */
    public void add(E elemento);
    
    /**
     * Elimina el elemento que ocupa la posición indicada
     * @param indice Indice del elemento que se quiere eliminar
     * @return El elemento eliminado
     * @throws IndexOutOfBoundsException si el indice es incorrecto
     */
    public E remove(int indice) throws IndexOutOfBoundsException;
    
    /**
     * Retorna el tamaño de la lista
     */
    public int size();
    
    /**
     * Elimina todos los elementos de la lista
     */
     public void clear();

}
