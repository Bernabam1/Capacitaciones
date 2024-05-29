package com.berna.springboot.error.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.berna.springboot.error.exceptions.UserNotFoundException;
import com.berna.springboot.error.models.ErrorDTO;

@RestControllerAdvice
public class HandlerExceptionController {

	@ExceptionHandler({ ArithmeticException.class }) // Mapeo el error acá
	public ResponseEntity<ErrorDTO> divisionByZero(Exception ex) {
		
		ErrorDTO err = new ErrorDTO();
		err.setDate(new Date());
		err.setError("No se puede dividir por cero");
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

		// return ResponseEntity.internalServerError().body(err);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(err);
	}
	
	@ExceptionHandler({ NoHandlerFoundException.class })
	public ResponseEntity<ErrorDTO> notFoundEx(Exception ex){
		ErrorDTO err = new ErrorDTO();
		err.setDate(new Date());
		err.setError("No se encontró la página");
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		
		return ResponseEntity.status(404).body(err);
	}
	
	@ExceptionHandler({ NumberFormatException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // Otra forma de pasar el response status
	public Map<String,Object> numberFormatException(Exception ex){
		Map<String,Object> err = new HashMap<>();
		err.put("Date", new Date());
		err.put("Error", "Numero invalido o no tiene forma de dígito");
		err.put("message", ex.getMessage());
		err.put("Status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		
		return err;
	}
	
	@ExceptionHandler({ NullPointerException.class, HttpMessageNotWritableException.class, UserNotFoundException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // Otra forma de pasar el response status
	public Map<String,Object> userNotFoundException(Exception ex){
		Map<String,Object> err = new HashMap<>();
		err.put("Date", new Date());
		err.put("Error", "Usuario no existe o Role no existe");
		err.put("message", ex.getMessage());
		err.put("Status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		
		return err;
	}
}
