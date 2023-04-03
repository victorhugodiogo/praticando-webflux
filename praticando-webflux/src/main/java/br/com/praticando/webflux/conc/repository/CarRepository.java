package br.com.praticando.webflux.conc.repository;

import java.util.HashMap;
import java.util.Map;

import br.com.praticando.webflux.conc.domain.CarDTO;
import br.com.praticando.webflux.conc.domain.CarDocument;
import reactor.core.publisher.Mono;

public class CarRepository {
  private final Map<String, CarDocument> cars = new HashMap<>();
  
  public Mono<CarDocument> save(CarDTO dto){
    var doc = CarDocument.builder().build();
    cars.put(doc.getChassi(), doc);
    return Mono.just(doc);
  }
  
  public Mono<Void> save(CarDocument document){
    cars.put(document.getChassi(), document);
    return Mono.just(document).then();
  }
  
  public Mono<String> save(Mono<CarDocument> document){
    return document
        .doOnNext(doc -> {
          var ch = doc.getChassi();
          var chCompleted = "11" + ch;
          doc.setChassi(chCompleted);
          cars.put(chCompleted, doc);
        })
        .map(d -> d.getChassi());
  }
}
