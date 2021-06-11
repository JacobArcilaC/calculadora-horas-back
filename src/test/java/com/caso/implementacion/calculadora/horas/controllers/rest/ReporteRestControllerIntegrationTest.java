package com.caso.implementacion.calculadora.horas.controllers.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.caso.implementacion.calculadora.horas.models.dto.ReporteDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
public class ReporteRestControllerIntegrationTest {
		
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void crearReporte() throws Exception {
		ReporteDTO crearReporteRequest = new ReporteDTO();
		crearReporteRequest.setIdTecnico("1152224910");
		crearReporteRequest.setIdServicio("1");
		GsonBuilder gsonBuilder = new GsonBuilder();
		String reporteRequestJsonString = gsonBuilder.create().toJson(crearReporteRequest);
		JsonObject requestJson = new Gson().fromJson(reporteRequestJsonString, JsonObject.class);
		requestJson.remove("fechaInicio");
		requestJson.remove("fechaFin");
		requestJson.addProperty("fechaInicio", "2021-06-03T05:30");
		requestJson.addProperty("fechaFin", "2021-06-03T07:30");
		MvcResult result = mvc.perform(post("/api/reporte")
				 .contentType(MediaType.APPLICATION_JSON)
				 .content(requestJson.toString()))
				.andExpect(status().isOk()).andReturn();
		MockHttpServletResponse httpResponse = result.getResponse();
		JsonObject httpResponseJson = new Gson().fromJson(httpResponse.getContentAsString(), JsonObject.class);
		Assertions.assertTrue(httpResponseJson.get("code").getAsInt() == 201);
		Assertions.assertTrue(httpResponseJson.get("data").getAsJsonObject().get("valid").getAsBoolean() == true);
	}

}
