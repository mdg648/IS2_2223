package es.unican.is2.p5ConRefactor;

import java.time.LocalDateTime;

public class Movimiento {
	private String mConcepto;
	private LocalDateTime mFecha;
	private double mImporte;

	// refactorizacion: cambio de nombres
	
	public double getImporte() {
		return mImporte;
	}

	public void setImporte(double newMImporte) {
		mImporte = newMImporte;
	}
	
	public String getConcepto() {
		return mConcepto;
	}

	public void setConcepto(String newMConcepto) {
		mConcepto = newMConcepto;
	}

	public LocalDateTime getFecha() {
		return mFecha;
	}

	public void setFecha(LocalDateTime newMFecha) {
		mFecha = newMFecha;
	}
	
}