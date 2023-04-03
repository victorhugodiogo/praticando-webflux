package br.com.praticando.webflux.conc.rest;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarRequest {
	@Schema
	@Min(value = 6)
	@Max(value = 6)
	private String chassi; // 123456
	@Schema
	@Min(value = 1)
	private String ano;
	@Schema
	@Min(value = 1)
	private String modelo;
}
