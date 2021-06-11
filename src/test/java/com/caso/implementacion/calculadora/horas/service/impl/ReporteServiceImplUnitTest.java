package com.caso.implementacion.calculadora.horas.service.impl;

import static org.mockito.Mockito.lenient;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.caso.implementacion.calculadora.horas.models.Reporte;
import com.caso.implementacion.calculadora.horas.models.dto.ReporteDTO;
import com.caso.implementacion.calculadora.horas.models.dto.ResponseDTO;
import com.caso.implementacion.calculadora.horas.repository.ReporteRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testing ReporteServiceImpl")
public class ReporteServiceImplUnitTest {

	@InjectMocks
	ReporteServiceImpl reporteService;
	
	@Mock
	ReporteRepository reporteRepository;
	
	@BeforeEach
	void setUp() {
		ReflectionTestUtils.setField(reporteService, "reporteRepository", reporteRepository);
	}
	
	@Test
	@DisplayName("Testing Crear Reporte")
	void crear() throws Exception{
		ReporteDTO reporteDTO = new ReporteDTO();
		reporteDTO.setIdTecnico("1152224910");
		reporteDTO.setIdServicio("1");
		reporteDTO.setFechaInicio(null);
		reporteDTO.setFechaFin(null);
		Reporte reporte = new Reporte();
		reporte.setIdTecnico(reporteDTO.getIdTecnico());
		reporte.setIdServicio(reporteDTO.getIdServicio());
		lenient().when(reporteRepository.saveAndFlush(Mockito.any(Reporte.class))).thenReturn(reporte);
		ResponseDTO<ReporteDTO> reporteRespuesta = reporteService.crear(reporteDTO);
		Assertions.assertEquals(409, reporteRespuesta.getCode());
		Assertions.assertTrue(reporteRespuesta.getData() == null);
		ReporteDTO reporteDTO1 = new ReporteDTO();
		reporteDTO1.setIdTecnico("1152224910");
		reporteDTO1.setIdServicio("1");
		reporteDTO1.setFechaInicio(new Date());
		reporteDTO1.setFechaFin(new Date());
		reporte.setFechaInicio(reporteDTO1.getFechaInicio());
		reporte.setFechaFin(reporteDTO1.getFechaFin());
		lenient().when(reporteRepository.saveAndFlush(Mockito.any(Reporte.class))).thenReturn(reporte);
		ResponseDTO<ReporteDTO> reporteRespuesta1 = reporteService.crear(reporteDTO1);
		Assertions.assertTrue(reporteRespuesta1.getData().isValid());
		
	}
	
	@Test
	@DisplayName("Testing Buscar Reporte")
	void buscar() throws Exception{
		lenient().when(reporteRepository.findById(1L)).thenReturn(Optional.ofNullable(new Reporte()));
		ResponseDTO<ReporteDTO> reporteRespuesta = reporteService.buscar(1L);
		Assertions.assertEquals(302, reporteRespuesta.getCode());
		Assertions.assertTrue(reporteRespuesta.getData() != null);
		lenient().when(reporteRepository.findById(3L)).thenReturn(Optional.ofNullable(null));
		reporteRespuesta = reporteService.buscar(3);
		Assertions.assertEquals(404, reporteRespuesta.getCode());
		Assertions.assertTrue(reporteRespuesta.getData() == null);
	}
	
	
	
}
