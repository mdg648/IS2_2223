package es.unican.is2.p5ConRefactor;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CuentaAhorro extends Cuenta {

	private List<Movimiento> mMovimientos;

	public CuentaAhorro(String numCuenta) {
		super(numCuenta);
		mMovimientos = new LinkedList<Movimiento>();
	}

	public void ingresar(double x) throws datoErroneoException {
		operacion(null, x, false);
	}

	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {
		operacion(null, x, true);
	}

	public void ingresar(String concepto, double x) throws datoErroneoException {
		operacion(concepto, x, false);
	}

	public void retirar(String concepto, double x) throws saldoInsuficienteException, datoErroneoException {
		operacion(concepto, x, true);
	}
	
	
	private void operacion(String concepto, double x, boolean retirar) throws saldoInsuficienteException, datoErroneoException {
		String msg;
		if (retirar) {
			if (getSaldo() < x)
				throw new saldoInsuficienteException("Saldo insuficiente");
			x = -x;
			msg = "No se puede retirar una cantidad negativa";
			
		} else {
			msg = "No se puede ingresar una cantidad negativa";
		}
		
		if (x <= 0)
			throw new datoErroneoException(msg);
		
		if (concepto == null) {
			if (retirar) {
				concepto = "Retirada de efectivo";
			} else {
				concepto = "Ingreso en efectivo";
			}
		}
		
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setFecha(now);
		m.setConcepto(concepto);
		m.setImporte(x);
		this.mMovimientos.add(m);		
	}
	
	public double getSaldo() {
		double r = 0.0;
		for (int i = 0; i < this.mMovimientos.size(); i++) {
			Movimiento m = (Movimiento) mMovimientos.get(i);
			r += m.getImporte();
		}
		return r;
	}

	public void addMovimiento(Movimiento m) {
		mMovimientos.add(m);
	}

	public List<Movimiento> getMovimientos() {
		return mMovimientos;
	}

}