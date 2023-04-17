package es.unican.is2.segurosCommon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ClienteITest {
	Cliente clienteValido1;
	Cliente clienteValido2;
	Cliente clienteValido3;
	
	Cliente clienteNoValido1;
	
	Seguro seguro1;
	Seguro seguro2;
	Seguro seguro3;
	List<Seguro> listaSeguros1 = new LinkedList<Seguro>();
	List<Seguro> listaSeguros2 = new LinkedList<Seguro>();
	
	@Test
	public void testTotalSeguro() {
		double totalSeguros = 0.0;
		// casos validos
		clienteValido1 = new Cliente("Juan", "12345678A", true);
		clienteValido2 = new Cliente("Isaac", "21345678A", false);
		clienteValido3 = new Cliente("Sandra", "1348778A", true);
		try {
			seguro1 = new Seguro("1234ABC", Cobertura.TODORIESGO, 90, LocalDate.now().minusDays(364));
			seguro2 = new Seguro("1234ABC", Cobertura.TERCEROSLUNAS, 100, LocalDate.now().minusDays(150));
			seguro3 = new Seguro("1234ABC", Cobertura.TERCEROS, 110, LocalDate.now());
		} catch (DatoNoValido e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		listaSeguros1.add(seguro1);
		clienteValido2.setSeguros(listaSeguros1);
		listaSeguros2.add(seguro1);
		listaSeguros2.add(seguro2);
		listaSeguros2.add(seguro3);
		clienteValido3.setSeguros(listaSeguros2);

		totalSeguros = clienteValido1.totalSeguros();
		assertEquals(0, totalSeguros, "Obtenido: " + totalSeguros);

		totalSeguros = clienteValido2.totalSeguros();
		assertEquals(840, totalSeguros, "Obtenido: " + totalSeguros);

		totalSeguros = clienteValido3.totalSeguros();
		assertEquals(1260, totalSeguros, "Obtenido: " + totalSeguros);


		// casos no validos
		clienteNoValido1 = new Cliente("Sebastan", "32145678", true);
		clienteNoValido1.setSeguros(null);
		assertThrows(NullPointerException.class, () -> clienteNoValido1.totalSeguros());

	}
}
