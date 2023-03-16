package es.unican.is2.segurosMain;

import es.unican.is2.segurosBusiness.GestionSeguros;
import es.unican.is2.segurosCommon.IClientesDAO;
import es.unican.is2.segurosCommon.ISegurosDAO;
import es.unican.is2.segurosDAO.ClientesDAO;
import es.unican.is2.segurosDAO.SegurosDAO;
import es.unican.is2.segurosGUI.VistaAgente;

public class Runner {

	public static void main(String[] args) {
		IClientesDAO daoContribuyentes = new ClientesDAO();
		ISegurosDAO daoVehiculos = new SegurosDAO();
		GestionSeguros negocio = new GestionSeguros(daoContribuyentes, daoVehiculos);
		VistaAgente vista = new VistaAgente(negocio, negocio, negocio);
		vista.setVisible(true);

	}

}
