package es.unican.is2.p5ConRefactorCORREGIDO;

import java.util.LinkedList;
import java.util.List;

public class Cliente {

	private String nombre;
	private String telefono;
	private String dni;

	private Direccion direccion;

	private List<Cuenta> Cuentas = new LinkedList<Cuenta>();

	public Cliente(String titular, String telefono, String dni, Direccion direccion) {  // WMC + 1
		this.nombre = titular;
		this.telefono = telefono;
		this.dni = dni;
		this.direccion = direccion;
	}

	public void cambiaDireccion(Direccion direccion) { // WMC + 1
		this.direccion = direccion;
	}

	public void anhadeCuenta(Cuenta c) { // WMC + 1
		Cuentas.add(c);
	}

	public double getSaldoTotal() { // WMC + 1
		double total = 0.0;
		for (Cuenta c: Cuentas) {  // WMC + 1 Ccog + 1
			if (c instanceof CuentaAhorro) { // WMC + 1 Ccog + 2
				total += ((CuentaAhorro) c).getSaldo();
			} else if (c instanceof CuentaValores)  { // WMC + 1 Ccog + 1
				for (Valor v: ((CuentaValores) c).getValores()) { // WMC + 1 Ccog + 3
					total += v.getCotizacion()*v.getNumValores();
				}
			}
		}
		return total;
	}

	public String getNombre() { // WMC + 1
		return nombre;
	}

	public String getTelefono() { // WMC + 1
		return telefono;
	}

	public String getDni() { // WMC + 1
		return dni;
	}

	public Direccion getDireccion() { // WMC + 1
		return this.direccion;
	}



}