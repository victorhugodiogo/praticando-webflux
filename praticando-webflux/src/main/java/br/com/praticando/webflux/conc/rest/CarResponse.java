package br.com.praticando.webflux.conc.rest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarResponse {
  private String chassi;
  private String ano;
  private String modelo;
}
