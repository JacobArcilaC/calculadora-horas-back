package com.caso.implementacion.calculadora.horas.service;

import com.caso.implementacion.calculadora.horas.models.dto.InformeRequestDTO;
import com.caso.implementacion.calculadora.horas.models.dto.InformeResponseDTO;
import com.caso.implementacion.calculadora.horas.models.dto.ReporteDTO;
import com.caso.implementacion.calculadora.horas.models.dto.ResponseDTO;

public interface ReporteService {
	
	ResponseDTO<ReporteDTO> crear(ReporteDTO reporte);

	ResponseDTO<ReporteDTO> buscar(long id);
	
	ResponseDTO<InformeResponseDTO> generar(InformeRequestDTO informe);
}
