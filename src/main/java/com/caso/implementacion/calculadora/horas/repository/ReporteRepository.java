package com.caso.implementacion.calculadora.horas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.caso.implementacion.calculadora.horas.models.Reporte;


public interface ReporteRepository extends JpaRepository<Reporte,Long>{
	
	@Query("select reporte from Reporte reporte where reporte.idTecnico = :idTecnico and reporte.semana = :semana")
	public List<Reporte> filterBy(@Param("idTecnico") String idTecnico, @Param("semana") int semana);

}
