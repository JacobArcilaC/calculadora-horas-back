package com.caso.implementacion.calculadora.horas.models.dto;

public class InformeResponseDTO {
	
	private String idTecnico;
	private int semana;
	private float horasNormales;
	private float horasNocturnas;
	private float horasDominicales;
	private float horasNormalesExtra;
	private float horasNocturnasExtra;
	private float horasDominicalesExtra;
	
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
	public float getHorasNormales() {
		return horasNormales;
	}
	public void setHorasNormales(float horasNormales) {
		this.horasNormales = horasNormales;
	}
	public float getHorasNocturnas() {
		return horasNocturnas;
	}
	public void setHorasNocturnas(float horasNocturnas) {
		this.horasNocturnas = horasNocturnas;
	}
	public float getHorasDominicales() {
		return horasDominicales;
	}
	public void setHorasDominicales(float horasDominicales) {
		this.horasDominicales = horasDominicales;
	}
	public float getHorasNormalesExtra() {
		return horasNormalesExtra;
	}
	public void setHorasNormalesExtra(float horasNormalesExtra) {
		this.horasNormalesExtra = horasNormalesExtra;
	}
	public float getHorasNocturnasExtra() {
		return horasNocturnasExtra;
	}
	public void setHorasNocturnasExtra(float horasNocturnasExtra) {
		this.horasNocturnasExtra = horasNocturnasExtra;
	}
	public float getHorasDominicalesExtra() {
		return horasDominicalesExtra;
	}
	public void setHorasDominicalesExtra(float horasDominicalesExtra) {
		this.horasDominicalesExtra = horasDominicalesExtra;
	}
	

	
	
}
