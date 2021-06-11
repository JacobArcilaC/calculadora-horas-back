package com.caso.implementacion.calculadora.horas.service.impl;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.caso.implementacion.calculadora.horas.models.dto.ReporteDTO;
import com.caso.implementacion.calculadora.horas.models.dto.ResponseDTO;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testing ReporteServiceImpl")
public class ReporteServiceImplUnitTest {

	@InjectMocks
	ReporteServiceImpl reporteService;
	
	@Test
	@Order(1) 
	@DisplayName("Testing Crear Reporte")
	void crear() throws Exception{
		ReporteDTO reporteDTO = new ReporteDTO();
		reporteDTO.setIdTecnico("1152224910");
		reporteDTO.setIdServicio("1");
		reporteDTO.setFechaInicio(null);
		reporteDTO.setFechaFin(null);
		ResponseDTO<ReporteDTO> reporteRespuesta = reporteService.crear(reporteDTO);
		Assertions.assertEquals(409, reporteRespuesta.getCode());
		Assertions.assertTrue(reporteRespuesta.getData() == null);
		ReporteDTO reporteDTO1 = new ReporteDTO();
		reporteDTO1.setIdTecnico("1152224910");
		reporteDTO1.setIdServicio("1");
		reporteDTO1.setFechaInicio(new Date());
		reporteDTO1.setFechaFin(new Date());
		ResponseDTO<ReporteDTO> reporteRespuesta1 = reporteService.crear(reporteDTO1);
		Assertions.assertTrue(reporteRespuesta1.getData().isValid());
		
	}
	
	@Test
	@Order(2) 
	@DisplayName("Testing buscar Reporte")
	void buscar() throws Exception{
		ResponseDTO<ReporteDTO> reporteRespuesta = reporteService.buscar(1);
		Assertions.assertEquals(302, reporteRespuesta.getCode());
		Assertions.assertTrue(reporteRespuesta.getData() != null);
		reporteRespuesta = reporteService.buscar(3);
		Assertions.assertEquals(404, reporteRespuesta.getCode());
		Assertions.assertTrue(reporteRespuesta.getData() == null);
	}
	
}
