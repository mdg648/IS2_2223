package es.unican.is2.segurosMain;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.fest.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.segurosBusiness.GestionSeguros;
import es.unican.is2.segurosCommon.IClientesDAO;
import es.unican.is2.segurosCommon.ISegurosDAO;
import es.unican.is2.segurosDAO.ClientesDAO;
import es.unican.is2.segurosDAO.SegurosDAO;
import es.unican.is2.segurosGUI.VistaAgente;


public class VistaAgenteITest {
	
	private FrameFixture demo;

	
	@BeforeEach
	public void setUp() {
		IClientesDAO daoContribuyentes = new ClientesDAO();
		ISegurosDAO daoVehiculos = new SegurosDAO();
		GestionSeguros negocio = new GestionSeguros(daoContribuyentes, daoVehiculos);
		VistaAgente gui = new VistaAgente(negocio, negocio, negocio);
		demo = new FrameFixture(gui);
		gui.setVisible(true);
	}
	
	@AfterEach
	public void tearDown() {
		demo.cleanUp();
	}
	
	@Test
	public void test() {
		// probar caso valido DNI registrado
		demo.textBox("txtDNICliente").setText("12345678S");
		demo.button("btnBuscar").click();
		demo.textBox("txtNombreCliente").requireText("Andres Ortega");
		assertTrue(demo.list("listSeguros").valueAt(0).equals("PLL9597 TODORIESGO"));
		demo.textBox("txtTotalCliente").requireText("675.0");
		
		// probar caso DNI no registrado
		demo.textBox("txtDNICliente").setText("87654321S");
		demo.button("btnBuscar").click();
		demo.textBox("txtNombreCliente").requireText("");
		assertThrows(IndexOutOfBoundsException.class, () -> demo.list("listSeguros").valueAt(0));
		demo.textBox("txtTotalCliente").requireText("");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}