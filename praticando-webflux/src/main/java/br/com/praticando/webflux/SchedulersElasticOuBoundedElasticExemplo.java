package br.com.praticando.webflux;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SchedulersElasticOuBoundedElasticExemplo {
  public static void main(String[] args) throws InterruptedException {
    
    //Schedulers - elastic/boundedElastic
    Flux.range(1, 10)
        .flatMap(i -> Flux.just(i)
            //.subscribeOn(Schedulers.elastic())
            .subscribeOn(Schedulers.boundedElastic())
            .map(j -> j * j)
        )
        .subscribe(n -> System.out
            .println("Numero :[" + n + "] na thread:[" + Thread.currentThread().getName() + "]"));
    Thread.sleep(1000l);
  }
}