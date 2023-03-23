package es.unican.is2.segurosCommon;


import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Clase que representa un seguro de coche.
 * Un seguro se identifica por la matrícula
 * del coche para el que se contrata el seguro
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Seguro")
public class Seguro {
	
	private static final double COBERTURA_TODO_RIESGO = 1000;
	private static final double COBERTURA_TERCEROS_LUNAS = 600;
	private static final double COBERTURA_TERCEROS = 400;
	private static final double PORCENTAJE_TRAMO_1 = 1.05;
	private static final double PORCENTAJE_TRAMO_2 = 1.2;
	private static final int INICIO_TRAMO_1= 90;
	private static final int FIN_TRAMO_1=110;
	private static final double DESCUENTO_PRIMER_ANHO = 0.8;
	private static final double DESCUENTO_SEGUNDO_ANHO = 0.9;
	
    
    @XmlAttribute(required = true)
    private int potencia;
    
    @XmlAttribute(required = true)
    private String matricula;
    
    @XmlAttribute(required = true)
    private Cobertura cobertura;
    
    @XmlAttribute(name="fecha", required=true)
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate fechaContratacion;
    
    public Seguro(String matricula, Cobertura cobertura, int potencia, LocalDate fechaContratacion) {
    	this.potencia = potencia;
    	this.matricula = matricula;
    	this.cobertura = cobertura;
    	this.fechaContratacion = fechaContratacion;
    }

	/**
	 * Retorna la matrícula del coche 
	 * asociado al seguro
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * Define el valor para la matrícula
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * Retorna el tipo de cobertura del seguro
	 */
	public Cobertura getCobertura() {
		return cobertura;
	}

	/**
	 * Define el valor para la cobertura
	 */
	public void setCobertura(Cobertura cobertura) {
		this.cobertura = cobertura;
	}


	/**
     * Retorna la potencia del coche asociado 
     * al seguro. 
     */
    public int getPotencia() {
        return potencia;
    }

    /**
     * Define el valor de la potencia.
     */
    public void setPotencia(int value) {
        this.potencia = value;
    }
    
    /**
     * Retorna el precio del seguro
     * @return
     */
    public double precio() {
    	double precio = 0;
    	
    	// precio inicial segun nivel de cobertura
    	switch(this.cobertura) {
    		case TODORIESGO:
    			precio = COBERTURA_TODO_RIESGO;
    		case TERCEROSLUNAS:
    			precio = COBERTURA_TERCEROS_LUNAS;
    		case TERCEROS:
    			precio = COBERTURA_TERCEROS;
			default:
				break;
    	}
    	
    	// aumento segun potencia del coche
    	if(this.potencia >= INICIO_TRAMO_1 && this.potencia <= FIN_TRAMO_1) {
    		precio = precio * PORCENTAJE_TRAMO_1;
    	} else if(this.potencia > FIN_TRAMO_1) {
    		precio = precio * PORCENTAJE_TRAMO_2;
    	}
    	
    	// descuento de oferta
    	LocalDate diferenciaTiempoAnho = LocalDate.now().minusYears(1);
    	LocalDate diferenciaTiempo2Anhos = LocalDate.now().minusYears(2);
    	if(this.fechaContratacion.isAfter(diferenciaTiempoAnho)) {
    		precio = precio * DESCUENTO_PRIMER_ANHO;
    	} else if(this.fechaContratacion.isAfter(diferenciaTiempo2Anhos)) {
    		precio = precio * DESCUENTO_SEGUNDO_ANHO;
    	}
    		
    	return precio;
    }

}
