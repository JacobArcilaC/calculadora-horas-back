package com.caso.implementacion.calculadora.horas.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caso.implementacion.calculadora.horas.models.dto.InformeRequestDTO;
import com.caso.implementacion.calculadora.horas.models.dto.InformeResponseDTO;
import com.caso.implementacion.calculadora.horas.models.dto.ReporteDTO;
import com.caso.implementacion.calculadora.horas.models.dto.ResponseDTO;
import com.caso.implementacion.calculadora.horas.service.ReporteService;


@RestController
@RequestMapping("api/reporte")
@CrossOrigin("*")
public class ReporteRestController {
	
	@Autowired
	ReporteService reporteService;

	@PostMapping
	public ResponseDTO<ReporteDTO> crearReporte(@RequestBody ReporteDTO reporte) {
		return reporteService.crear(reporte);
	}
	
	@PostMapping("/buscar/{id}")
	public ResponseDTO<ReporteDTO> buscarReporte(@PathVariable("id") long id){
		return reporteService.buscar(id);
	}
	
	@PostMapping("/informe")
	public ResponseDTO<InformeResponseDTO> generarInforme(@RequestBody InformeRequestDTO informeRequest){
		return reporteService.generar(informeRequest);
	}
	
	
}
