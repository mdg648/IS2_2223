package es.unican.is2.segurosCommon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class SeguroITest {
	Seguro seguroValido1;
	Seguro seguroValido2;
	Seguro seguroValido3;
	Seguro seguroValido4;
	Seguro seguroValido5;
	Seguro seguroValido6;
	Seguro seguroValido7;
	Seguro seguroValido8;
	
	Seguro seguroNoValido1;
	Seguro seguroNoValido2;
	Seguro seguroNoValido3;
	Seguro seguroNoValido4;
	Seguro seguroNoValido5;
	
	@Test
	public void testPrecio() {
		double precio = 0.0;
		// Casos validos 
		seguroValido1 = new Seguro("1234ABC", Cobertura.TODORIESGO, 90, LocalDate.now().minusDays(364));
		seguroValido2 = new Seguro("1234ABC", Cobertura.TERCEROSLUNAS, 100, LocalDate.now().minusDays(150));
		seguroValido3 = new Seguro("1234ABC", Cobertura.TERCEROS, 110, LocalDate.now());
		seguroValido4 = new Seguro("1234ABC", Cobertura.TODORIESGO, 111, LocalDate.now().minusDays(729));
		seguroValido5 = new Seguro("1234ABC", Cobertura.TERCEROSLUNAS, 300, LocalDate.now().minusDays(500));
		seguroValido6 = new Seguro("1234ABC", Cobertura.TERCEROS, 89, LocalDate.now().minusDays(365));
		seguroValido7 = new Seguro("1234ABC", Cobertura.TODORIESGO, 1, LocalDate.now().minusDays(730));
		seguroValido8 = new Seguro("1234ABC", Cobertura.TERCEROSLUNAS, 90, LocalDate.now().minusDays(1000));

		try {
			precio = seguroValido1.precio();
			assertEquals(840, precio, "Obtenido: " + precio);
		} catch(DatoNoValido e) {
			fail("Caso 1: No deberia lanzar la excepcion");
		}

		try {
			precio = seguroValido2.precio();
			assertEquals(504, precio, "Obtenido: " + precio);
		} catch(DatoNoValido e) {
			fail("Caso 2: No deberia lanzar la excepcion");
		}
		
		try {
			precio = seguroValido3.precio();
			assertEquals(336, precio, "Obtenido: " + precio);
		} catch(DatoNoValido e) {
			fail("Caso 3: No deberia lanzar la excepcion");
		}
		
		try {
			precio = seguroValido4.precio();
			assertEquals(1080, precio, "Obtenido: " + precio);
		} catch(DatoNoValido e) {
			fail("Caso 4: No deberia lanzar la excepcion");
		}
		
		try {
			precio = seguroValido5.precio();
			assertEquals(648, precio, "Obtenido: " + precio);
		} catch(DatoNoValido e) {
			fail("Caso 5: No deberia lanzar la excepcion");
		}
		
		try {
			precio = seguroValido6.precio();
			assertEquals(360, precio, "Obtenido: " + precio);
		} catch(DatoNoValido e) {
			fail("Caso 6: No deberia lanzar la excepcion");
		}
		
		try {
			precio = seguroValido7.precio();
			assertEquals(1000, precio, "Obtenido: " + precio);
		} catch(DatoNoValido e) {
			fail("Caso 7: No deberia lanzar la excepcion");
		}
		
		try {
			precio = seguroValido8.precio();
			assertEquals(630, precio, "Obtenido: " + precio);
		} catch(DatoNoValido e) {
			fail("Caso 8: No deberia lanzar la excepcion");
		}
		
		// Casos no validos
		seguroNoValido1 = new Seguro("1234ABC", Cobertura.TODORIESGO, 0, LocalDate.now());
		seguroNoValido2 = new Seguro("1234ABC", Cobertura.TERCEROSLUNAS, -100, LocalDate.now());
		seguroNoValido3 = new Seguro("1234ABC", Cobertura.TERCEROS, 90, null);
		seguroNoValido4 = new Seguro("1234ABC", Cobertura.TODORIESGO, 100, LocalDate.now().plusDays(1));
		seguroNoValido5 = new Seguro("1234ABC", Cobertura.TERCEROSLUNAS, 110, LocalDate.now().plusDays(365));
		
		DatoNoValido thrown;
		
		thrown = assertThrows(DatoNoValido.class,
				() -> seguroNoValido1.precio(),
				"Deberia lanzar la exepcion por tener potencia cero");
		assertEquals("La potencia no puede ser cero", thrown.getMessage());

		thrown = assertThrows(DatoNoValido.class,
				() -> seguroNoValido2.precio(),
				"Deberia lanzar la exepcion por tener potencia negativa");
		assertEquals("La potencia no puede ser negativa", thrown.getMessage());
		
		assertThrows(NullPointerException.class, () -> seguroNoValido3.precio());

		thrown = assertThrows(DatoNoValido.class,
				() -> seguroNoValido4.precio(),
				"Deberia lanzar la exepcion por haber una fecha de contratacion superior al dia actual");
		assertEquals("La fecha de contratacion no puede ser superior al dia actual", thrown.getMessage());

		thrown = assertThrows(DatoNoValido.class,
				() -> seguroNoValido5.precio(),
				"Deberia lanzar la exepcion por haber una fecha de contratacion superior al dia actual");
		assertEquals("La fecha de contratacion no puede ser superior al dia actual", thrown.getMessage());
		
		
		
	}
}
