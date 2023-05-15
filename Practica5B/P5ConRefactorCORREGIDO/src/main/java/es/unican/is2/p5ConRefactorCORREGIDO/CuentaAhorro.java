package es.unican.is2.p5ConRefactorCORREGIDO;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CuentaAhorro extends Cuenta {

	private List<Movimiento> mMovimientos;

	public CuentaAhorro(String numCuenta) { // WMC + 1
		super(numCuenta);
		mMovimientos = new LinkedList<Movimiento>();
	}

	public void ingresar(double x) throws DatoErroneoException { // WMC + 1
		operacion(null, x, false);
	}

	public void retirar(double x) throws SaldoInsuficienteException, DatoErroneoException { // WMC + 1
		operacion(null, x, true);
	}

	public void ingresar(String concepto, double x) throws DatoErroneoException { // WMC + 1
		operacion(concepto, x, false);
	}

	public void retirar(String concepto, double x) throws SaldoInsuficienteException, DatoErroneoException { // WMC + 1
		operacion(concepto, x, true);
	}
	
	
	private void operacion(String concepto, double x, boolean retirar) throws SaldoInsuficienteException, DatoErroneoException { // WMC + 1
		double importe = x;
		String msg = null;
		if (retirar) { // WMC + 1 Ccog + 1
			if (getSaldo() < x) // WMC + 1 Ccog + 2
				throw new SaldoInsuficienteException("Saldo insuficiente");
			 importe = -x;
			msg = "No se puede retirar una cantidad negativa";
			
		} else { // Ccog + 1
			msg = "No se puede ingresar una cantidad negativa";
		}
		
		if (x <= 0) // WMC + 1 Ccog + 1
			throw new DatoErroneoException(msg);
		
		if (concepto == null) { // WMC + 1 Ccog + 1
			if (retirar) { // WMC + 1 Ccog + 2
				concepto = "Retirada de efectivo";
			} else { // Ccog + 1
				concepto = "Ingreso en efectivo";
			}
		}
		
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setFecha(now);
		m.setConcepto(concepto);
		m.setImporte(importe);
		this.mMovimientos.add(m);		
	}
	
	public double getSaldo() { // WMC + 1
		double r = 0.0;
		for (int i = 0; i < this.mMovimientos.size(); i++) { // WMC + 1
			Movimiento m = mMovimientos.get(i);
			r += m.getImporte();
		}
		return r;
	}

	public void addMovimiento(Movimiento m) { // WMC + 1
		mMovimientos.add(m);
	}

	public List<Movimiento> getMovimientos() { // WMC + 1
		return mMovimientos;
	}

}