package com.berna.springboot.app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.berna.springboot.app.models.ParamDto;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/params")
public class RequestParamsController {
	
	@GetMapping("/foo")
	public ParamDto foo(@RequestParam(required = false, defaultValue = "No hay param", name = "mensaje") String message) { 
		// Con esta annotation espera un param, con required false no da error si no viene nada
		ParamDto param = new ParamDto();
		
		param.setMessage(message);
		// message va por la url con ?name=valor
		return param;
	}
	
	@GetMapping("/bar")
	public ParamDto bar(@RequestParam String text, @RequestParam Integer code) {
		ParamDto params = new ParamDto();
		
		params.setMessage(text);
		params.setCode(code);
		// los params van por la url con ?name=valor&name=valor
		return params;
	}
	
	@GetMapping("/request")
	public ParamDto request(HttpServletRequest request) {
		ParamDto params = new ParamDto();
		
		params.setCode(Integer.parseInt(request.getParameter("code")));
		params.setMessage(request.getParameter("message"));
		
		return params;
	}
}
