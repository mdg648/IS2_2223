package es.unican.is2.p5ConRefactor;

import java.time.LocalDate;

public class Debito extends Tarjeta {
	
	private double saldoDiarioDisponible;
	private double limiteDebito;
	
	private static final double LIMITE_DEBITO = 1000;

	public Debito(String numero, String titular, CuentaAhorro c, LocalDate date) { // WMC + 1
		super(numero, titular, c, date);
		limiteDebito = LIMITE_DEBITO;
	}
	
	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException { // WMC + 1
		if (saldoDiarioDisponible<x) { // WMC + 1 Ccog + 1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.mCuentaAsociada.retirar("Retirada en cajero automático", x);
		saldoDiarioDisponible-=x;
	}
	
	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException { // WMC + 1
		if (saldoDiarioDisponible<x) { // WMC + 1 Ccog + 1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.mCuentaAsociada.retirar("Compra en : " + datos, x);
		saldoDiarioDisponible-=x;
	}
	
	/**
	 * Método invocado automáticamente a las 00:00 de cada día
	 */
	public void restableceSaldo() { // WMC + 1
		saldoDiarioDisponible = this.limiteDebito;
	}
	
	public CuentaAhorro getCuentaAsociada() { // WMC + 1
		return mCuentaAsociada;
	}

	public double getLimiteDebito() { // WMC + 1
		return limiteDebito;
	}

}