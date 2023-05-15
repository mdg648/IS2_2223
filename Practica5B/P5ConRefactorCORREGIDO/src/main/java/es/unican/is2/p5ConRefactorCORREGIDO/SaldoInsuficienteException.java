package es.unican.is2.p5ConRefactorCORREGIDO;

@SuppressWarnings("serial")
public class SaldoInsuficienteException extends RuntimeException {

	public SaldoInsuficienteException (String mensaje) {
		super(mensaje);
	}
}
