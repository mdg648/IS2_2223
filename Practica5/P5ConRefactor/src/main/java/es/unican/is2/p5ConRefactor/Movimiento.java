package es.unican.is2.p5ConRefactor;

import java.time.LocalDateTime;

public class Movimiento {
	private String mConcepto;
	private LocalDateTime mFecha;
	private double mImporte;

	// refactorizacion: cambio de nombres
	
	public double getImporte() { // WMC + 1
		return mImporte;
	}

	public void setImporte(double newMImporte) { // WMC + 1
		mImporte = newMImporte;
	}
	
	public String getConcepto() { // WMC + 1
		return mConcepto;
	}

	public void setConcepto(String newMConcepto) { // WMC + 1
		mConcepto = newMConcepto;
	}

	public LocalDateTime getFecha() { // WMC + 1
		return mFecha;
	}

	public void setFecha(LocalDateTime newMFecha) { // WMC + 1
		mFecha = newMFecha;
	}
	
}