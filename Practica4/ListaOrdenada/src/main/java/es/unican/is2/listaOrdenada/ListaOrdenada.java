package es.unican.is2.listaOrdenada;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que implementa una lista ordenada en base al orden natural Los
 * elementos de la lista deben implementar la interfaz Comparable<E>
 */
public class ListaOrdenada<E extends Comparable<E>> implements IListaOrdenada<E> {

	private ArrayList<E> lista = new ArrayList<E>();

	public E get(int indice) {
		return lista.get(indice);
	}

	public void add(E elemento) {
		int indice = 0;
		if (lista.size() != 0) {
			while (indice < lista.size() && elemento.compareTo(lista.get(indice)) > 0) {
				indice++;
			}
		}
		lista.add(indice, elemento);
	}

	public E remove(int indice) {
		E borrado = lista.remove(indice);
		return borrado;
	}

	public int size() {
		return lista.size();
	}

	public void clear() {
		lista.clear();
	}
}