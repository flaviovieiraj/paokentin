package com.devcaotics.PaoKentin.model.classes;

import java.sql.Timestamp;

public class Fornada {
	
	private int codigo;
	private Timestamp horaInicio;
	private boolean pronto;
	private boolean quente;	
	private Pao pao;
	
	public Fornada() {
		this.horaInicio = new Timestamp(System.currentTimeMillis());
		this.pronto = false;
		this.quente = false;
		this.pao = null;
	}

	

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	

	public Timestamp getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Timestamp horaInicio) {
		this.horaInicio = horaInicio;
	}


	public Pao getPao() {
		return pao;
	}

	public void setPao(Pao pao) {
		this.pao = pao;
	}

	public boolean isPronto() {
		return pronto;
	}

	public void setPronto(boolean pronto) {
		this.pronto = pronto;
	}

	public boolean isQuente() {
		return quente;
	}

	public void setQuente(boolean quente) {
		this.quente = quente;
	}
	
	
	
	

}
