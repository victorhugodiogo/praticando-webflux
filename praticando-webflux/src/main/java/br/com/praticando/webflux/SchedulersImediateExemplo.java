package br.com.praticando.webflux;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SchedulersImediateExemplo {
  public static void main(String[] args) {
    
    // Schedulers - immediate - executa na mesma thread
    Flux<Integer> syncFlux = Flux.just(1, 2, 3, 4, 5);
    syncFlux.subscribeOn(Schedulers.immediate())
        .subscribe(d -> System.out.println("Valor: [" + d + "] na thread :[" + Thread.currentThread().getName() + "]"));
  }

}
