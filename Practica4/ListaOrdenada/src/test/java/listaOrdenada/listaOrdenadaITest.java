package listaOrdenada;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


import es.unican.is2.listaOrdenada.IListaOrdenada;
import es.unican.is2.listaOrdenada.ListaOrdenada;

public class listaOrdenadaITest {
	IListaOrdenada<Integer> lista = new ListaOrdenada<>();
	
	@Test
	public void testGet() {
		
	}
	
	@Test
	public void testAdd() {
		lista.add(1);
		assertEquals(1, lista.get(0));
		lista.add(3);
		assertEquals(1, lista.get(0));
		assertEquals(3, lista.get(1));
		lista.add(4);
		lista.add(5);
		lista.add(2);
		assertEquals(1, lista.get(0));
		assertEquals(2, lista.get(1));
		assertEquals(3, lista.get(2));
		assertEquals(4, lista.get(3));
		assertEquals(5, lista.get(4));
		
		
	}
	
	@Test
	public void testRemove() {
		
	}
	
	@Test
	public void testSize() {
		
	}
	
	@Test
	public void testClear() {
		
	}
}
