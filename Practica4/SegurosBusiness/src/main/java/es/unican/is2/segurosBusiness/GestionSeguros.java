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
		if (c == null) {
			return null;
		}

		if (c.getSeguros().size() != 0) {
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

		// comprobamos si el seguro ya existe
		if (this.daoVehiculos.seguros().contains(s)) {
			throw new OperacionNoValida("El seguro ya existe");
		}
		listaSegurosCliente.add(s);
		c.setSeguros(listaSegurosCliente);
		this.daoVehiculos.creaSeguro(s);
		this.daoContribuyentes.actualizaCliente(c);
		return s;
	}

	public Seguro bajaSeguro(String matricula, String dni) throws OperacionNoValida {
		Cliente c = this.daoContribuyentes.cliente(dni);
		Seguro s = this.daoVehiculos.seguro(matricula);

		if (s == null || c == null) {
			return null;
		}

		List<Seguro> segurosCliente = c.getSeguros();
		// se comprueba si el cliente tiene el seguro contratado para poder darse de baja
		if (!segurosCliente.contains(s)) {
			throw new OperacionNoValida("El cliente no tiene ese seguro");
		}
		segurosCliente.remove(s);
		c.setSeguros(segurosCliente);
		this.daoContribuyentes.actualizaCliente(c);
		return this.daoVehiculos.eliminaSeguro(matricula);
	}
}