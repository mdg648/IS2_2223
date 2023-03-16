package es.unican.is2.segurosCommon;

@SuppressWarnings("serial")
public class OperacionNoValida extends RuntimeException {

	public OperacionNoValida(String string) {
		super(string);
	}

}
