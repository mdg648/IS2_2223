package es.unican.is2.p5ConRefactorCORREGIDO;

import java.time.LocalDate;

public abstract class Tarjeta {
	
	protected String mNumero;
	protected String mTitular;		
	protected CuentaAhorro mCuentaAsociada;
	protected LocalDate mFechaCaducidad;

	protected Tarjeta(String numero, String titular, CuentaAhorro c, LocalDate date) { // WMC + 1-
		mNumero = numero;
		mTitular = titular;
		mCuentaAsociada = c;
		this.mFechaCaducidad = date;
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar. 
	 * @throws SaldoInsuficienteException
	 * @throws DatoErroneoException
	 */
	public abstract void retirar(double x) throws SaldoInsuficienteException, DatoErroneoException; 

	/**
	 * Pago en establecimiento con la tarjeta
	 * @param datos Concepto del pago
	 * @param x Cantidada a pagar
	 * @throws SaldoInsuficienteException
	 * @throws DatoErroneoException
	 */
	public abstract void pagoEnEstablecimiento(String datos, double x) 
			throws SaldoInsuficienteException, DatoErroneoException;
	
	public LocalDate getFechaCaducidad() { // WMC + 1
		return this.mFechaCaducidad;
	}
	
}