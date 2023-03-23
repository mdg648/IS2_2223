package es.unican.is2.segurosCommon;

import java.time.LocalDate;

public class SeguroTest {
	@Test
	public void testPrecio() {
		// Casos validos 
		Seguro seguroValido1 = new Seguro("1234ABC", Cobertura.TODORIESGO, 90, LocalDate.now().minusDays(364));
		Seguro seguroValido2 = new Seguro("1234ABC", Cobertura.TERCEROSLUNAS, 100, LocalDate.now().minusDays(150));
		Seguro seguroValido3 = new Seguro("1234ABC", Cobertura.TERCEROS, 110, LocalDate.now());
		Seguro seguroValido4 = new Seguro("1234ABC", Cobertura.TODORIESGO, 111, LocalDate.now().minusDays(729));
		Seguro seguroValido5 = new Seguro("1234ABC", Cobertura.TERCEROSLUNAS, 300, LocalDate.now().minusDays(500));
		Seguro seguroValido6 = new Seguro("1234ABC", Cobertura.TERCEROS, 89, LocalDate.now().minusDays(365));
		Seguro seguroValido7 = new Seguro("1234ABC", Cobertura.TODORIESGO, 1, LocalDate.now().minusDays(730));
		Seguro seguroValido8 = new Seguro("1234ABC", Cobertura.TERCEROSLUNAS, 90, LocalDate.now().minusDays(1000));
		
		// Casos no validos
	}
}
