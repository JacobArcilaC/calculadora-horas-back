package com.caso.implementacion.calculadora.horas.models.dto;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class ResponseDTO<T> {

	@JsonIgnore
	private HttpStatus status;

	private int code;

	private T data;

	private List<String> errors;

	public ResponseDTO() {
		this(HttpStatus.INTERNAL_SERVER_ERROR, null, null, "Se ha producido un error. Por favor, póngase en contacto con el administrador.");
	}

	public ResponseDTO(HttpStatus status, List<String> errors) {
		this(status, null, errors);
	}

	public ResponseDTO(HttpStatus status, T data) {
		this(status, data, null);
	}

	public ResponseDTO(HttpStatus status, T data, List<String> errors) {
		this.status = status;
		this.code = status.value();
		this.errors = errors;
		this.data = data;
	}
	
	public ResponseDTO(HttpStatus status, T data, List<String> errors, String singleError) {
		this(status, data, errors);
		this.pushError(singleError);
	}
	
	public void pushError(String error) {
		if(null == this.errors)
			this.errors = new LinkedList<>();
		if(null != error)
			errors.add(error);
	}

	public void pushErrors(List<String> errors) {
		if(null == this.errors)
			this.errors = new LinkedList<>(errors);
		else
			this.errors.addAll(errors);
	}

	public boolean hasErrors() {
		return this.errors != null && !this.errors.isEmpty();
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrorMessage(List<String> errors) {
		this.errors = errors;
	}

}
