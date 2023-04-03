package br.com.praticando.webflux.conc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.praticando.webflux.conc.domain.CarService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/conc/car")
public class CarController {
  @Autowired private CarService carService;
  
  @PostMapping
  public Mono<Void> saveCar(int chassi) {
    return Mono.just("").then();
  }
  
  @GetMapping
  public Mono<CarResponse> getCars() {
    return Mono.empty();
  }
  
  @GetMapping("/{chassi}")
  public Mono<CarResponse> getCars(int chassi) {
    return carService.getCar()
        .switchIfEmpty(Mono.error(new Exception("Não existe carro com esse chassi!")))
        .map(c -> CarResponse.builder().build());
  }
  
}
