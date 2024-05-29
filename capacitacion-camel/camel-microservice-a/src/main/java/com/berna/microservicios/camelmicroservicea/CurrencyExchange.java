package com.berna.microservicios.camelmicroservicea;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class CurrencyExchange {
	private Long id;
	private String from;
	private String to;
	@JsonProperty("conversionMultiple") // Esto mapea la propiedad al atributo si tienen distinto nombre
	private BigDecimal conversion;

	public CurrencyExchange() {
	}

	public CurrencyExchange(Long id, String from, String to, BigDecimal convserion) {
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversion = convserion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public BigDecimal getConversion() {
		return conversion;
	}

	public void setConversion(BigDecimal conversion) {
		this.conversion = conversion;
	}

	@Override
	public String toString() {
		return "CurrencyExchange [id=" + id + ", from=" + from + ", to=" + to + ", conversion=" + conversion + "]";
	}

}
