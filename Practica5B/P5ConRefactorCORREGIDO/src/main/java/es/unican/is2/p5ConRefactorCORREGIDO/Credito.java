package es.unican.is2.p5ConRefactorCORREGIDO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


public class Credito extends Tarjeta {
	
	private double mCredito;
	private List<Movimiento> mMovimientosMensuales;
	private List<Movimiento> mhistoricoMovimientos;
	
	// refactorizacion: definicion de constante
	private static final double COMISION = 0.05;
	
	
	public Credito(String numero, String titular, CuentaAhorro c, double credito, LocalDate date) { // WMC + 1
		super(numero, titular, c, date);
		mCredito = credito;
		mMovimientosMensuales = new LinkedList<Movimiento>();
		mhistoricoMovimientos = new LinkedList<Movimiento>();
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar. Se aplica una comisión del 5%.
	 * @throws SaldoInsuficienteException
	 * @throws DatoErroneoException
	 */
	@Override
	public void retirar(double x) throws SaldoInsuficienteException, DatoErroneoException { // WMC + 1 
		if (x<0) // WMC + 1 Ccog + 1
			throw new DatoErroneoException("No se puede retirar una cantidad negativa");
		
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setFecha(now);
		m.setConcepto("Retirada en cajero automático");
		x += x * COMISION; // Añadimos una comisión de un 5%
		m.setImporte(-x);
		
		if (getGastosAcumulados()+x > mCredito) // WMC + 1 Ccog + 1
			throw new SaldoInsuficienteException("Crédito insuficiente");
		else { // Ccog + 1
			mMovimientosMensuales.add(m);
		}
	}

	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws SaldoInsuficienteException, DatoErroneoException { // WMC + 1
		if (x<0) // WMC + 1 Ccog + 1
			throw new DatoErroneoException("No se puede pagar una cantidad negativa");
		
		if (getGastosAcumulados() + x > mCredito) // WMC + 1 Ccog + 1
			throw new SaldoInsuficienteException("Saldo insuficiente");
		
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setFecha(now);
		m.setConcepto("Compra a crédito en: " + datos);
		m.setImporte(-x);
		mMovimientosMensuales.add(m);
	}
	
    public double getGastosAcumulados() { // WMC + 1
		double r = 0.0;
		for (int i = 0; i < this.mMovimientosMensuales.size(); i++) { // WMC + 1 Ccog + 1
			Movimiento m = mMovimientosMensuales.get(i);
			r += m.getImporte();
		}
		return -r;
	}


	/**
	 * Método que se invoca automáticamente el día 1 de cada mes
	 */
	public void liquidar() { // WMC + 1
		Movimiento liq = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		liq.setFecha(now);
		liq.setConcepto("Liquidación de operaciones tarjeta crédito");
		double r = -this.getGastosAcumulados();
		liq.setImporte(r);
	
		if (r != 0) // WMC + 1 Ccog + 1
			mCuentaAsociada.addMovimiento(liq);
		
		mhistoricoMovimientos.addAll(mMovimientosMensuales);
		mMovimientosMensuales.clear();
	}

	public List<Movimiento> getMovimientosUltimoMes() { // WMC + 1
		return mMovimientosMensuales;
	}
	
	public Cuenta getCuentaAsociada() { // WMC + 1
		return mCuentaAsociada;
	}
	
	public List<Movimiento> getMovimientos() { // WMC + 1
		return mhistoricoMovimientos;
	}


}