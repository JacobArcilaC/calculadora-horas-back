package com.caso.implementacion.calculadora.horas.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="REPORTE")
public class Reporte {
	
	@Id
	@Column(name="ID")
	@SequenceGenerator(name = "reporte_gen", sequenceName = "reporte_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reporte_gen")
	private Long id;
	
	@Column(name = "ID_TECNICO")
	private String idTecnico;
	
	@Column(name = "ID_SERVICIO")
	private String idServicio;
	
	@Column(name = "FECHA_INICIO")
	@DateTimeFormat(pattern="yyyy-MM-dd'T'hh:mm")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaInicio;
	
	@Column(name = "FECHA_FIN")
	@DateTimeFormat(pattern="yyyy-MM-dd'T'hh:mm")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaFin;
	
	@Column(name = "SEMANA")
	private int semana;

	public int getSemana() {
		return semana;
	}

	public void setWeek(int semana) {
		this.semana = semana;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
}
