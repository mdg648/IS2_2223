package es.unican.is2.p5ConRefactor;

import java.time.LocalDate;

public class Debito extends Tarjeta {
	
	private double saldoDiarioDisponible;
	private double limiteDebito;
	
	private static final int LIMITE_DEBITO = 1000;

	public Debito(String numero, String titular, CuentaAhorro c, LocalDate date) {
		super(numero, titular, c, date);
		limiteDebito = LIMITE_DEBITO;
	}
	
	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {
		if (saldoDiarioDisponible<x) {
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.mCuentaAsociada.retirar("Retirada en cajero automático", x);
		saldoDiarioDisponible-=x;
	}
	
	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException {
		if (saldoDiarioDisponible<x) {
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.mCuentaAsociada.retirar("Compra en : " + datos, x);
		saldoDiarioDisponible-=x;
	}
	
	/**
	 * Método invocado automáticamente a las 00:00 de cada día
	 */
	public void restableceSaldo() {
		saldoDiarioDisponible = this.limiteDebito;
	}
	
	public CuentaAhorro getCuentaAsociada() {
		return mCuentaAsociada;
	}

	public double getLimiteDebito() {
		return limiteDebito;
	}

}