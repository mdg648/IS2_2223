package es.unican.is2.p5SinRefactor;

import java.util.LinkedList;
import java.util.List;

public class Cliente {
	
	public String nombre;
	public String calle;
	public String zip;
	public String localidad;
	public String telefono;
	public String dni;
	
    private List<Cuenta> Cuentas = new LinkedList<Cuenta>();

 	public Cliente(String titular, String calle, String zip, String localidad, 
 			String telefono, String dni) {  // WMC + 1
		this.nombre = titular;
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
		this.telefono = telefono;
		this.dni = dni;
	}
	
	public void cambiaDireccion(String calle, String zip, String localidad) { // WMC + 1
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
	}
	
	public void anhadeCuenta(Cuenta c) { // WMC + 1
		Cuentas.add(c);
	}
	
	public double getSaldoTotal() { // WMC + 1
		double total = 0.0;
		for (Cuenta c: Cuentas) {  // WMC + 1 Ccog + 1
			if (c instanceof CuentaAhorro) { // WMC + 1  Ccog + 2
				total += ((CuentaAhorro) c).getSaldo();
			} else if (c instanceof CuentaValores)  { // WMC + 1  Ccog + 1
				for (Valor v: ((CuentaValores) c).getValores()) { // WMC + 1  Ccog + 3
					total += v.getCotizacion()*v.getNumValores();
				}
			}
		}
		return total;
	}
	
	public String getNombre() { // WMC + 1
		return nombre;
	}

	public String getCalle() { // WMC + 1
		return calle;
	}

	public String getZip() { // WMC + 1
		return zip;
	}

	public String getLocalidad() { // WMC + 1
		return localidad;
	}

	public String getTelefono() { // WMC + 1
		return telefono;
	}

	public String getDni() { // WMC + 1
		return dni;
	}
	
	
	
}