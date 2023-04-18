package es.unican.is2.listaOrdenada;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

//import es.unican.is2.listaOrdenada.IListaOrdenada;
//import es.unican.is2.listaOrdenada.ListaOrdenada;

public class ListaOrdenadaTest {
	IListaOrdenada<Integer> lista;

	@Test
	public void testGet() {
		// Casos validos
		lista  = new ListaOrdenada<>();
		lista.add(1);
		try {
			assertEquals(1, lista.get(0));
		} catch (IndexOutOfBoundsException e) {
			fail("Caso 1: No deberia lanzarse una excepcion");
		}
		lista.add(5);
		lista.add(3);
		lista.add(2);
		lista.add(4);
		try {
			assertEquals(2, lista.get(1));
		} catch (IndexOutOfBoundsException e) {
			fail("Caso 2: No deberia lanzarse una excepcion");
		}
		try {
			assertEquals(5, lista.get(4));
		} catch (IndexOutOfBoundsException e) {
			fail("Caso 3: No deberia lanzarse una excepcion");
		}

		// Casos no validos
		lista  = new ListaOrdenada<>();
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(0));
		for (int i = 1; i <= 5; i++) {
			lista.add(i);
		}
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(5));
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(20));

	}

	@Test
	public void testAdd() {
		// Casos validos
		lista = new ListaOrdenada<>();
		lista.add(3);
		try {
			assertEquals(3, lista.get(0), "No se anhade a la lista ");
			assertEquals(1, lista.size());
		} catch (IndexOutOfBoundsException e) {
			fail("Caso 1: No deberia lanzarse una excepcion");
		}
		lista.add(1);
		try {
			assertEquals(1, lista.get(0), "No se anhade en la posicion correcta ");
			assertEquals(3, lista.get(1), "No se anhade en la posicion correcta ");
			assertEquals(2, lista.size());
		} catch (IndexOutOfBoundsException e) {
			fail("Caso 2: No deberia lanzarse una excepcion");
		}
		lista.add(4);
		lista.add(5);
		lista.add(2);
		try {
			assertEquals(5, lista.size());
			for (int i = 1; i <= 5; i++) {
				assertEquals(i, lista.get(i-1), i + " no se anhade en la posicion correcta ");
			}
		} catch (IndexOutOfBoundsException e) {
			fail("Caso 3: No deberia lanzarse una excepcion");
		}

	}

	@Test
	public void testRemove() {
		// Casos validos
		lista = new ListaOrdenada<>();
		lista.add(1);
		try {
			assertEquals(1, lista.remove(0));
			assertEquals(0, lista.size());
		} catch (IndexOutOfBoundsException e) {
			fail("Caso 1: No deberia lanzarse una excepcion");
		}
		lista.add(1);
		lista.add(4);
		lista.add(5);
		lista.add(3);
		lista.add(2);
		try {
			assertEquals(2, lista.remove(1));
			assertEquals(4, lista.size());
			assertEquals(1, lista.get(0));
			assertEquals(3, lista.get(1));
			assertEquals(4, lista.get(2));
			assertEquals(5, lista.get(3));

		} catch (IndexOutOfBoundsException e) {
			fail("Caso 2: No deberia lanzarse una excepcion");
		}
		try {
			assertEquals(5, lista.remove(3));
			assertEquals(3, lista.size());
			assertEquals(1, lista.get(0));
			assertEquals(3, lista.get(1));
			assertEquals(4, lista.get(2));
		} catch (IndexOutOfBoundsException e) {
			fail("Caso 3: No deberia lanzarse una excepcion");
		}

		// Casos no validos
		lista = new ListaOrdenada<>();
		assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(0));
		for (int i = 1; i <= 5; i++) {
			lista.add(i);
		}
		assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(5));
		assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(20));


	}

	@Test
	public void testSize() {
		// Casos validos
		lista = new ListaOrdenada<>();
		assertEquals(0, lista.size());
		lista.add(3);
		assertEquals(1, lista.size());
		lista.add(4);
		lista.add(5);
		assertEquals(3, lista.size());
	}

	@Test
	public void testClear() {
		// Casos validos
		lista = new ListaOrdenada<>();
		lista.clear();
		assertEquals(0, lista.size());
		lista.add(3);
		lista.clear();
		assertEquals(0, lista.size());
		lista.add(3);
		lista.add(4);
		lista.add(5);
		lista.clear();
		assertEquals(0, lista.size());
	}
}
