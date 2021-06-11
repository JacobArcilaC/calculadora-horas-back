package com.caso.implementacion.calculadora.horas.converter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.Locale;

import com.caso.implementacion.calculadora.horas.models.Reporte;
import com.caso.implementacion.calculadora.horas.models.dto.ReporteDTO;

public class ReporteConverter {
	
	private ReporteConverter() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static Reporte convert(ReporteDTO reporteDTO) {
		Reporte reporte = new Reporte();
		reporte.setIdTecnico(reporteDTO.getIdTecnico());
		reporte.setIdServicio(reporteDTO.getIdServicio());
		reporte.setIdTecnico(reporteDTO.getIdTecnico());
		reporte.setFechaInicio(reporteDTO.getFechaInicio());
		reporte.setFechaFin(reporteDTO.getFechaFin());
		LocalDate fechaInicio = reporteDTO.getFechaInicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int week = fechaInicio.get(WeekFields.of(Locale.getDefault()).weekOfYear());
		reporte.setWeek(week);
		return reporte;
	}
	
	public static ReporteDTO convert(Reporte reporte) {
		ReporteDTO reporteDTO = new ReporteDTO();
		reporteDTO.setIdTecnico(reporte.getIdTecnico());
		reporteDTO.setIdServicio(reporte.getIdServicio());
		reporteDTO.setIdTecnico(reporte.getIdTecnico());
		reporteDTO.setFechaInicio(reporte.getFechaInicio());
		reporteDTO.setFechaFin(reporte.getFechaFin());
		return reporteDTO;
	}
	
}
