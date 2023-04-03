package br.com.praticando.webflux.conc.domain;

import reactor.core.publisher.Mono;

public class CarService {
  public Mono<CarDTO> getCar() {
    return Mono.empty();
  }

  public Mono<Void> executeService() {
    return Mono.empty();
  }
  
  public Mono<Void> save(CarDTO dto) {
    return Mono.empty();
  }
}
