package es.unican.is2.p5ConRefactor;

public class Direccion {
	
	public String calle;
	public String zip;
	public String localidad;

	public Direccion(String calle, String zip, String localidad) {
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
	}
	
	public String getCalle() {
		return calle;
	}

	public String getZip() {
		return zip;
	}

	public String getLocalidad() {
		return localidad;
	}
}
