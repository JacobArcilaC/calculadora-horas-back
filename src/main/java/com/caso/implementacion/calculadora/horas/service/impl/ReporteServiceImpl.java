package com.caso.implementacion.calculadora.horas.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.caso.implementacion.calculadora.horas.converter.ReporteConverter;
import com.caso.implementacion.calculadora.horas.models.Reporte;
import com.caso.implementacion.calculadora.horas.models.dto.InformeRequestDTO;
import com.caso.implementacion.calculadora.horas.models.dto.InformeResponseDTO;
import com.caso.implementacion.calculadora.horas.models.dto.ReporteDTO;
import com.caso.implementacion.calculadora.horas.models.dto.ResponseDTO;
import com.caso.implementacion.calculadora.horas.repository.ReporteRepository;
import com.caso.implementacion.calculadora.horas.service.ReporteService;

import java.time.Instant;

@Service
public class ReporteServiceImpl implements ReporteService {

	@Autowired
	ReporteRepository reporteRepository;
	
    final static long SECONDS_SEMANA_LABORAL = 172800; // 48 horas laborales en segundos
	final static float MINUTOS_HORA = 60f;
    
	@Override
	public ResponseDTO<ReporteDTO> crear(ReporteDTO reporteDTO) {
		if(reporteDTO.isValid()) {
			Reporte reporte = ReporteConverter.convert(reporteDTO);
			reporte = reporteRepository.saveAndFlush(reporte);
			reporteDTO = ReporteConverter.convert(reporte);
			return new ResponseDTO<ReporteDTO>(HttpStatus.CREATED, reporteDTO);
		}
		List<String> errores = Arrays.asList("Verifique que todos los campos se hayan diligenciado", "Verifique que la fecha y hora de inicio sea menor o igual que la fecha y hora de finalización"); 
		return new ResponseDTO<ReporteDTO>(HttpStatus.CONFLICT, null, errores);
	}
	
	@Override
	public ResponseDTO<ReporteDTO> buscar(long id){
		Optional<Reporte> reporte = reporteRepository.findById(id);
		ResponseDTO<ReporteDTO> respuesta = new ResponseDTO<ReporteDTO>(HttpStatus.NOT_FOUND, null, null, "No se encontro el reporte.");
		if(reporte.isPresent()) {
			ReporteDTO reporteDTO = ReporteConverter.convert(reporte.get());
			respuesta = new ResponseDTO<ReporteDTO>(HttpStatus.FOUND, reporteDTO);
		}
		return respuesta;
	}

	@Override
	public ResponseDTO<InformeResponseDTO> generar(InformeRequestDTO informeRequest) {
		List<Reporte> reportesSemanal = reporteRepository.filterBy(informeRequest.getIdTecnico(), informeRequest.getSemana());
		InformeResponseDTO informeSemanal = new InformeResponseDTO();
		informeSemanal.setIdTecnico(informeRequest.getIdTecnico());
		informeSemanal.setSemana(informeRequest.getSemana());
		for(Reporte reporte: reportesSemanal) {
			calcularHorasReporte(reporte.getFechaInicio(), reporte.getFechaFin(), informeSemanal);
		}
		return new ResponseDTO<InformeResponseDTO>(HttpStatus.CREATED, informeSemanal);
	}
	
	public void calcularHorasReporte(Date fechaInicio, Date fechaFin, InformeResponseDTO informe) {
		long  SecondInicio = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toEpochSecond();
		long  SecondFin = fechaFin.toInstant().atZone(ZoneId.systemDefault()).toEpochSecond();
		long  SegundosHorasNormales = 0;
		long  SegundosHorasNocturnas = 0;
		long  SegundosHorasDominicales = 0;
		long  SegundosHorasNormalesExtra = 0;
		long  SegundosHorasNocturnasExtra = 0;
		long  SegundosHorasDominicalesExtra = 0;
		long  SegundosSemana = TimeUnit.MINUTES.toSeconds((long) ((informe.getHorasNormales() + informe.getHorasNocturnas() + informe.getHorasDominicales()) * MINUTOS_HORA));
		for(long i = SecondInicio; i <= SecondFin; i++) {
			LocalDateTime fechaActual = secondsToLocalDateTime(i);
			int semanaActual = fechaActual.get(WeekFields.of(Locale.getDefault()).weekOfYear());
			int diaActual = fechaActual.getDayOfWeek().getValue();
			int horaActual = fechaActual.getHour();
			if(semanaActual == informe.getSemana()) {
				if(SegundosSemana < SECONDS_SEMANA_LABORAL) {
					if(diaActual < 7) {
						if(horaActual >= 7 && horaActual < 20) {
							SegundosSemana++;
							SegundosHorasNormales++;
						}else if(horaActual >= 0 && horaActual < 7 || horaActual >= 20) {
							SegundosSemana++;
							SegundosHorasNocturnas++;
						}
					}else {
						SegundosSemana++;
						SegundosHorasDominicales++;
					}
				}else {
					if(diaActual < 7) {
						if(horaActual >= 7 && horaActual < 20) {
							SegundosHorasNormalesExtra++;
						}else if(horaActual >= 0 && horaActual < 7 || horaActual >= 20) {
							SegundosHorasNocturnasExtra++;
						}
					}else {
						SegundosHorasDominicalesExtra++;
					}
				}
			}else {
				break;
			}
		
		}
		informe.setHorasNormales(informe.getHorasNormales() + (TimeUnit.SECONDS.toMinutes(SegundosHorasNormales)/MINUTOS_HORA));
		informe.setHorasNormalesExtra(informe.getHorasNormalesExtra() + (TimeUnit.SECONDS.toMinutes(SegundosHorasNormalesExtra)/MINUTOS_HORA));
		informe.setHorasNocturnas(informe.getHorasNocturnas() + (TimeUnit.SECONDS.toMinutes(SegundosHorasNocturnas)/MINUTOS_HORA));
		informe.setHorasNocturnasExtra(informe.getHorasNocturnasExtra() + (TimeUnit.SECONDS.toMinutes(SegundosHorasNocturnasExtra)/MINUTOS_HORA));
		informe.setHorasDominicales(informe.getHorasDominicales() + (TimeUnit.SECONDS.toMinutes(SegundosHorasDominicales)/MINUTOS_HORA));
		informe.setHorasDominicalesExtra(informe.getHorasDominicalesExtra() + (TimeUnit.SECONDS.toMinutes(SegundosHorasDominicalesExtra)/MINUTOS_HORA));
	}
	
	public static LocalDateTime secondsToLocalDateTime(long seconds) {
	      Instant instant = Instant.ofEpochSecond(seconds);
	      LocalDateTime date = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
	      return date;
	}
	
	
	

}
