package es.unican.is2.p5ConRefactor;

import java.time.LocalDate;

public abstract class Tarjeta {
	
	protected String mNumero, mTitular;		
	protected CuentaAhorro mCuentaAsociada;
	protected LocalDate mFechaCaducidad;

	public Tarjeta(String numero, String titular, CuentaAhorro c, LocalDate date) { // WMC + 1
		mNumero = numero;
		mTitular = titular;
		mCuentaAsociada = c;
		this.mFechaCaducidad = date;
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar. 
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	public abstract void retirar(double x) throws saldoInsuficienteException, datoErroneoException; // WMC + 1

	/**
	 * Pago en establecimiento con la tarjeta
	 * @param datos Concepto del pago
	 * @param x Cantidada a pagar
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	public abstract void pagoEnEstablecimiento(String datos, double x) // WMC + 1
			throws saldoInsuficienteException, datoErroneoException;
	
	public LocalDate getFechaCaducidad() { // WMC + 1
		return this.mFechaCaducidad;
	}
	
}