package com.caso.implementacion.calculadora.horas.models.dto;

import java.util.Date;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReporteDTO {

	private String idTecnico;
	private String idServicio;
	@JsonFormat(pattern="yyyy-MM-dd'T'hh:mm", timezone="GMT-5")
	private Date fechaInicio;
	@JsonFormat(pattern="yyyy-MM-dd'T'hh:mm", timezone="GMT-5")
	private Date fechaFin;
	
	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getIdTecnico() {
		return idTecnico;
	}
	
	public void setIdTecnico(String idTecnico) {
		this.idTecnico = idTecnico;
	}
	
	public String getIdServicio() {
		return idServicio;
	}
	
	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}
	
	public boolean isValid() {
		return !StringUtils.isBlank(idServicio) && !StringUtils.isBlank(idTecnico) && ( (!Objects.isNull(fechaInicio) && !Objects.isNull(fechaFin)) && fechaInicio.compareTo(fechaFin) <= 0 );
	}

}
