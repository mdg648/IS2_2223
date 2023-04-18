package es.unican.is2.segurosCommon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class SeguroTest {
	Seguro seguroValido1;
	Seguro seguroValido2;
	Seguro seguroValido3;
	Seguro seguroValido4;
	Seguro seguroValido5;
	Seguro seguroValido6;
	Seguro seguroValido7;
	Seguro seguroValido8;

	
	@Test
	public void testConstructor() {
		String matricula;
		Cobertura cobertura;
		double potencia;
		
		// Casos validos
		try {
			seguroValido1 = new Seguro("1234ABC", Cobertura.TODORIESGO, 90, LocalDate.now().minusDays(364));
			matricula = seguroValido1.getMatricula();
			cobertura = seguroValido1.getCobertura();
			potencia = seguroValido1.getPotencia();
			assertEquals("1234ABC", matricula, "Matricula definida incorrectamente");
			assertEquals(Cobertura.TODORIESGO, cobertura, "Cobertura definida incorrectamente");
			assertEquals(90, potencia, "Potencia definida incorrectamente");
		} catch(DatoNoValido e) {
			fail("Caso 1: No deberia lanzar la excepcion");
		}
		
		try {
			seguroValido2 = new Seguro("1234ABC", Cobertura.TERCEROSLUNAS, 100, LocalDate.now().minusDays(150));
			matricula = seguroValido2.getMatricula();
			cobertura = seguroValido2.getCobertura();
			potencia = seguroValido2.getPotencia();
			assertEquals("1234ABC", matricula, "Matricula definida incorrectamente");
			assertEquals(Cobertura.TERCEROSLUNAS, cobertura, "Cobertura definida incorrectamente");
			assertEquals(100, potencia, "Potencia definida incorrectamente");
		} catch(DatoNoValido e) {
			fail("Caso 2: No deberia lanzar la excepcion");
		}

		try {
			seguroValido3 = new Seguro("1234ABC", Cobertura.TERCEROS, 110, LocalDate.now());
			matricula = seguroValido3.getMatricula();
			cobertura = seguroValido3.getCobertura();
			potencia = seguroValido3.getPotencia();
			assertEquals("1234ABC", matricula, "Matricula definida incorrectamente");
			assertEquals(Cobertura.TERCEROS, cobertura, "Cobertura definida incorrectamente");
			assertEquals(110, potencia, "Potencia definida incorrectamente");
		} catch(DatoNoValido e) {
			fail("Caso 3: No deberia lanzar la excepcion");
		}

		// Casos no validos

		DatoNoValido thrown;

		thrown = assertThrows(DatoNoValido.class,
				() -> new Seguro("1234ABC", Cobertura.TODORIESGO, 0, LocalDate.now()),
				"Deberia lanzar la exepcion por tener potencia cero");
		assertEquals("La potencia no puede ser cero", thrown.getMessage());

		thrown = assertThrows(DatoNoValido.class,
				() -> new Seguro("1234ABC", Cobertura.TERCEROSLUNAS, -100, LocalDate.now()),
				"Deberia lanzar la exepcion por tener potencia negativa");
		assertEquals("La potencia no puede ser negativa", thrown.getMessage());

		thrown = assertThrows(DatoNoValido.class,
				() -> new Seguro("1234ABC", Cobertura.TERCEROS, 90, null),
				"Deberia lanzar la exepcion por tener fecha nula");
		assertEquals("La fecha de contratacion no puede ser null", thrown.getMessage());

		thrown = assertThrows(DatoNoValido.class,
				() -> new Seguro("1234ABC", Cobertura.TODORIESGO, 100, LocalDate.now().plusDays(1)),
				"Deberia lanzar la exepcion por haber una fecha de contratacion superior al dia actual");
		assertEquals("La fecha de contratacion no puede ser superior al dia actual", thrown.getMessage());

		thrown = assertThrows(DatoNoValido.class,
				() -> new Seguro("1234ABC", Cobertura.TERCEROSLUNAS, 110, LocalDate.now().plusDays(365)),
				"Deberia lanzar la exepcion por haber una fecha de contratacion superior al dia actual");
		assertEquals("La fecha de contratacion no puede ser superior al dia actual", thrown.getMessage());

		thrown = assertThrows(DatoNoValido.class,
				() -> new Seguro(null, Cobertura.TODORIESGO, 100, LocalDate.now()),
				"Deberia lanzar la exepcion porque la matricula vale null");
		assertEquals("La matricula no puede ser null", thrown.getMessage());


	}

	@Test
	public void testPrecio() {
		double precio = 0.0;
		// Casos validos 
		try {
			seguroValido1 = new Seguro("1234ABC", Cobertura.TODORIESGO, 90, LocalDate.now().minusDays(364));
			seguroValido2 = new Seguro("1234ABC", Cobertura.TERCEROSLUNAS, 100, LocalDate.now().minusDays(150));
			seguroValido3 = new Seguro("1234ABC", Cobertura.TERCEROS, 110, LocalDate.now());
			seguroValido4 = new Seguro("1234ABC", Cobertura.TODORIESGO, 111, LocalDate.now().minusDays(729));
			seguroValido5 = new Seguro("1234ABC", Cobertura.TERCEROSLUNAS, 300, LocalDate.now().minusDays(500));
			seguroValido6 = new Seguro("1234ABC", Cobertura.TERCEROS, 89, LocalDate.now().minusDays(365));
			seguroValido7 = new Seguro("1234ABC", Cobertura.TODORIESGO, 1, LocalDate.now().minusDays(730));
			seguroValido8 = new Seguro("1234ABC", Cobertura.TERCEROSLUNAS, 90, LocalDate.now().minusDays(1000));
		} catch (DatoNoValido e1) {
			// Auto-generated catch block
			e1.printStackTrace();
		}

		precio = seguroValido1.precio();
		assertEquals(840, precio, "Obtenido: " + precio);

		precio = seguroValido2.precio();
		assertEquals(504, precio, "Obtenido: " + precio);

		precio = seguroValido3.precio();
		assertEquals(336, precio, "Obtenido: " + precio);

		precio = seguroValido4.precio();
		assertEquals(1080, precio, "Obtenido: " + precio);

		precio = seguroValido5.precio();
		assertEquals(648, precio, "Obtenido: " + precio);

		precio = seguroValido6.precio();
		assertEquals(360, precio, "Obtenido: " + precio);

		precio = seguroValido7.precio();
		assertEquals(1000, precio, "Obtenido: " + precio);

		precio = seguroValido8.precio();
		assertEquals(630, precio, "Obtenido: " + precio);
		
	}
}
