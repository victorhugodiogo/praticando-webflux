package br.com.praticando.webflux.conc.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarDTO {
  private String chassi;
  private String ano;
  private String modelo;
}
