package br.com.praticando.webflux.conc.domain;

import java.time.ZonedDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarDocument {
  private String chassi;
  private String ano;
  private String modelo;
  private ZonedDateTime dataCriacao;
}
