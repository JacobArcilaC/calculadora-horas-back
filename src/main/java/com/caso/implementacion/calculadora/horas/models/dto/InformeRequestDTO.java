package com.caso.implementacion.calculadora.horas.models.dto;

public class InformeRequestDTO {
	
	String idTecnico;
	int semana;
	
	public String getIdTecnico() {
		return idTecnico;
	}
	public void setIdTecnico(String idTecnico) {
		this.idTecnico = idTecnico;
	}
	public int getSemana() {
		return semana;
	}
	public void setSemana(int semana) {
		this.semana = semana;
	}
	
}
