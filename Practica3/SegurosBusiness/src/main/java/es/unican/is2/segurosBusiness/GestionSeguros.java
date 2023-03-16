package es.unican.is2.segurosBusiness;

import es.unican.is2.segurosCommon.IGestionClientes;
import es.unican.is2.segurosCommon.IGestionSeguros;
import es.unican.is2.segurosCommon.IInfoSeguros;
import es.unican.is2.segurosCommon.ISegurosDAO;
import es.unican.is2.segurosCommon.Cliente;
import es.unican.is2.segurosCommon.IClientesDAO;
import es.unican.is2.segurosCommon.OperacionNoValida;
import es.unican.is2.segurosCommon.Seguro;

public class GestionSeguros implements IGestionSeguros,IGestionClientes, IInfoSeguros {
	
	private IClientesDAO daoContribuyentes;
	
	private ISegurosDAO daoVehiculos;
	
	public GestionSeguros(IClientesDAO daoContribuyentes, ISegurosDAO daoVehiculos) {
		
	}
	
	public Cliente cliente(String dni) {
		// TODO Auto-generated method stub
		return IClientesDAO;
	}

	public Seguro seguro(String matricula) {
		// TODO Auto-generated method stub
		return null;
	}

	public Cliente nuevoCliente(Cliente c) {
		// TODO Auto-generated method stub
		return null;
	}

	public Cliente bajaCliente(String dni) throws OperacionNoValida {
		// TODO Auto-generated method stub
		return null;
	}

	public Seguro nuevoSeguro(Seguro s, String dni) throws OperacionNoValida {
		// TODO Auto-generated method stub
		return null;
	}

	public Seguro bajaSeguro(String matricula, String dni) throws OperacionNoValida {
		// TODO Auto-generated method stub
		return null;
	}}