package br.com.praticando.webflux;

import java.time.Duration;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class FluxDelayExemplo {
  public static void main(String[] args) {
   
    
    // Delay
    Scheduler scheduler = Schedulers.newSingle("schEls1");
    Flux<Integer> integerFlux = Flux.range(1, 10).delayElements(Duration.ofSeconds(1), scheduler);
    integerFlux.subscribe(e -> System.out.println("Delay: " + e));
    
    
  }
}
