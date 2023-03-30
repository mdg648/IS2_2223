package es.unican.is2.segurosCommon;
	
@SuppressWarnings("serial")
public class DatoNoValido extends Exception {
	
	public DatoNoValido(String mensaje) {
		super(mensaje);
	}
}
