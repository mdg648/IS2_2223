package es.unican.is2.segurosBusiness;

import es.unican.is2.segurosCommon.IGestionClientes;
import es.unican.is2.segurosCommon.IGestionSeguros;
import es.unican.is2.segurosCommon.IInfoSeguros;
import es.unican.is2.segurosCommon.ISegurosDAO;

import java.util.List;

import es.unican.is2.segurosCommon.Cliente;
import es.unican.is2.segurosCommon.IClientesDAO;
import es.unican.is2.segurosCommon.OperacionNoValida;
import es.unican.is2.segurosCommon.Seguro;

public class GestionSeguros implements IGestionSeguros,IGestionClientes, IInfoSeguros {
	
	private IClientesDAO daoContribuyentes;
	
	private ISegurosDAO daoVehiculos;
	
	public GestionSeguros(IClientesDAO daoContribuyentes, ISegurosDAO daoVehiculos) {
		this.daoContribuyentes = daoContribuyentes;
		this.daoVehiculos = daoVehiculos;
	}
	
	public Cliente cliente(String dni) {
		return this.daoContribuyentes.cliente(dni);
	}

	public Seguro seguro(String matricula) {
		return this.daoVehiculos.seguro(matricula);
	}

	public Cliente nuevoCliente(Cliente c) {
		return this.daoContribuyentes.creaCliente(c);
	}

	public Cliente bajaCliente(String dni) throws OperacionNoValida {
		Cliente c = this.daoContribuyentes.cliente(dni);
		if (c != null && c.getSeguros() != null) {
			throw new OperacionNoValida("El cliente tiene seguros a su nombre");
		}
		return this.daoContribuyentes.eliminaCliente(dni);
	}

	public Seguro nuevoSeguro(Seguro s, String dni) throws OperacionNoValida {
		Cliente c = this.daoContribuyentes.cliente(dni);
		if (c == null) {
			return null;
		}
		List<Seguro> listaSegurosCliente = c.getSeguros();
		// comprobamos is ya tiene contratado ese seguro el cliente
		if (listaSegurosCliente.contains(s)) {
			throw new OperacionNoValida("El seguro ya existe");
		}
		listaSegurosCliente.add(s);
		c.setSeguros(listaSegurosCliente);
		return s;
	}

	public Seguro bajaSeguro(String matricula, String dni) throws OperacionNoValida {
		Cliente c = this.daoContribuyentes.cliente(dni);
		Seguro s = this.daoVehiculos.seguro(matricula);
		
		if (s == null || c == null) {
			return null;
		}
		
		// se comprueba si el cliente tiene el seguro contratado para poder darse de baja
		if (!c.getSeguros().contains(s)) {
			throw new OperacionNoValida("El cliente no tiene ese seguro");
		}
		return this.daoVehiculos.eliminaSeguro(matricula);
	}}